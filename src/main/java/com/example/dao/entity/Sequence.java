package com.example.dao.entity;

public class Sequence {
    private String prefix;

    private String name;

    private String today;

    private Integer minNum;

    private Integer currentNum;

    private Integer numLength;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? null : prefix.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today == null ? null : today.trim();
    }

    public Integer getMinNum() {
        return minNum;
    }

    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
    }

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    public Integer getNumLength() {
        return numLength;
    }

    public void setNumLength(Integer numLength) {
        this.numLength = numLength;
    }
    public Sequence(){}

    public Sequence(String prefix, String name, int minNum, int currentNum, int numLength) {
        this.prefix = prefix;
        this.name = name;
        this.minNum = minNum;
        this.currentNum = currentNum;
        this.numLength = numLength;
    }
    @Override
    public String toString() {
        return "SlmSequence{" +
                "prefix='" + prefix + '\'' +
                ", name='" + name + '\'' +
                ", today='" + today + '\'' +
                ", minNum=" + minNum +
                ", currentNum=" + currentNum +
                ", numLength=" + numLength +
                '}';
    }
}