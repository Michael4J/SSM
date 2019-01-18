<%@page language="java" pageEncoding="UTF-8" %>
<%@page import="com.hello.common.Constant" %>
<%@page import="com.hello.common.IResultBean" %>
<%
    response.setContentType("text/json; charset=UTF-8");    // 解决从服务器返回中文乱码问题
    IResultBean resultBean = (IResultBean) request.getAttribute(Constant.RESULT_BEAN);
    if (null != resultBean) {
        out.print(resultBean.toJSON());
    }
    out.flush();
%>

