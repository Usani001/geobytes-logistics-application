package org.geobytelogistics.service.service_implementation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.geobytelogistics.dto.request.UserRequest;
import org.geobytelogistics.dto.response.ApiResponse;
import org.geobytelogistics.dto.response.UserResponse;
import org.geobytelogistics.entities.User;
import org.geobytelogistics.repository.UserRepository;
import org.geobytelogistics.service.UserServices;
import org.geobytelogistics.utils.EntityMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserServices {



    private final UserRepository userRepository;
    private final EntityMapper entityMapper;

    public ApiResponse<UserResponse> registerUser(UserRequest request){
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            return new ApiResponse<>("01","User already exists or email has already been taken", HttpStatus.ALREADY_REPORTED);
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        log.info("User has been saved to database");
        userRepository.save(user);
        return new ApiResponse<>("00","User has been registered Successfully", entityMapper.userResponse(request), "Success", HttpStatus.CREATED);


    }


    public ApiResponse<UserResponse> loginUser(UserRequest request, HttpServletRequest httpRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return new ApiResponse<>("02","Email does not exist, please signup if you are a new user", HttpStatus.NOT_FOUND);
        }
        User user = optionalUser.get();
        if (!user.getPassword().equals(request.getPassword())) {
            return new ApiResponse<>("03","Invalid password!!! Please provide a valid password", HttpStatus.FORBIDDEN);
        }
        HttpSession session = httpRequest.getSession();
        session.setAttribute("user", user);

        return new ApiResponse<>("00", "User has been logged in Successfully", entityMapper.userResponse(request), "Success", HttpStatus.ACCEPTED);

    }

    public ApiResponse<String> logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Invalidate the session
            session.invalidate();
            log.info("User has been logged out");
            return new ApiResponse<>("00", "User has been logged out", null, "Success", HttpStatus.OK);
        }
        return new ApiResponse<>("01", "No active session found", null, "Error", HttpStatus.BAD_REQUEST);
    }
}
