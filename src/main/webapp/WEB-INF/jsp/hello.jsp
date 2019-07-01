<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery
/jquery-1.4.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("button").click(function () {
                // $.get("/ajaxget",false, function (data, status) {
                //     if (data != undefined && data.responseText != undefined
                //         && data.responseText != "") {
                //         alert("数据：" + data)
                //     } else {
                //         alert("数据：" + data.responseText + "\n状态：" + status);
                //     }
                // });
                $.ajax({
                    type:'get',
                    url:'/ajaxget',
                    dataType: 'json',
                    async:false,
                    complete:function(data) {
                        if(data!=undefined && data.responseText !=undefined && data.responseText !=""){
                            // jq ajax 请求到数据
                            var objStr = JSON.stringify(JSON.parse(data.responseText),null,2)
                            console.log(objStr)
                            var obj = JSON.parse(data.responseText)
                            console.log(obj.book_id)
                        }
                    }
                });

            });
        });
    </script>
</head>
<body>

<button>向页面发送 HTTP GET 请求，然后获得返回的结果</button>

</body>
