<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.setAttribute("basePath", basePath);

%>
<meta name="content-type" content="text/html; charset=UTF-8 ">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">

<link type="text/css" rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="<%=basePath%>css/common.css" />

<script type="text/javascript">
    var Hi = {};
    Hi.global = {};
    Hi.global.contextPath = "<%=path%>";
    Hi.global.basePath = "<%=basePath%>";

</script>
<script type="text/javascript">
    function setCookie(name, value) {
        var Days = 30;  // 30天有效期
        var exp = new Date();
        exp.setTime(exp.getTime() + Days*24*60*60*1000);
        document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
    }
    function getCookie(cookieName) {
        var strCookie = document.cookie;
        var arrCookie = strCookie.split("; ");
        for(var i = 0; i < arrCookie.length; i++){
            var arr = arrCookie[i].split("=");
            if(cookieName == arr[0]){
                return arr[1];
            }
        }
        return "";
    }
</script>

<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>

<%@include file="init.jsp"%>



