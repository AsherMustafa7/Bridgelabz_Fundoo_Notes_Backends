
package com.fundoo.notes.controller;

import com.fundoo.notes.dto.request.LoginRequestDto;
import com.fundoo.notes.dto.request.UserRegisterRequestDto;
import com.fundoo.notes.dto.response.LoginResponseDto;
import com.fundoo.notes.dto.response.UserResponseDto;
import com.fundoo.notes.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Class Name  : UserController
 * Description : Exposes REST endpoints for user registration
 *               and login operations.
 * Author      : Asher Mustafa
 * @author Asher
 * Date        : 14/11/2026
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // [Asher Mustafa]: Constructor injection of the user service.
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // [Asher Mustafa]: Registers a new user and returns HTTP 201.
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
            @Valid @RequestBody UserRegisterRequestDto requestDto) {

        UserResponseDto response = userService.register(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // [Asher Mustafa]: Authenticates the user and returns a JWT.
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto requestDto) {

        LoginResponseDto response = userService.login(requestDto);

        return ResponseEntity.ok(response);
    }
}
