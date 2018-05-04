package com.example.demo.mapper.utils;

import com.example.demo.input.FilterInput;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
@Component
public class CycleMappingContext {
    private Map<Object, Object> knownInstances = new IdentityHashMap<>();

    private Map<String,Object>  fieldMaps;

    public Object getPropertyValue(String key){
        return this.fieldMaps.get(key);
    }
    @BeforeMapping
    public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
        return (T) knownInstances.get( source );
    }

    @BeforeMapping
    public void storeMappedInstance(Object source, @MappingTarget Object target) {
        knownInstances.put(source, target);
    }

    @AfterMapping
    public void MappedAfter(Object source,@MappingTarget Object result) {
       // FilterInput input = (FilterInput) result;
        //ExpandFilter(input);
    }

    public CycleMappingContext(Map<String,Object> dataMap){
        this.fieldMaps = dataMap;
        if(null == dataMap){
            this.fieldMaps = new HashMap<String, Object>();
        }
    }

    public void ExpandFilter(FilterInput filter){
        String value =(String)this.fieldMaps.get(filter.getProperty());
        if(!value.isEmpty()){
            filter.setProperty(value);
        }
        for (FilterInput input : filter.getChildFilters()) {
            ExpandFilter(input);
        }
    }

}
