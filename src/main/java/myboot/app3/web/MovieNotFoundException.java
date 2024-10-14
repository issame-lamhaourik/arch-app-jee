package myboot.app3.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class MovieNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    }
