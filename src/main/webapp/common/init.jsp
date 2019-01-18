<%@page language="java" pageEncoding="UTF-8" %>
<%@page import="com.hello.common.Constant" %>
<%@page import="com.hello.common.IResultBean" %>
<%
    // 获取传过来的数据
    IResultBean resultBean = (IResultBean) request.getAttribute(Constant.RESULT_BEAN);
    String result = "";
    if (null != resultBean) {
        result = resultBean.toJSON();
    }
%>
<script type="text/javascript">
    window.onload = function() {
        var data = eval(<%=result%>);
        if (undefined != data && null != data && "" != data) {
            // 字段赋值
            var fields = data.fields;
            if (undefined != fields && null != fields) {
                for(var key in fields) {
                    if (document.getElementById(key)) document.getElementById(key).value = fields[key];
                }
            }
            var lists = data.lists;
            if (undefined != lists && null != lists) {
                for(var key in lists) {
                    var listData = lists[key];
                    var table = document.getElementById(key);
                    if (table) {
                        var headers = table.getElementsByTagName("thead")[0].getElementsByTagName("tr")[0].getElementsByTagName("th");
                        var tbody = document.createElement("tbody");
                        for (var i=0;i<listData.length;i++ ) {
                            var row = listData[i];
                            var tr = document.createElement("tr");
                            for(var j=0;j<headers.length;j++) {
                                var title = headers[j].getAttribute("data-title");
                                var td = document.createElement("td");
                                td.dataset.title = title;
                                td.innerHTML = row[title];
                                tr.appendChild(td);
                            }
                            tbody.appendChild(tr);
                        }
                        table.appendChild(tbody);
                    }
                }
            }
        }
    }
</script>



