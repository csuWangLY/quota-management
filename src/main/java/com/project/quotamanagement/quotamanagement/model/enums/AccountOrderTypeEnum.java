package com.project.quotamanagement.quotamanagement.model.enums;

public enum AccountOrderTypeEnum implements BaseEnum {
    RELEASE("RELEASE", "占用"),


    OCCUPIED("OCCUPIED", "释放 "),
    ;

    /**
     * code
     */
    private String code;

    /**
     * description
     */
    private String description;


    AccountOrderTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
