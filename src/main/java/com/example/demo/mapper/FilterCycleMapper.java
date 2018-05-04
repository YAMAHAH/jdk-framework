package com.example.demo.mapper;

import com.example.demo.input.FilterInput;
import com.example.demo.mapper.utils.CycleMappingContext;
import com.example.demo.mapper.utils.FilterFieldMap;
import com.example.demo.mapper.utils.FilterFieldMapping;
import com.example.demo.models.FilterModel;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@SuppressWarnings("unchecked")
@Mapper(uses = {FilterFieldMapping.class} , componentModel = "spring")
public interface FilterCycleMapper {

    @Mapping(source = "property",
            target = "property",
            qualifiedBy = FilterFieldMap.class
        )
    FilterModel toFilterModel(FilterInput filter, @Context CycleMappingContext context);

    List<FilterModel> toFilterModel(List<FilterInput> filters, @Context CycleMappingContext context);

    @InheritInverseConfiguration
    FilterInput fromFilterModel(FilterModel filterModel, @Context CycleMappingContext context);

    List<FilterInput> fromFilterModel(List<FilterModel> filters, @Context CycleMappingContext context);
}
