function actionName(value, row, index) {
	return [
			'<div onclick="getName(this);">',
			value,
			'</div>'].join('');
}

function getName(temp){
	var hang = $(temp.parentNode).parent().prevAll().length+1;  //jquery获取td所在的行和列
	   //var lie = $(temp.parentNode).prevAll().length+1;  
	   //alert("第"+hang+"行"+"	"+"第"+lie+"列"); 

		 var val=document.getElementById("table").rows[hang].cells[0]; //取得所在列的值
	     var id=val.innerHTML; 
	     $.get("../../mobileConsume/getFoodId.do",{
	    	 id:id 
	      },function(data){
	    	  var foodId=data;
	    	  var url="../"+foodId+".html";
	    	  location.href=url;
	      });
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
   }


 var code=getQueryString("code");
 var state=getQueryString("state");

 /*
  * test类测试session是否存在，不存在就跳转回调ouath网址                                                                      
  * 存在就查询数据库里用户信息
  */
 
 $.get("/xProgram/oauth/test.do", { 
	 campusId:campusId
   }, function(data) {
	//   alert("data="+data);

  if(data=="false"){
	//   alert("false");
      if(code!=null){   	  
           $.get("/xProgram/oauth/getUserInfo.do", {
              code:code,
              state:state,
               campusId:campusId
            }, function(text) {

         	  var obj = $.parseJSON(text);
            
         	 $("#brand").html(obj.campusName);
         	 $(".headimgUrl").attr("src",obj.headimgUrl);
         	 $(".nickName").html(obj.nickName);
         	  
       	     /*
       	      * 此处需要两次执行相同的函数，否则回调后不能执行以下函数
       	      */    
 			  $("#table").bootstrapTable({
 				url : "/xProgram/mobileConsume/getAllConsume.do"
 			  });
 			  
            });
           
           }else{
        	   location.href=redirectUrl;
           }
   }else{
	 //  alert("session true");
	   $.get("/xProgram/oauth/getUserInfoByOpenId.do",{
		   campusId:campusId
	   },function(text) {
      	  var obj = $.parseJSON(text);

      	 $("#brand").html(obj.campusName);
      	 $(".headimgUrl").attr("src",obj.headimgUrl);
    	 $(".nickName").html(obj.nickName);
      	  
      	     /*
    	      * 此处需要两次执行相同的函数，否则返回ture时不能执行以下函数
    	      */  
      	  
			$("#table").bootstrapTable({
				url : "/xProgram/mobileConsume/getAllConsume.do"
			});
         
	   });
   }
     
});