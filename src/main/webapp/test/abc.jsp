<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String wsPath = "ws://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<title>Insert title here</title>
</head>
<body>
普通商户
<jsp:useBean id="clock" class="java.util.Date"/>
<c:out value="${5+9 }" />
<c:choose>
 <c:when test="${clock.hours<12}">
  <h1>Good morning</h1>
 </c:when>
 <c:when test="${clock.hours<18}">
  <h1>Good day</h1>
 </c:when>
 <c:otherwise>
  <h1>Good evening</h1>
 </c:otherwise>
</c:choose>
<div id="message"></div>
</body>
<div class="container">
    <form action="" class="form-horizontal"  role="form">
        <fieldset>
            <legend>Test</legend>
            <div class="form-group">
                <label for="dtp_input1" class="col-md-2 control-label">DateTime Picking</label>
                <div class="input-group date form_datetime col-md-5" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                    <input class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
				<input type="hidden" id="dtp_input1" value="" /><br/>
            </div>
        </fieldset>
    </form>
</div>

<div style="width:970px; margin:10px auto;">
    演示一：<input placeholder="请输入日期" class="laydate-icon" onclick="laydate()">
</div>

<script src="../laydate/laydate.js"></script>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type='text/javascript' src="../js/jquery.js" ></script>
<script type="text/javascript" src="../bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript">
var date = new Date();
Y = date.getFullYear() + '-';
M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
D = date.getDate() + ' ';
h = date.getHours() + ':';
m = date.getMinutes() + ':';
s = date.getSeconds(); 
console.log(Y+M+D+h+m+s)  

    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	$('.form_date').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	$('.form_time').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
    });
</script>

<script type="text/javascript">
 $.post("/xProgram/test/aaa.do",
		 function(data){
	    $('#message').html(data);
 });
</script>
</html>