package com.learning.facedetectionbrains.api;

import com.learning.facedetectionbrains.dto.ImageRequestDto;
import com.learning.facedetectionbrains.dto.LoginRequestDto;
import com.learning.facedetectionbrains.dto.RegisterRequestDto;
import com.learning.facedetectionbrains.dto.UserProfileDto;
import com.learning.facedetectionbrains.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        List<UserProfileDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserProfileDto> login(@RequestBody LoginRequestDto requestDto) {
        UserProfileDto userProfileDto = userService.login(requestDto);
        return ResponseEntity.ok(userProfileDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserProfileDto> register(@RequestBody RegisterRequestDto requestDto) {
        UserProfileDto userProfileDto = userService.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userProfileDto);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfileDto> getProfile(@PathVariable("id") Integer userId) {
        UserProfileDto userProfileDto = userService.getProfile(userId);
        return ResponseEntity.ok(userProfileDto);
    }

    @PutMapping("/image")
    public ResponseEntity<Integer> imageUpdate(@RequestBody ImageRequestDto requestDto) {
        Integer count = userService.updateEntries(requestDto.getUserId());
        return ResponseEntity.ok(count);
    }
}
