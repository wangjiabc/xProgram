<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>comet</title> 
<body>
	    <div id="msg" value="0"></div>
	    <div id="console"></div>
	</body>  
	<%  //这里是为了模拟一个登陆用户，访问该页时，传一个userId=t1或userId=t2,
        //则认为是t1或t2登录，后台将不同的消息分别推送给t1和t2
        String userId = "t4";  
        session.setAttribute("currentUserId", userId);  
    %>
<script type="text/javascript">  
   
var consoleDiv = document.getElementById("console");
var msgDiv = document.getElementById("msg");
if(typeof(EventSource) !== "undefined") {
  var evtSource = new EventSource("/xProgram/sse");

  evtSource.addEventListener("ping", function(e){
	  var obj = JSON.parse(e.data);
	    var total=document.getElementById("msg").attributes["value"];
	    var i=parseInt(total["value"]);
	    i++
	    console.log("total="+i);
	    document.getElementById("msg").setAttribute("value",i); 
	    appendText(msgDiv,"total="+i+"   i="+obj.i+ "  j="+obj.j+ "  ping at " + obj.time + ", welcome to " + obj.message
	    		    +",currentUserId "+obj.currentUserId+"<br />");
	    console.log(obj.currentUserId+"         "+e);
  }, false);

  evtSource.addEventListener("open", function(e) {
    // Connection was opened.
    appendText(consoleDiv, "Connection was opened.");
  }, false);

  evtSource.addEventListener("error", function(e) {
    if (event.readyState == EventSource.CLOSED) {
      // Connection was closed.
      appendText(consoleDiv, "Error occurred");
    }
  }, false);
  
  evtSource.addEventListener("close", function(e) {

  }, false);
  
} else {
  appendText(consoleDiv, "Your browser does not support EventSource.");
}

function appendText(obj, text) {
  obj.innerHTML = obj.innerHTML + "<br/>"  + text;
}

</script>  

</html> 