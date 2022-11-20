package com.irynapistun.exception;

import com.irynapistun.domain.User;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id){
        super("User is not found by id " + id);
    }
}
