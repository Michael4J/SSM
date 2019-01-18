package com.hello.ssm.controller;

import com.hello.common.controller.BaseController;
import com.hello.ssm.entity.User;
import com.hello.ssm.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: Base
 * @Auther: Michael
 * @Date: 2018/11/22
 */
@Controller
public class IndexController extends BaseController {

    @Resource
    private IUserService userService;

    @RequestMapping("toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {

        setValue("username", "mike");

        return "user/login";
    }

    @RequestMapping("login")
    public String login(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(username, password);
        if (null == user) {
            request.setAttribute("errorMsg", "用户名或密码错误！");
            return "user/login";
        }
        request.getSession().setAttribute("login_user", user);
        return "redirect:user/listUser.do";
    }

    @RequestMapping("hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        setValue("password", "1");
        List list = new ArrayList<>();
        Map map = new HashMap<>();
        map.put("name", "Tanmay");
        map.put("city", "Bangalore");
        map.put("zipcode", "560001");
        Map map1 = new HashMap<>();
        map1.put("name", "Sachin");
        map1.put("city", "Mumbai");
        map1.put("zipcode", "400003");
        Map map2 = new HashMap<>();
        map2.put("name", "Uma");
        map2.put("city", "Pune");
        map2.put("zipcode", "411027");
        list.add(map);
        list.add(map1);
        list.add(map2);

        setList("dg_info", list);

        User user = userService.login(username, password);
        if (null == user) {
            setSuccess(false);
            setMessage("用户名或密码错误！");

            return AJAX;
        }
        setSuccess(true);
        request.getSession().setAttribute("login_user", user);

        return AJAX;
    }

    @RequestMapping("unauthorized")
    public String unauthorized() {

        return "user/unauthorized";
    }

}
