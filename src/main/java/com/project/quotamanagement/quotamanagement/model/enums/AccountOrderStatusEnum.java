package com.project.quotamanagement.quotamanagement.model.enums;

public enum AccountOrderStatusEnum implements BaseEnum {
    SUCCESS("SUCCESS", "成功"),


    FAIL("FAIL", "失败"),
    ;

    /**
     * code
     */
    private String code;

    /**
     * description
     */
    private String description;


    AccountOrderStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
