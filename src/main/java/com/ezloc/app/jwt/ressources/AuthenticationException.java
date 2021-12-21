package com.ezloc.app.jwt.ressources;
public class AuthenticationException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7030916594359090743L;

	public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}

