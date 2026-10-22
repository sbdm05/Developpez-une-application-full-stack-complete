package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ProfileResponse;
import com.openclassrooms.mddapi.dto.UpdateProfileRequest;

public interface UserService {

    ProfileResponse getProfile(Long userId);

    ProfileResponse updateProfile(Long userId, UpdateProfileRequest request);
}