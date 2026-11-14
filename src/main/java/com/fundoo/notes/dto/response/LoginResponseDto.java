
package com.fundoo.notes.dto.response;

/*
 * Class Name  : LoginResponseDto
 * Description : Represents the response returned after
 *               successful user authentication.
 * Author      : Asher Mustafa
 * @author Asher
 * Date        : 14/11/2026
 */

public class LoginResponseDto {

    private String token;
    private String message;

    // [Asher Mustafa]: Default constructor.
    public LoginResponseDto() {
    }

    // [Asher Mustafa]: Initializes the login response.
    public LoginResponseDto(String token, String message) {
        this.token = token;
        this.message = message;
    }

    // [Asher Mustafa]: Returns the authentication token.
    public String getToken() {
        return token;
    }

    // [Asher Mustafa]: Sets the authentication token.
    public void setToken(String token) {
        this.token = token;
    }

    // [Asher Mustafa]: Returns the response message.
    public String getMessage() {
        return message;
    }

    // [Asher Mustafa]: Sets the response message.
    public void setMessage(String message) {
        this.message = message;
    }
}
