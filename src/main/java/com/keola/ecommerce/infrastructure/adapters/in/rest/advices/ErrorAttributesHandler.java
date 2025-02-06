package com.keola.ecommerce.infrastructure.adapters.in.rest.advices;

import java.util.Map;

public interface ErrorAttributesHandler {
    Map<String, Object> handle(Throwable throwable);
}
