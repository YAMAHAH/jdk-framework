package com.example.demo.mapper;

import com.example.demo.models.Good;
import com.example.demo.output.GoodOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface GoodMapper {
    public static GoodMapper goodMapper  = Mappers.getMapper(GoodMapper.class);

    @Mappings({
            @Mapping(source = "id",target = "id"),
    })
    public GoodOutput ModelToViewModel(Good good);

    public List<GoodOutput> GoodToViewModels(List<GoodOutput> list);
}
