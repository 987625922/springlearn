<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery
/jquery-1.4.min.js"></script>
    <!-- 引入 Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <script type="text/javascript">
        $(document).ready(function () {
            $("button").click(function () {
                $.ajax({
                    type: 'get',
                    url: '/ajaxget',
                    dataType: 'json',
                    async: false,
                    complete: function (data) {
                        if (data != undefined && data.responseText != undefined && data.responseText != "") {
                            // jq ajax 请求到数据
                            var objStr = JSON.stringify(JSON.parse(data.responseText), null, 2)
                            console.log(objStr)
                            var obj = JSON.parse(data.responseText)
                            console.log(obj.book_id)

                            // myArr = JSON.parse(this.responseText);
                            // document.getElementById("demo").innerHTML = myArr[1];
                        }
                    }
                });

            });
        });
    </script>
</head>
<body>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<button type="button" class="btn btn-default">向页面发送 HTTP GET 请求，然后获得返回的结果</button>

</body>
