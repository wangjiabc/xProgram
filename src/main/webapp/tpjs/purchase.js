 $("#buybtn").click(function(){
	 $.post("/xProgram/mobilePurchase/action.do", {
         foodId:foodId,
         campusAdmin:campusAdmin,
         amount:1
	}, function(data) {
		/*
	    var text=data.replace(/"/g,""); //去掉字符串的"号	 
	    var matche=/redirect/g;
	    */
         foodName=$(document).attr("title");
         price=$(".price").text();

		   //判断服务器传递参数是否需要跳转到购买页面
	    if(data==5){
	    	 Url="http://"+location.host+"/xProgram/mobile/pay.html?foodId="+
	    	 foodId+"&foodName="+foodName+"&price="+price;
	         location.href=Url;
	    }
	    
		if(data==1){
			$('#toast').show();
		     setTimeout(function () {
		         $('#toast').hide();
		         location.href="/xProgram/mobile/home.html"; //购买完成后跳转到个人主页
		     }, 2000);
		}else if(data==0){
			$("#msgWarn").attr("style","display:block");
			$(".mainBody").attr("style","display:none");
			$(".weui_msg_title").html("购买失败");
			$(".weui_msg_desc").html("此商品的购买时间还没开始！");
		}else if(data==2){
			$("#msgWarn").attr("style","display:block");
			$(".mainBody").attr("style","display:none");
			$(".weui_msg_title").html("购买失败");
			$(".weui_msg_desc").html("此商品的购买时间已结束！");
		}else if(data==3){
			$("#msgWarn").attr("style","display:block");
			$(".mainBody").attr("style","display:none");
			$(".weui_msg_title").html("购买失败");
			$(".weui_msg_desc").html("商品未通过审核！");
		}
      
	   $.post("../mobileService/getFoodCountById.do", {
           foodId:foodId
		}, function(text) {
		   $("#count").html(text);
		});  
	 }); 
  });
 
 $(".weui_btn_primary").click(function(){
		$("#msgWarn").attr("style","display:none");
		$(".mainBody").attr("style","display:block");
		
	});
