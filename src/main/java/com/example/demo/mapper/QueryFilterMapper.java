package com.example.demo.mapper;

import com.example.demo.input.FilterInput;
import com.example.demo.mapper.utils.FilterMappingUtil;
import com.example.demo.mapper.utils.MapSource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper( uses = FilterMappingUtil.class,componentModel="spring")
public interface QueryFilterMapper {

    QueryFilterMapper MAPPER = Mappers.getMapper( QueryFilterMapper.class );

    @Mappings( {
            @Mapping(
                    source = "map",
                    target = "property",
                    qualifiedBy = FilterMappingUtil.Property.class ),
    })
    FilterInput toFilter(MapSource s);

}
