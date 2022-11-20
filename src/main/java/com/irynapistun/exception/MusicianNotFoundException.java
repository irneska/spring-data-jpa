package com.irynapistun.exception;

public class MusicianNotFoundException extends RuntimeException {
    public MusicianNotFoundException(Integer id) {
        super("Musician is not found by id " + id);
    }
}
