package com.example.demo.mapper.utils;

import org.mapstruct.Context;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Component
public class FilterFieldMapping {

    @FilterFieldMap
    public String getFilterFieldValue(String source, @Context CycleMappingContext context) {
        String mapValue = (String)context.getPropertyValue(source);
        return !StringUtils.isEmpty(mapValue) ? mapValue : source;
    }
}
