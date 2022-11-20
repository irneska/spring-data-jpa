package com.irynapistun.exception;

import com.irynapistun.domain.Song;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(Integer id) {
        super("Song is not found by id " + id);
    }
}
