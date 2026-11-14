
package com.fundoo.notes.dto.response;

/*
 * Class Name  : UserResponseDto
 * Description : Represents the safe user information returned
 *               by the application after registration.
 * Author      : Asher Mustafa
 * @author Asher
 * Date        : 14/11/2026
 */

public class UserResponseDto {

    private Long id;
    private String firstName;
    private String email;

    // [Asher Mustafa]: Default constructor.
    public UserResponseDto() {
    }

    // [Asher Mustafa]: Initializes the registration response.
    public UserResponseDto(Long id, String firstName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
    }

    // [Asher Mustafa]: Returns the user ID.
    public Long getId() {
        return id;
    }

    // [Asher Mustafa]: Sets the user ID.
    public void setId(Long id) {
        this.id = id;
    }

    // [Asher Mustafa]: Returns the first name.
    public String getFirstName() {
        return firstName;
    }

    // [Asher Mustafa]: Sets the first name.
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // [Asher Mustafa]: Returns the email.
    public String getEmail() {
        return email;
    }

    // [Asher Mustafa]: Sets the email.
    public void setEmail(String email) {
        this.email = email;
    }
}
