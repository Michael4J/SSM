package com.hello.common.bean;

import com.alibaba.druid.support.json.JSONUtils;
import com.hello.common.IResultBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回前台数据封装
 * @Title: ResultBean
 * @Auther: Michael
 * @Date: 2019/1/15
 */
public class ResultBean implements IResultBean {
    private boolean success = true;
    private String message = "";
    private Map<String, Object> fields = new HashMap<>();
    private Map<String, List<?>> lists = new HashMap<>();
    private Map<String, Object> resultBean = new HashMap<>();

    public boolean getSuccess() {
        return this.success;
    }

    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Map<String, Object> getFields() {
        return this.fields;
    }

    @Override
    public Object getFieldValue(String key) {
        return this.fields.get(key);
    }

    @Override
    public void setFieldValue(String key, Object value) {
        this.fields.put(key, value);
    }

    @Override
    public Map<String, List<?>> getLists() {
        return this.lists;
    }

    @Override
    public List<?> getListData(String key) {
        return this.lists.get(key);
    }

    @Override
    public void setListData(String key, List<?> list) {
        this.lists.put(key, list);
    }

    @Override
    public String toJSON() {
        Map map = new HashMap();
        resultBean.put("success", this.getSuccess());
        resultBean.put("message", this.getMessage());
        resultBean.put("fields", this.getFields());
        resultBean.put("lists", this.getLists());

        return JSONUtils.toJSONString(resultBean);
    }
}
