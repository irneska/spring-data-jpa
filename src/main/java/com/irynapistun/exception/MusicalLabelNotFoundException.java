package com.irynapistun.exception;

import com.irynapistun.domain.MusicalLabel;

public class MusicalLabelNotFoundException extends RuntimeException {
    public MusicalLabelNotFoundException(Integer id) {
        super("Musical label is not found by id " + id);
    }
}
