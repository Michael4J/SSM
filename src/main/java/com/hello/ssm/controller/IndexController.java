package com.hello.ssm.controller;

import com.hello.common.controller.BaseController;
import com.hello.ssm.entity.User;
import com.hello.ssm.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    Logger logger = LoggerFactory.getLogger(IndexController.class);

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

    /**
     * 普通下载
     * @param request
     * @param response
     */
    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        String filePath = "G:\\迅雷下载\\ideaIU-2018.2.7.exe";

        File file = new File(filePath);
        String fileName = file.getName();

        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int length = 0;
            while ((length = bis.read(buff)) != -1) {
                os.write(buff, 0, length);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return;

    }
    /**
     * 下载 （支持浏览器断点续传下载）
     * @param request
     * @param response
     */
    @RequestMapping("resumeDownload")
    public void resumeDownload(HttpServletRequest request, HttpServletResponse response) {
        String filePath = "G:\\迅雷下载\\ideaIU-2018.2.7.exe";

        FileInputStream inputFileStream = null;
        OutputStream outputStream = null;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                long point = 0;
                long fileLength = file.length();
                long position_end = fileLength;

                inputFileStream = new FileInputStream(file);
                outputStream = response.getOutputStream();
                response.reset();

                // 获取断点位置
                String Range = request.getHeader("Range");
                if (Range != null) {
                    response.setStatus(response.SC_PARTIAL_CONTENT);
                    String RangeFromTo = Range.replaceAll("bytes=", "");
                    logger.info("threadName: " + Thread.currentThread().getId() + " Range : " + RangeFromTo);

                    String[] Ranges = RangeFromTo.split("-");
                    point = Long.parseLong(Ranges[0]);

                    if (Ranges.length == 2 && !Ranges[1].equals("")) {
                        position_end = Long.parseLong(Ranges[1]);
                    }
                }

                // 设置支持断点
                response.setHeader("Accept-Ranges", "bytes");

                //写明要下载的文件的大小
                response.setHeader("Content-Length", new Long(position_end - point).toString());
                logger.info("threadName: " + Thread.currentThread().getId() + " point : " + point + " Content-Length : " + new Long(position_end - point).toString());

                //设置response的编码方式
                response.setContentType("application/x-download");

                //设置附加文件名(解决中文乱码)
                String fileName = file.getName();
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));

                //设置断点续传回应头
                String contentRange = new StringBuffer("bytes ")
                        .append(new Long(point).toString())
                        .append("-")
                        .append(new Long(fileLength - 1).toString())
                        .append("/")
                        .append(new Long(fileLength).toString())
                        .toString();

                if (point != 0) {
                    // 断点续传的回应头：告诉改块插入的位置和文件的总大小
                    // 格式：Content-Range: bytes [文件块的开始字节]-[文件块的结束字节 - 1]/[文件的总大小]
                    contentRange = new StringBuffer("bytes ")
                            .append(new Long(point).toString())
                            .append("-")
                            .append(new Long(position_end - 1).toString())
                            .append("/")
                            .append(new Long(fileLength).toString())
                            .toString();
                    // 移动文件指针位置，断点处
                    inputFileStream.skip(point);
                }
                response.setHeader("Content-Range", contentRange);

                long bytesWritten = 0;
                byte[] bytes = new byte[1024 * 4];
                int byteCount = 0;

                long NeedWriten = position_end - point + 1;

                while (NeedWriten >= bytesWritten && (byteCount = inputFileStream.read(bytes)) != -1) {
                    if (NeedWriten >= bytesWritten) {
                        long tTempWriten = (bytesWritten + byteCount) > NeedWriten ? (NeedWriten - bytesWritten) : byteCount;
                        outputStream.write(bytes, 0, (int) tTempWriten);
                        bytesWritten += tTempWriten;
                    }
                }
            } else {
                logger.error("下载文件失败：" + filePath + " and authcode: 文件不存在");
                setHttpResposeCode(response, response.SC_NOT_FOUND, "download file failed ：" + filePath + " : file not exist");
                return;
            }

        } catch (FileNotFoundException e) {
            logger.error("下载文件失败：" + filePath + " FileNotFoundException happen : " + e + " URL : " + request.getRequestURL().toString());
            setHttpResposeCode(response, response.SC_NOT_FOUND, "download file failed FileNotFoundException happen : " + e + " filePath :" + filePath + " : file not exist");
            return;
        } catch (IOException e) {
            // 可以忽略这个异常，有可能是用户暂停下载，或者迅雷等下载工具分块下载
        } finally {
            try {
                if (null != inputFileStream) {
                    inputFileStream.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
                logger.error("下载文件失败：" + filePath + " EofException happen : " + e);
            }
        }
        return;
    }

    /**
     * 设置http 返回码
     * @param code
     * @param msg
     **/
    private void setHttpResposeCode(HttpServletResponse response, int code, String msg) {
        try {
            response.reset();
            response.setCharacterEncoding("UTF-8");               //设置字符编码格式
            response.setContentType("text/html; charset=UTF-8"); //设置输出编码格式
            response.sendError(code, new String(msg.getBytes(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("FileDownLoad setHttpResposeCode IOException happen : " + e.getMessage());
        }
    }

}
