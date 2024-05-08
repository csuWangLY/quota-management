package com.project.quotamanagement.quotamanagement.controller;

public interface ControllerTemplate {
    /**
     * 检查
     */
    void check() throws Exception;

    /**
     * 执行
     */
    void execute() throws Exception;
}
