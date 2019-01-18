package com.hello.common;

import java.util.List;
import java.util.Map;

/**
 * @Title: IResultBean
 * @Auther: Michael
 * @Date: 2019/1/15
 */
public interface IResultBean {
    String RESULT_BEAN = Constant.RESULT_BEAN;

    /**
     * 成功返回是否
     * @param success
     */
    void setSuccess(boolean success);

    /**
     * 获取返回信息
     * @return
     */
    String getMessage();

    /**
     * 设置返回信息
     * @param message
     */
    void setMessage(String message);

    /**
     * 获取所有字段数据
     * @return
     */
    Map<String, Object> getFields();

    /**
     * 获取字段值
     * @param key
     * @return
     */
    Object getFieldValue(String key);

    /**
     * 设置字段值
     * @param key
     * @param value
     */
    void setFieldValue(String key, Object value);

    /**
     * 获取所有集合数据
     * @return
     */
    Map<String, List<?>> getLists();

    /**
     * 获取集合数据
     * @param key
     * @return
     */
    List<?> getListData(String key);

    /**
     * 设置集合数据
     * @param key
     * @param list
     */
    void setListData(String key, List<?> list);

    /**
     * 转JSON
     * @return
     */
    String toJSON();

}
