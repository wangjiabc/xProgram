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
	   
      if(code!=null){   	  
           $.get("/xProgram/oauth/getUserInfo.do", {
              code:code,
              state:state,
               campusId:campusId
            }, function(text) {

         	  var obj = $.parseJSON(text);
         	 /*
        	   * 此处需要两次执行相同的函数，否则回调后不能执行以下函数
        	  */
         	  /*
             $("#openid").html(obj.openId);
              $("#nickname").html(obj.nickName);
             $("#sex").html(obj.sex);
             $("#country").html(obj.country);
             $("#province").html(obj.province);
            $("#city").html(obj.city);
            $("#subscribe").html(obj.subscribe);
            $("#subscribeTime").html(obj.subscribeTime); 
            $("#language").html(obj.language);
             $("#headimgUrl").html("<img src='"+obj.headimgUrl+"'  width='80px' height='auto'>");
             */
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
      	/*
 	      * 此处需要两次执行相同的函数，否则返回ture时不能执行以下函数
 	      */ 
      	  /*
         $("#openid").html(obj.openId);
           $("#nickname").html(obj.nickName);
          $("#sex").html(obj.sex);
          $("#country").html(obj.country);
          $("#province").html(obj.province);
         $("#city").html(obj.city);
         $("#subscribe").html(obj.subscribe);
         $("#subscribeTime").html(obj.subscribeTime); 
         $("#language").html(obj.language);
          $("#headimgUrl").html("<img src='"+obj.headimgUrl+"'  width='80px' height='auto'>");
         */
         
	   });
   }
     
});