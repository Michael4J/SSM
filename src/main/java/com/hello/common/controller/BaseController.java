package com.hello.common.controller;

import com.hello.common.Constant;
import com.hello.common.IBaseController;
import com.hello.common.IResultBean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Title: BaseController
 * @Auther: Michael
 * @Date: 2019/1/15
 */
public class BaseController implements IBaseController{


    @Override
    public IResultBean getResultBean() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (IResultBean) request.getAttribute(Constant.RESULT_BEAN);
    }

    @Override
    public IResultBean setSuccess(Boolean success) {
        IResultBean resultBean = this.getResultBean();
        resultBean.setSuccess(success);
        return resultBean;
    }

    @Override
    public IResultBean setMessage(String message) {
        IResultBean resultBean = this.getResultBean();
        resultBean.setMessage(message);
        return resultBean;
    }

    @Override
    public IResultBean setValue(String key, Object value) {
        IResultBean resultBean = this.getResultBean();
        resultBean.setFieldValue(key,value);
        return resultBean;
    }

    @Override
    public IResultBean setMap(Map<String, Object> map, boolean clear) {
        IResultBean resultBean = this.getResultBean();
        for(Map.Entry<String, Object> entry: map.entrySet()) {
            if (clear || null == resultBean.getFieldValue(entry.getKey())) {
                resultBean.setFieldValue(entry.getKey(), entry.getValue());
            }
        }
        return resultBean;
    }

    @Override
    public IResultBean setList(String key, List<?> list) {
        IResultBean resultBean = this.getResultBean();
        resultBean.setListData(key, list);
        return resultBean;
    }

}
