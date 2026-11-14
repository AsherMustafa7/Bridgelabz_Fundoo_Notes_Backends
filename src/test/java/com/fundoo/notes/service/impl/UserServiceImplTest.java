
/*
 * Class Name  : UserServiceImplTest
 * Description : Tests registration and login business logic,
 *               including password encoding, duplicate email
 *               validation and JWT token generation.
 * Author      : Asher Mustafa
 * @author Asher
 * Date        : 14/11/2026
 */

package com.fundoo.notes.service.impl;

import com.fundoo.notes.dto.request.LoginRequestDto;
import com.fundoo.notes.dto.request.UserRegisterRequestDto;
import com.fundoo.notes.dto.response.LoginResponseDto;
import com.fundoo.notes.dto.response.UserResponseDto;
import com.fundoo.notes.entity.User;
import com.fundoo.notes.exception.DuplicateEmailException;
import com.fundoo.notes.exception.InvalidCredentialsException;
import com.fundoo.notes.repository.UserRepository;
import com.fundoo.notes.service.PasswordService;
import com.fundoo.notes.service.TokenService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Mockito-based unit tests for UserServiceImpl.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordService passwordService;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private UserServiceImpl userService;

    /*
     * Test 1:
     * Verify that registration saves the user
     * with an encoded password.
     */
    @Test
    void shouldRegisterUserWithEncodedPassword() {

        UserRegisterRequestDto request = new UserRegisterRequestDto();
        request.setFirstName("Asher");
        request.setEmail("asher@example.com");
        request.setPassword("Password123");

        // [Asher Mustafa]: Mock email lookup to indicate that the email is new.
        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        // [Asher Mustafa]: Mock BCrypt password encoding.
        when(passwordService.encodePassword("Password123"))
                .thenReturn("$2a$10$encodedPasswordHash");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setFirstName("Asher");
        savedUser.setEmail("asher@example.com");
        savedUser.setPassword("$2a$10$encodedPasswordHash");

        // [Asher Mustafa]: Mock database save operation.
        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        UserResponseDto response = userService.register(request);

        // [Asher Mustafa]: Verify registration response.
        assertEquals(1L, response.getId());
        assertEquals("Asher", response.getFirstName());
        assertEquals("asher@example.com", response.getEmail());

        // [Asher Mustafa]: Verify password encoding and repository save.
        verify(passwordService).encodePassword("Password123");
        verify(userRepository).save(any(User.class));
    }

    /*
     * Test 2:
     * Verify that registration rejects an existing email.
     */
    @Test
    void shouldRejectDuplicateEmail() {

        UserRegisterRequestDto request = new UserRegisterRequestDto();
        request.setFirstName("Asher");
        request.setEmail("asher@example.com");
        request.setPassword("Password123");

        // [Asher Mustafa]: Mock an existing user with the same email.
        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(new User()));

        // [Asher Mustafa]: Verify that the custom exception is thrown.
        assertThrows(
                DuplicateEmailException.class,
                () -> userService.register(request)
        );

        // [Asher Mustafa]: Password encoding and saving must not happen.
        verify(passwordService, never()).encodePassword(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    /*
     * Test 3:
     * Verify successful login with correct credentials.
     */
    @Test
    void shouldLoginSuccessfullyWithCorrectPassword() {

        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("asher@example.com");
        request.setPassword("Password123");

        User user = new User();
        user.setId(1L);
        user.setFirstName("Asher");
        user.setEmail("asher@example.com");
        user.setPassword("$2a$10$encodedPasswordHash");

        // [Asher Mustafa]: Mock user lookup.
        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(user));

        // [Asher Mustafa]: Mock successful password verification.
        when(passwordService.matchesPassword(
                "Password123",
                "$2a$10$encodedPasswordHash"
        )).thenReturn(true);

        // [Asher Mustafa]: Mock JWT generation.
        when(tokenService.generateToken("asher@example.com"))
                .thenReturn("sample.jwt.token");

        LoginResponseDto response = userService.login(request);

        // [Asher Mustafa]: Verify login response.
        assertEquals("sample.jwt.token", response.getToken());
        assertEquals("Login successful", response.getMessage());

        // [Asher Mustafa]: Verify password verification and token generation.
        verify(passwordService).matchesPassword(
                "Password123",
                "$2a$10$encodedPasswordHash"
        );

        verify(tokenService).generateToken("asher@example.com");
    }

    /*
     * Test 4:
     * Verify that login rejects an unknown email address.
     */
    @Test
    void shouldRejectLoginForUnknownEmail() {

        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("unknown@example.com");
        request.setPassword("Password123");

        // [Asher Mustafa]: Mock an email lookup that returns no user.
        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        // [Asher Mustafa]: Verify the custom invalid credentials exception.
        assertThrows(
                InvalidCredentialsException.class,
                () -> userService.login(request)
        );

        // [Asher Mustafa]: Password verification and token generation must not happen.
        verify(passwordService, never())
                .matchesPassword(anyString(), anyString());

        verify(tokenService, never()).generateToken(anyString());
    }

    /*
     * Test 5:
     * Verify that login rejects an incorrect password.
     */
    @Test
    void shouldRejectLoginForIncorrectPassword() {

        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("asher@example.com");
        request.setPassword("WrongPassword");

        User user = new User();
        user.setEmail("asher@example.com");
        user.setPassword("$2a$10$encodedPasswordHash");

        // [Asher Mustafa]: Mock successful user lookup.
        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(user));

        // [Asher Mustafa]: Mock failed password verification.
        when(passwordService.matchesPassword(
                "WrongPassword",
                "$2a$10$encodedPasswordHash"
        )).thenReturn(false);

        // [Asher Mustafa]: Verify the custom invalid credentials exception.
        assertThrows(
                InvalidCredentialsException.class,
                () -> userService.login(request)
        );

        // [Asher Mustafa]: Token must never be generated for invalid credentials.
        verify(tokenService, never()).generateToken(anyString());
    }
}
