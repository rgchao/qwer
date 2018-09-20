
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>My JSP 'duanxin.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
</head>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<style type="text/css">
</style>
<style type="text/css">
    label.error {
        COLOR: red;
    }
</style>
<script type="text/javascript">
    jQuery.validator.addMethod("checka",function(value){
        var reg=/^[0-9_]{11}$/;
        return reg.test(value);
    },"输入格式有误,请输入11位有效的手机号码！");
    $(document).ready(function() {
        jQuery("#regForm").validate({
            rules : {
                "phone" : {
                    required : true,
                    checka:true
                }
                ,"yanz" : {
                    required : true
                }
            },
            messages : {
                "phone" : {
                    required : "手机号不能为空",
                }
                ,"yanz" : {
                    required : "请输入验证码"
                }

            },
            errorElement : "label",
            highlight : function(element, errorClass)//针对验证的表单添加高亮显示
            {
                $(element).addClass(errorClass);
            },
            success : function(label) {
                label.html("").addClass("valid");
            }

        })

    })
</script>
<script type="text/javascript">
    function check(){
        $.ajax({
            url:"/duanxin",
            data:{"phone":$("#myphone").val()},
            dataType:"json",
            type:"post",
            //true 异步
            //false 同步
            async:false,
            success:function(data){
                if(!data.result){
                    alert("验证码发送失败");
                }else{
                    alert("验证码已发送，请注意查收");
                }
            }
        });
    }
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<body>
<form action="${pageContext.request.contextPath}/duanxin?method=yz" method="post"  id="regForm">
    请输入手机号：<input type="text" name="phone" value="${phone}"  id="myphone"><br>
    验     证     码：<input type="text" name="yanz" value="">  <input type="button" onclick="check()" value="点击获取验证码"></input><br>
    <input type="submit">
</form>
</body>
</html>
