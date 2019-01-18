package com.hello.ssm.controller;

import com.hello.common.controller.BaseController;
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
 * @Title: UserAction
 * @Auther: Michael
 * @Date: 2018/11/15
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private IUserService userService;

    @RequestMapping("listUser")
    public String list(HttpServletRequest request, HttpServletResponse response) {

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

        return "user/list";
    }
}
