
package com.fundoo.notes.service;

/*
 * Class Name  : PasswordService
 * Description : Defines password encoding and verification
 *               operations for the application.
 * Author      : Asher Mustafa
 * @author Asher
 * Date        : 14/11/2026
 */
public interface PasswordService {

    // [Asher Mustafa]: Converts a raw password into a BCrypt hash.
    String encodePassword(String rawPassword);

    // [Asher Mustafa]: Checks whether a raw password matches its stored hash.
    boolean matchesPassword(String rawPassword, String encodedPassword);
}
