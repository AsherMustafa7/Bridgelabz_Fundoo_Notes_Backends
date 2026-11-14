/*
 * Class Name  : DuplicateEmailException
 * Description : Custom exception thrown when a user registers
 *               with an email address that already exists.
 * Author      : Asher Mustafa
 * @author Asher
 * Date        : 14/11/2026
 */

package com.fundoo.notes.exception;

public class DuplicateEmailException extends RuntimeException {

    // [Asher Mustafa]: Creates an exception with a descriptive message.
    public DuplicateEmailException(String message) {
        super(message);
    }
}