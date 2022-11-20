package com.irynapistun.exception;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(Integer id) {
        super("Album is not found by id " + id);
    }
}
