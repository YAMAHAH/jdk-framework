package com.example.demo.mapper;

import com.example.demo.input.FilterInput;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;


@Mapper(componentModel = "spring")
public abstract class FilterMapper {

    public static FilterMapper MAPPER = Mappers.getMapper( FilterMapper.class );
    public FilterInput convert(FilterInput entity,Map<String,Object> propertyMap) {
        FilterInput input = map(entity);
        input.setProperty((String)propertyMap.get(input.getProperty()));
        return input;
    }

    public List<FilterInput> convert(List<FilterInput> list,Map<String,Object> propertyMap) {

        List<FilterInput> listMap = map(list);
        for(FilterInput input : listMap) {
            String prop =(String) propertyMap.get(input.getProperty());
            if(!StringUtils.isEmpty(prop)){
                input.setProperty(prop);
            }
        }
        return listMap;
    }

    @AfterMapping
    protected void fillProperty(FilterMapper filterMapper, @MappingTarget FilterInput result) {
            result.setIgnoreCase(false);
    }

    protected abstract FilterInput map(FilterInput filterInput);
    protected abstract List<FilterInput> map(List<FilterInput> list);
}