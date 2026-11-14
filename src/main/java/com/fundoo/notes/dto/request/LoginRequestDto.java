
package com.fundoo.notes.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/*
 * Class Name  : LoginRequestDto
 * Description : Stores and validates the email and password
 *               submitted by a user during login.
 * Author      : Asher Mustafa
 * @author Asher
 * Date        : 14/11/2026
 */

public class LoginRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    // [Asher Mustafa]: Default constructor for DTO object creation.
    public LoginRequestDto() {
    }

    // [Asher Mustafa]: Returns the email address.
    public String getEmail() {
        return email;
    }

    // [Asher Mustafa]: Sets the email address.
    public void setEmail(String email) {
        this.email = email;
    }

    // [Asher Mustafa]: Returns the password.
    public String getPassword() {
        return password;
    }

    // [Asher Mustafa]: Sets the password.
    public void setPassword(String password) {
        this.password = password;
    }
}
