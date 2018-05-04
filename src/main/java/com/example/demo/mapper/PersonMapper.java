package com.example.demo.mapper;

import com.example.demo.input.PersonInput;
import com.example.demo.models.Person;
import com.example.demo.output.PersonOutput;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface PersonMapper {

    public static PersonMapper personMapper  = Mappers.getMapper(PersonMapper.class);
    @Mappings({
            @Mapping(source = "person.id",target = "personId"),
            @Mapping(source = "person.name",target = "personName"),
            @Mapping(source = "person.age",target = "personAge")
    })
    public PersonOutput from(Person person);

    @InheritInverseConfiguration
    public Person PersonVoToPerson(PersonOutput personOutput);

    @Mappings({
            @Mapping(target = "id", source = "personId"),
            @Mapping(target = "name", source = "personName"),
            @Mapping(target = "age", source = "personAge")
    })
    public void UpdatePersonOutput( PersonOutput personOutput, @MappingTarget Person person);

    public List<PersonOutput> PersonToPersonOutputs(List<Person> list);

    @InheritInverseConfiguration
    public  List<Person> PersonOutputToPersons(List<PersonOutput> personOutputs);

    @Mappings({
            @Mapping(source = "personInput.id",target = "id"),
            @Mapping(source = "personInput.name",target = "name"),
            @Mapping(source = "personInput.age",target = "age")
    })
    public Person PersonInputToPerson(PersonInput personInput);
    public List<Person> PersonInputToPersonos(List<PersonInput> list);
}
