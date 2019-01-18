
var Hi = window.Hi = window.Hi || {};

(function(factory) {
    factory(jQuery);
})(function($) {
    $.extend(true, window, {Hi: core()});
    Hi.init();

    function core() {
        function init() {
            console.log("init Hi");
        }
        function submit(formId, url, param, success, error) {
            // 构建提交数据
            var data;
            if (undefined != formId && null != formId && "" != formId) {
                data = $('#' + formId).serialize();
            }
            if (undefined != param && null != param && "" != param) {
                data = $.extend({}, data, param);
            }

            $.ajax({
                url: url,
                data: data,
                type: 'POST',   // 默认POST提交方式
                dataType: 'json',
                contextType: 'application/json; charset=utf-8',
                success: function(data) {
                    var fields = data.fields;
                    if (undefined != fields && null != fields) {
                        for(var key in fields) {
                            $("#" + key).val(fields[key]);
                        }
                    }
                    var lists = data.lists;
                    if (undefined != lists && null != lists) {
                        for(var key in lists) {
                            var listData = lists[key];
                            var $table = $("#" + key);
                            if ($table) {
                                if (0 == $table.find("tbody").length) $table.append("<tbody></tbody>");
                                var tbodyData = "";
                                for (var i=0;i<listData.length;i++ ) {
                                    var row = listData[i];
                                    tbodyData += "<tr>";
                                    $("#" + key).find("thead tr th").each(function(i, e) {
                                        var title = $(this).data("title");
                                        tbodyData += "<td data-title='"+ title +"'>" + row[title] + "</td>";
                                    });
                                    tbodyData += "</tr>";
                                }
                                $table.children("tbody").empty().append(tbodyData);
                            }
                        }
                    }
                    if (success) success(data);
                },
                error: error
            });
        }
        return {
            init: init,
            submit: submit
        };
    }
});