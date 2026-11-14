/*
 * Class Name  : InvalidCredentialsException
 * Description : Custom exception thrown when login credentials
 *               are incorrect.
 * Author      : Asher Mustafa
 * @author Asher
 * Date        : 14/11/2026
 */

package com.fundoo.notes.exception;

public class InvalidCredentialsException extends RuntimeException {

    // [Asher Mustafa]: Creates an exception with a descriptive message.
    public InvalidCredentialsException(String message) {
        super(message);
    }
}