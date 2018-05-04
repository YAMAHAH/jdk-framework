package com.example.demo.utils;

import java.util.Iterator;
import java.util.Vector;

@SuppressWarnings("unchecked")
public class OrderArray<T> implements Iterable<KeyValuePair> {
    // 实际保存数据的有序数组
    private Vector<KeyValuePair> items = new Vector<KeyValuePair>();

    /**
     * 带初始数据的构造函数
     *
     * @param items
     */
    public OrderArray(KeyValuePair... items) {
        for (KeyValuePair item : items) {
            this.items.addElement(item);
        }
    }

    /**
     * 不带初始数据的构造函数
     */
    public OrderArray() {
    }

    /**
     * 返回全部数据
     *
     * @return
     */
    public Vector<KeyValuePair> getItems() {
        return items;
    }

    /**
     * 添加一个值
     *
     * @param item
     * @return
     */
    public OrderArray add(KeyValuePair<T> item) {
        return set(item.key, item.value);
    }

    /**
     * 冗余方法,同样添加一个值
     *
     * @param item
     * @return
     */
    public OrderArray put(KeyValuePair<T> item) {
        return set(item.key, item.value);
    }

    /**
     * 快速添加一个键值对
     *
     * @param key
     * @param value
     * @return
     */
    public OrderArray add(T key, Object value) {
        return set(key, value);
    }

    /**
     * 同上
     *
     * @param key
     * @param value
     * @return
     */
    public OrderArray put(T key, Object value) {
        return set(key, value);
    }

    /**
     * 修改一个值
     *
     * @param key
     * @param value
     * @return
     */
    public OrderArray set(T key, Object value) {
        // 如果原来有,修改
        for (KeyValuePair item : items) {
            if (item.key.toString().equalsIgnoreCase(key.toString())) {
                item.value = value;
                return this;
            }
        }

        // 否则 添加
        items.addElement(new KeyValuePair(key, value));
        return this;
    }

    /**
     * 移除一个值
     *
     * @param key
     * @return
     */
    public OrderArray remove(T key) {
        for (KeyValuePair item : items) {
            if (item.key.toString().equalsIgnoreCase(key.toString())) {
                items.remove(item);
            }
        }
        return this;
    }

    /**
     * 取一个键值对
     *
     * @param index
     * @return
     */
    public KeyValuePair get(int index) {
        return items.get(index);
    }

    /**
     * 根据键取值
     *
     * @param key
     * @return
     */
    public Object get(T key) {
        for (KeyValuePair item : items) {
            if (item.key.toString().equalsIgnoreCase(key.toString())) {
                return item.value;
            }
        }
        return null;
    }

    /**
     * 返回 遍历器
     *
     * @return
     */
    public Iterator<KeyValuePair> iterator() {
        return new ArrayIterator();
    }

    /**
     * 遍历类
     *
     * @author 蓝冰大侠
     */
    class ArrayIterator implements Iterator<KeyValuePair> {
        // 索引
        private int index = 0;

        /**
         * 是否有下一个
         */
        public boolean hasNext() {
            return index != items.size();
        }

        /**
         * 取下一个
         */
        public KeyValuePair next() {
            return items.get(index++);
        }

        /**
         * 删除一个
         */
        public void remove() {
            items.remove(index);
        }
    }

    /**
     * 字段名修正大小写
     *
     * @param name
     */
    public void rename(T... names) {
        //逐个名称遍历
        for (T name : names) {

            //在保存的数据里遍历
            for (KeyValuePair item : items) {

                //如果原来的键(不区分大小写)匹配,则修改为新的键
                if (item.key.toString().equalsIgnoreCase(name.toString())) {
                    item.key = name;
                }
            }
        }
    }

    public Integer getSize(){
        return  this.items.size();
    }
}
