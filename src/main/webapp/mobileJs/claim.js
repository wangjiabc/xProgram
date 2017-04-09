var state;
var campusAdmin;
var id;
var openId;
var foodId;
var amount;
var foodName;

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
	     $.get("../mobileConsume/getFoodId.do",{
	    	 id:id 
	      },function(data){
	    	  var foodId=data;
	    	  var url="../mobile/"+foodId+".html";
	    	  location.href=url;
	      });
}

function actionClaim(value, row, index) {
    if(value==0){
    	return [
    			'<div  title="未消费">',
    			'<img src="../img/mobileRight.jpg" width="15px" height="15px">',
    			'</div>'].join('');
    }else{
    	return [
    			'<div  title="已消费" style="color:red;font-size:10px">',
    			'<img src="../img/mobileError.jpg" width="15px" height="15px">',
    			'</div>'].join('');
    }
    	

}


function actionRefund(value, row, index) {
	return [
			'<div onclick="detail(this);" title="点击退款">',
			'&nbsp;<img src="../img/refund.jpg" width="25px" height="25px">&nbsp;',
			'</div>'].join('');
}
 
function detail(temp){
	var hang = $(temp.parentNode).parent().prevAll().length+1;  //jquery获取td所在的行和列
	   //var lie = $(temp.parentNode).prevAll().length+1;  
	   //alert("第"+hang+"行"+"	"+"第"+lie+"列"); 

		 var val=document.getElementById("table").rows[hang].cells[0]; //取得所在列的值
	     var id=val.innerHTML; 

	     $.post("../mobilePay/refundment.do",{
	 		id:id
	 	},function(data){
	 		$('#loadingToast').hide();
	 		if(data==1){
	 			$("#dialog1").attr("style","display:none"); 
	 			$("#msgSuccess").attr("style","display:block");
	 			$(".mainElement").attr("style","display:none");
	 			$(".weui_msg_title").html("操作成功");
	 			$(".weui_msg_desc").html("退款申请已提交!");
	 		}else if(data==2){
	 			$("#dialog1").attr("style","display:none"); 
				$("#msgWarn").attr("style","display:block");
				$(".mainElement").attr("style","display:none");
				$(".weui_msg_title").html("操作失败");
				$(".weui_msg_desc").html("已消费的商品不能申请退款!");
	 		}else if(data==3){
	 			$("#dialog1").attr("style","display:none"); 
	 			$("#msgWarn").attr("style","display:block");
	 			$(".mainElement").attr("style","display:none");
	 			$(".weui_msg_title").html("消费失败");
	 			$(".weui_msg_desc").html("系统超时，请重试!");
	 		}else if(data==4){
	 			$("#dialog1").attr("style","display:none"); 
	 			$("#msgWarn").attr("style","display:block");
	 			$(".mainElement").attr("style","display:none");
	 			$(".weui_msg_title").html("消费失败");
	 			$(".weui_msg_desc").html("管理员已取消申请!");
	 		}else if(data==0){
	 			$("#dialog1").attr("style","display:none"); 
	 			$("#msgWarn").attr("style","display:block");
	 			$(".mainElement").attr("style","display:none");
	 			$(".weui_msg_title").html("操作失败");
	 			$(".weui_msg_desc").html("0元商品不能申请退款!");
	 		}
	 	});
		 
}

$(".default").click(function(){
	 $("#dialog1").attr("style","display:none"); 
	 $("#table").bootstrapTable({
			url : "/xProgram/mobileConsume/getAllConsume.do"
		  });
});

$(".primary").on("click",function(){
	$("#dialog1").attr("style","display:none");
	$('#loadingToast').show();
	$.post("../mobileConsume/affirm.do",{
		id:id
	},function(data){
		$('#loadingToast').hide();
		if(data==1){
			$("#dialog1").attr("style","display:none"); 
			$("#msgSuccess").attr("style","display:block");
			$(".mainElement").attr("style","display:none");
			$(".weui_msg_title").html("操作成功");
			$(".weui_msg_desc").html("商品"+foodName+"消费已确认!");
		}else if(data==2){
			$("#dialog1").attr("style","display:none"); 
			$("#msgWarn").attr("style","display:block");
			$(".mainElement").attr("style","display:none");
			$(".weui_msg_title").html("消费失败");
			$(".weui_msg_desc").html("商品"+foodName+"已过期!");
		}else if(data==3){
			$("#dialog1").attr("style","display:none"); 
			$("#msgWarn").attr("style","display:block");
			$(".mainElement").attr("style","display:none");
			$(".weui_msg_title").html("消费失败");
			$(".weui_msg_desc").html("系统超时，请重试!");
		}else if(data==4){
			$("#dialog1").attr("style","display:none"); 
			$("#msgWarn").attr("style","display:block");
			$(".mainElement").attr("style","display:none");
			$(".weui_msg_title").html("消费失败");
			$(".weui_msg_desc").html("管理员已取消申请!");
		}else if(data==0){
			$("#dialog1").attr("style","display:none"); 
			$("#msgWarn").attr("style","display:block");
			$(".mainElement").attr("style","display:none");
			$(".weui_msg_title").html("消费失败");
			$(".weui_msg_desc").html("未知错误!");
		}
	});
	
});

$(".weui_btn_primary").click(function(){
	$("#msgWarn").attr("style","display:none");
	$("#msgSuccess").attr("style","display:none");
	$(".mainElement").attr("style","display:block");
	$("#table").bootstrapTable({
			url : "/xProgram/mobileConsume/getAllConsume.do"
		  });
});



$.post("../mobileConsume/getConsumeInfo.do",{
	
	},function(data){
		//解析json为对象
		if(data!=null){
		 var obj=JSON.parse(data);
		 state=obj.state;
		 campusAdmin=obj.campusAdmin;
		 id=obj.id;
		 openId=obj.openId;
		 foodId=obj.foodId;
		 amount=obj.amount;
		 foodName=obj.name;
		 
		 
		 $("#brand").html(obj.campusName);
		 $(".headimgUrl").attr("src",obj.headimgUrl);
		 $(".nickName").html(obj.nickName);
		 
		}

		
		if(state==true){
			 $("#dialog1").attr("style","display:block");
			 $("#affirmBody").html("订单号 : "+id+
					 "<br />商品名称 : "+foodName+
					 "<br />商品数量 : "+amount+
					 "<br />所属商家 : "+campusAdmin);
		}else if(openId!=0){
			$("#msgWarn").attr("style","display:block");
			$(".mainElement").attr("style","display:none");
			$(".weui_msg_title").html("错误");
			$(".weui_msg_desc").html("没有未消费的"+campusAdmin+"的商品");
		}else{
			$("#msgWarn").attr("style","display:block");
			$(".mainElement").attr("style","display:none");
			$(".weui_msg_title").html("操作错误");
			$(".weui_msg_desc").html("未登陆，请扫描二维码重新确认");
		}
	})

