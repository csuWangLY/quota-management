package com.project.quotamanagement.quotamanagement.model.enums;


public enum QuotaAccountTypeEnum  implements BaseEnum {
    TYPE_1("TYPE_1", "TYPE_1"),


    TYPE_2("TYPE_2", "TYPE_2"),


    TYPE_3("TYPE_3", "TYPE_3"),


    TYPE_4("TYPE_4", "TYPE_4"),


    TYPE_5("TYPE_5", "TYPE_5"),
    ;

    /**
     * code
     */
    private String code;

    /**
     * description
     */
    private String description;

    public static QuotaAccountTypeEnum getByCode(String code) {
        for (QuotaAccountTypeEnum accountTypeEnum : values()) {
            if (accountTypeEnum.equals(code)) {
                return accountTypeEnum;
            }
        }

        return null;
    }


    QuotaAccountTypeEnum(String code, String description) {
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
