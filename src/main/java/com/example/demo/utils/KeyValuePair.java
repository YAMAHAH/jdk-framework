package com.example.demo.utils;

public class KeyValuePair<T> {
    // 键
    public T key;

    // 值
    public Object value;

    /**
     * 构造方法
     *
     * @param key
     * @param value
     */
    public KeyValuePair(T key, Object value) {
        this.key = key;
        this.value = value;
    }
}
