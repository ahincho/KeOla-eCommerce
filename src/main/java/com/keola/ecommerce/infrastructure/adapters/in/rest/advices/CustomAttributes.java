package com.keola.ecommerce.infrastructure.adapters.in.rest.advices;

import com.keola.ecommerce.domain.exceptions.ClientDuplicateException;
import com.keola.ecommerce.domain.exceptions.ClientNotFoundException;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        HashMap<String, Object> errorAttributes = new HashMap<>();
        Throwable throwable = super.getError(request);
        if (throwable instanceof ClientNotFoundException) {
            errorAttributes.put("status", HttpStatus.NOT_FOUND);
            errorAttributes.put("message", throwable.getMessage());
        } else if (throwable instanceof ClientDuplicateException) {
            errorAttributes.put("status", HttpStatus.CONFLICT);
            errorAttributes.put("message", throwable.getMessage());
        } else {
            errorAttributes.put("status", HttpStatus.BAD_REQUEST);
            errorAttributes.put("message", throwable.getMessage());
        }
        return errorAttributes;
    }
}
