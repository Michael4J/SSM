<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>list</title>
    <%@ include file="/common/common.jsp"%>
</head>
<body>
<div class="container">
    <form id="frm">
        <div class="jumbotron">
            <h1>Hello</h1>
            <p id="username">${login_user.username}</p>
        </div>

        <div class="table-responsive">
            <table class="table table-striped table-hover" id="dg_info">
                <caption align="center">信息列表</caption>
                <thead>
                <tr>
                    <th data-title="name">名称</th>
                    <th data-title="city">城市</th>
                    <th data-title="zipcode">邮编</th>
                    <th data-title="zipcode">邮编</th>
                    <th data-title="zipcode">邮编</th>
                    <th data-title="zipcode">邮编</th>
                    <th data-title="zipcode">邮编</th>
                    <th data-title="zipcode">邮编</th>
                    <th data-title="zipcode">邮编</th>
                </tr>
                </thead>
            </table>
        </div>
    </form>

</div>

</body>
<script>

</script>
</html>
