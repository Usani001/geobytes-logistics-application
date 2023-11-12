package org.geobytelogistics.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.geobytelogistics.dto.request.UserRequest;
import org.geobytelogistics.dto.response.ApiResponse;
import org.geobytelogistics.dto.response.UserResponse;

import org.geobytelogistics.exceptions.UserExistException;

import org.geobytelogistics.service.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@ControllerAdvice
public class AuthController {



        private final UserServices userServices;


        @PostMapping("/register")
        public ResponseEntity<ApiResponse<?>> registerUser(@RequestBody @Validated UserRequest request) {
            try {
                ApiResponse<UserResponse> response = userServices.registerUser(request);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }catch (UserExistException e){
                return ResponseEntity.status(e.getHttpStatus()).body(new ApiResponse<>(e.getMessage()));
            }
        }

        @PostMapping("/login")
        public ResponseEntity<ApiResponse<?>> loginUser(@RequestBody UserRequest request,
                                                                   HttpServletRequest httpRequest) {

                ApiResponse<UserResponse> response = userServices.loginUser(request, httpRequest);
                return ResponseEntity.ok(response);

        }

        @PostMapping("/logout")
        public ResponseEntity<ApiResponse<?>> logoutUser(HttpServletRequest request) {

            ApiResponse<String> response = userServices.logoutUser(request);
            return ResponseEntity.status(HttpStatus.GONE).body(response);
        }


}
