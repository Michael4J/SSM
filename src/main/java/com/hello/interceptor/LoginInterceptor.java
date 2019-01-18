package com.hello.interceptor;

import com.hello.ssm.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 * @Title: LoginInterceptor
 * @Auther: Michael
 * @Date: 2018/11/22
 */
public class LoginInterceptor implements HandlerInterceptor {

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

        HttpSession session = request.getSession();
        String servletPath = request.getServletPath();

        // 放行这些请求
//        if (null == servletPath || servletPath.indexOf("toLogin") > 0 || servletPath.indexOf("login") > 0
//                || servletPath.indexOf("unauthorized.do") > 0) {
//            return true;
//        }

        // 查看session里是否有用户信息
        User user = (User) session.getAttribute("login_user");
        if (null == user) {
            response.sendRedirect(request.getContextPath() + "/toLogin.do");
            return false;
        }

        // 权限控制
        Boolean permission  = true;
        if (!permission) {
            if (null == request.getHeader("X-Requested-With")) {    // 非AJAX请求
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("<script language=\"javascript\">" +
                        "alert(\"没有操作权限!\");" +
                        "window.location.href=\"" + request.getContextPath() + "/unauthorized.do\"" +
                        "</script>");
//                response.sendRedirect(request.getContextPath() + "/unauthorized.do");
            } else {
                response.getWriter().write("{\"success\":false,\"message\":\"没有操作权限!\"}");
            }
            return false;
        }
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
