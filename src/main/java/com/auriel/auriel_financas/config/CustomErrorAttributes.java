package com.auriel.auriel_financas.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.HashMap;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Throwable error = getError(webRequest);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("message", error != null ? error.getMessage() : "Erro desconhecido");
        return attributes;
    }
}
