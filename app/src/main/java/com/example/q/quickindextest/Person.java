package com.example.q.quickindextest;

/**
 * Created by YQ on 2016/7/30.
 */
public class Person implements Comparable<Person> {
    private String name;
    private String pinyin;

    public Person(String name) {
        this.name = name;
        this.pinyin = PinYinUtils.getPinyin(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }


    @Override
    public int compareTo(Person person) {
        return this.pinyin.compareTo(person.getPinyin());
    }
}
