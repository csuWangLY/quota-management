package com.project.quotamanagement.quotamanagement.model.enums;

public enum QuotaAccountStatusEnum implements BaseEnum {

    EFFECTIVE("EFFECTIVE", "生效"),


    FROZEN("FROZEN", "冻结"),
    ;

    /**
     * code
     */
    private String code;

    /**
     * description
     */
    private String description;


    QuotaAccountStatusEnum(String code, String description) {
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
