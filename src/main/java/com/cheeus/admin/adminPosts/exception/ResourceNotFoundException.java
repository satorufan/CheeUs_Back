package com.cheeus.admin.adminPosts.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ResourceNotFoundException.class);

    public ResourceNotFoundException(String message) {
        super(message);
        ResourceNotFoundException.log.error("오류메세지 작동테스트");
    }

}
