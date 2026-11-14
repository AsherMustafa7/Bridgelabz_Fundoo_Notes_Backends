
package com.fundoo.notes.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/*
 * Class Name  : UserRegisterRequestDto
 * Description : Stores and validates the user registration
 *               details received from the client.
 * Author      : Asher Mustafa
 * @author Asher
 * Date        : 14/11/2026
 */

public class UserRegisterRequestDto {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    // [Asher Mustafa]: Default constructor for DTO object creation.
    public UserRegisterRequestDto() {
    }

    // [Asher Mustafa]: Returns the first name.
    public String getFirstName() {
        return firstName;
    }

    // [Asher Mustafa]: Sets the first name.
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
