<%@ page pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>login</title>
    <%@ include file="/common/common.jsp"%>
</head>
<body>
<div class="container">
    <form class="form-horizontal" role="form" id="frm" action="login.do" method="post">
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">表格展示</h3>
                </div>
                <div class="panel-body">
                    <table class="table table-hover" id="dg_info">
                        <caption align="center">信息列表</caption>
                        <thead>
                        <tr>
                            <th data-title="name">名称</th>
                            <th data-title="city">城市</th>
                            <th data-title="zipcode">邮编</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>

        <div class="col-sm-1"></div>
        <div class="col-sm-5">
            <div class="well">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="username" class="control-label col-sm-3">用户名:</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="control-label col-sm-3">密码:</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" id="password" name="password" />
                            </div>
                        </div>

                        <input type="button" class="btn" value="AJAX请求" onclick="fnHello();" />
                        <button type="submit" class="btn btn-primary">登陆</button>
                        <a href="<%=basePath%>download.do" >下载文件</a>
                        <a href="<%=basePath%>resumeDownload.do" >下载文件（支持断点续传）</a>

                        <h3 id="errorMsg">${errorMsg}</h3>
                    </div>
                </div>
            </div>
        </div>

    </form>
</div>
</body>
<script>
    /**
     * AJAX请求
     */
    function fnHello () {
        Hi.submit("frm", Hi.global.basePath + "hello.do", null, function (data) {
            if (data.success) {
                window.location.href = Hi.global.basePath + "user/listUser.do";
            } else {
                $("#errorMsg").text(data.message);
                $("#dg_info tbody").find("td").each(function() {
                    if ("name" == $(this).data("title") && "Uma" == $(this).text()) {
                        if (!$(this).parent("").hasClass("success")) $(this).parent("").addClass("success");
                    }
                });

            }
        });
    }

    function fnDownload() {
        Hi.submit("frm", Hi.global.basePath + "download.do", null);
    }
</script>
</html>
