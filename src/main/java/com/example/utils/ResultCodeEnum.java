package com.example.utils;

/**
 * ${DESCRIPTION}
 *
 * @auther skyer
 * @data 2017/7/26 9:03
 * @project slms
 */
public enum ResultCodeEnum {
    SUCCESS("操作成功", 88888888),SAVE_ERROR("保存失败", 20000001),UPDATE_ERROR("更新失败", 20000002),REPEAT_ERROR("数据已存在", 20000003),DATA_CHANGE_ERROR("数据已改变", 20000004);
    private String name;
    private int index;
    // 构造方法
    private ResultCodeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ResultCodeEnum c : ResultCodeEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
