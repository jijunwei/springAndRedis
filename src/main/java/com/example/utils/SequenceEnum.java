package com.example.utils;


import com.example.dao.entity.Sequence;

/**
 * 主键生成策略枚举
 *  Sequence构造参数如下
 *  prefis：前缀，name：策略名，minNum：起始顺序号，currentNum：当前顺序号,numLength:顺序号长度
 *  起始顺序号长度不满numLength定义长度前面自动补0
 *
 * @data 2017/8/9 11:22
 *
 */
public enum SequenceEnum {
    /**小区*/
    XQ(new Sequence("010","小区主键生成策略", 1, 1, 8)),
    /**房源*/
    FY(new Sequence("010","房源主键生成策略", 1, 1, 8));


    private Sequence Sequence;
    SequenceEnum(Sequence Sequence){
        this.Sequence = Sequence;
    }

    public Sequence getSequence() {
        return Sequence;
    }
}
