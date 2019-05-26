package com.aries.canis.model.vo;

public class QuestionDifficultyVO {
    private Integer code;
    private String level;

    public QuestionDifficultyVO() {

    }

    public QuestionDifficultyVO(Integer code, String level) {
        this.code = code;
        this.level = level;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
