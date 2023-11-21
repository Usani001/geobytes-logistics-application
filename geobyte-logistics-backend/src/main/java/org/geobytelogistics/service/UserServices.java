package org.geobytelogistics.service;

import jakarta.servlet.http.HttpServletRequest;
import org.geobytelogistics.dto.request.UserRequest;
import org.geobytelogistics.dto.response.ApiResponse;
import org.geobytelogistics.dto.response.UserResponse;



public interface UserServices {

    ApiResponse<UserResponse> registerUser(UserRequest request);
    ApiResponse<UserResponse> loginUser(UserRequest request, HttpServletRequest httpRequest);
    ApiResponse<String> logoutUser(HttpServletRequest request);
}
