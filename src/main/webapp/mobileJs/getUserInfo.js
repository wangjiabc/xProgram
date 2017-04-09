function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
   }

function getcookie(name){ 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]); 
    else 
        return null; 
  }

 var campusId;
 var code=getQueryString("code");
 var state=getQueryString("state");
 var APPID=getQueryString("appId");
 
 var REDIRECT_URI="http://"+location.host+"/xProgram/mobile/getUserInfo.html";

 
//微信网页授权回调链接
 var redirectUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri="+REDIRECT_URI+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

 /*
  * test类测试session是否存在，不存在就跳转回调ouath网址                                                                      
  * 存在就查询数据库里用户信息
  */
 if(code!=null){
	// alert(code);
	 $.ajax({
	        type:'POST',
	        url:'/xProgram/mobileConsume/getCampusId.do',
	        dataType:'json',
	        async:false,
	        success:function(data){
	        	result=data;
	        }
	    });
	 
	  campusId=result;
     
	  $.ajax({
	        type:'POST',
	        url:'/xProgram/oauth/getUserInfo.do',
	        dataType:'json',
	        data:{code:code,state:state,campusId:campusId},
	        async:false,
	        success:function(data){
	        	var obj = $.parseJSON(data);
	        }
	    });
	  
    }else{
	
   // 	alert(redirectUrl);
    	
    	location.href=redirectUrl;
    	 
    }
     
     