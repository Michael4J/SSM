package com.hello.common;

import java.util.List;
import java.util.Map;

/**
 * @Title: IBaseController
 * @Auther: Michael
 * @Date: 2019/1/15
 */
public interface IBaseController {
    String AJAX = "ajax";

    IResultBean getResultBean();

    IResultBean setSuccess(Boolean success);

    IResultBean setMessage(String message);

    IResultBean setValue(String key, Object value);

    IResultBean setMap(Map<String, Object> map, boolean clear);

    IResultBean setList(String key, List<?> list);

}
