package com.hello.interceptor;

import com.hello.common.Constant;
import com.hello.common.IResultBean;
import com.hello.common.bean.ResultBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 返回前台数据组装拦截器
 * @Title: ResultBeanInterceptor
 * @Auther: Michael
 * @Date: 2019/1/15
 */
public class ResultBeanInterceptor implements HandlerInterceptor {
    /**
     * 预处理回调
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        IResultBean resultBean = new ResultBean();
        request.setAttribute(Constant.RESULT_BEAN, resultBean);

        return true;
    }

    /**
     * 后处理回调
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求完回调
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
