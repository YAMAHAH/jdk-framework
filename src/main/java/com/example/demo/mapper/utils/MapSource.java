package com.example.demo.mapper.utils;

import java.util.Map;

public class MapSource {

    private Map<String, Object> _map;

    public Map<String, Object> getMap() {
        return _map;
    }

    public void setMap(Map<String, Object> map) {
        this._map = map;
    }

    public MapSource(Map<String, Object> map) {
        this._map = map;
    }

}
