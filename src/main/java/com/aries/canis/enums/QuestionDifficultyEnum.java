package com.aries.canis.enums;

import java.util.ArrayList;
import java.util.List;

public enum QuestionDifficultyEnum {
    JIANDAN(1, "简单"),
    ZHENGCHANG(2, "正常"),
    ZHONGDENG(3, "中等"),
    KUNNAN(4, "困难"),
    TENAN(5, "特难");
    private Integer code;
    private String level;

    QuestionDifficultyEnum(Integer code, String level) {
        this.code = code;
        this.level = level;
    }

    public Integer getCode() {
        return code;
    }

    public String getLevel() {
        return level;
    }

    public static String getLevel(Integer code) {
        switch (code) {
            case 1:
                return JIANDAN.getLevel();
            case 2:
                return ZHENGCHANG.getLevel();
            case 3:
                return ZHONGDENG.getLevel();
            case 4:
                return KUNNAN.getLevel();
            case 5:
                return TENAN.getLevel();
            default:
                return null;
        }
    }

    public static List<Integer> getDifficultyList() {
        List<Integer> list = new ArrayList<>();
        list.add(JIANDAN.code);
        list.add(ZHENGCHANG.code);
        list.add(ZHONGDENG.code);
        list.add(KUNNAN.code);
        list.add(TENAN.code);
        return list;
    }
}
