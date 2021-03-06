package com.aries.canis.enums;

public enum QuestionTypeEnum {
    SelectionType(0, "选择题"),
    FillingType(1, "填空题");

    private Integer id;
    private String typeName;

    QuestionTypeEnum(Integer id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public Integer getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public static String getTypeName(Integer id) {
        switch (id) {
            case 0: {
                return SelectionType.getTypeName();
            }
            case 1: {
                return FillingType.getTypeName();
            }
            default: {
                return null;
            }
        }

    }


}
