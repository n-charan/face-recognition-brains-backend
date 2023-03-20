package com.learning.facedetectionbrains.service;

import com.learning.facedetectionbrains.dto.LoginRequestDto;
import com.learning.facedetectionbrains.dto.RegisterRequestDto;
import com.learning.facedetectionbrains.dto.UserProfileDto;
import com.learning.facedetectionbrains.entity.User;
import com.learning.facedetectionbrains.exception.LoginException;
import com.learning.facedetectionbrains.exception.ResourceNotFoundException;
import com.learning.facedetectionbrains.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userService")
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;


    public List<UserProfileDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserProfileDto> userProfileDtos = new ArrayList<>();
        users.stream().forEach(user -> {
            userProfileDtos.add(getUserProfileDto(user));
        });
        return userProfileDtos;
    }

    public UserProfileDto login(LoginRequestDto requestDto) {
        Optional<User> userOptional = userRepo.getUserByEmailAndPassword(requestDto.getEmail(),
                requestDto.getPassword());
        if (userOptional.isPresent()) {
            return getUserProfileDto(userOptional.get());
        } else {
            throw new LoginException("error logging in");
        }
    }

    public UserProfileDto register(RegisterRequestDto requestDto) {
        User user = new User();
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        User savedUser = userRepo.save(user);
        return getUserProfileDto(savedUser);
    }

    public UserProfileDto getProfile(Integer userId) {
        Optional<User> userOptional = userRepo.getByUserId(userId);
        if (userOptional.isPresent()) {
            return getUserProfileDto(userOptional.get());
        } else {
            throw new ResourceNotFoundException("No user with id : " + userId);
        }
    }

    public Integer updateEntries(Integer userId) {
        Optional<User> userOptional = userRepo.getByUserId(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEntries(user.getEntries() + 1);
            user = userRepo.save(user);
            return user.getEntries();
        } else {
            throw new ResourceNotFoundException("No user with id : " + userId);
        }
    }

    private UserProfileDto getUserProfileDto(User user) {
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setId(user.getId());
        userProfileDto.setName(user.getName());
        userProfileDto.setEmail(user.getEmail());
        userProfileDto.setPassword(user.getPassword());
        userProfileDto.setEntries(user.getEntries());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        userProfileDto.setJoined(user.getJoined().format(dateTimeFormatter));
        return userProfileDto;
    }

}
