package org.geobytelogistics.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.geobytelogistics.dto.request.UserRequest;
import org.geobytelogistics.dto.response.UserResponse;
import org.geobytelogistics.entities.User;
import org.geobytelogistics.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntityMapper {
    private final UserRepository userRepository;
    public UserResponse userResponse (UserRequest request){
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        User userResponse = optionalUser.get();
        UserResponse response = UserResponse.builder()
                .id(userResponse.getId())
                .firstName(userResponse.getFirstName())
                .lastName(userResponse.getLastName())
                .email(userResponse.getEmail())
                .build();
        return response;
    }
}
