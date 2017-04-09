	function actionStatus(value, row, index){
		if (value == "0"){
			return "<img src='../../img/start.jpg' width='15px' height='13px' title='未开始'></i>";
		}else if (value == "1") {
			return "<img src='../../img/go.jpg' width='15px' height='13px' title='进行中'></i>";
		}else if(value=="2"){
			return "<img src='../../img/end.jpg' width='13px' height='13px' title='已结束'></i>";
		}
	}

	function statusFormatter(value, row, index) {
		if (value == "0")
			return "<img src='../../img/wrong.jpg' width='15px' height='15px'></i>未审核";
		else if (value == "1") {
			return "<img src='../../img/right.jpg' width='15px' height='15px'></i>已审核";
		}
	}
	
	function setSuccess(message) {
            alert(message);
	}
	
	function actionImg(value, row, index) {
		return ['<div class="detail-image">',
				'&nbsp;<img src="'+value+'" height="23px">&nbsp;',
				'</div>'].join('');
	}
	
	function statusFormatter(value, row, index) {
		if (value == "0")
			return "<img src='../../img/wrong.jpg' width='15px' height='15px'></i>未审核";
		else if (value == "1") {
			return "<img src='../../img/right.jpg' width='15px' height='15px'></i>已审核";
		}
	}
	
	
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

			 var val=document.getElementById("table").rows[hang].cells[1]; //取得所在列的值
		     var foodId=val.innerHTML; 
		     var url="orders.html?foodId="+foodId;
	    	  location.href=url;

	}
	
$(function(){
	
	if(getCookie("type")==0)
		 $('#brand').text(getCookie("campusAdmin")+" (管理员)");
		else
		  $('#brand').text(getCookie("campusAdmin")+" (普通商户)");
	
	//定义全局变量
	var adminType = getCookie("type");		//当前登录账号类型（总管理员、校区管理员）
	console.log('admintype='+adminType);
	var campusId = getCookie("campusId");	
	var status = getCookie("status");		//校区状态
	console.log(document.cookie);
	
		if(campusId!=null&&typeof(campusId)!="undefined"){
			$.post('/xProgram/campus/getCampusById.do', {
				campusId : getCookie("campusId")
			},function(data){		
			   $("#dropdownMenu1").html('<i class="result-icon result-fail"></i>'+
					   data.campus.campusName+'<span class="caret"></span>');	
			},"json");
		}

	
	$("#dropdown-campus").empty();//移除子元素
	$.getJSON("/xProgram/campus/getAllCampus.do", 
	  function(data){
		//alert(data.campus[0].campusName);
		for(var i=0; i < data.length; i++){
			$("#dropdown-campus").append("<li role='presentation'><a class='campus-item' role='menuitem' tabindex='-1' href='#'>"+data[i].campusName+"</a></li>");
		}
		$(".campus-item").on("click", function(event){
			//alert($(this).text());
			var campusName = $(this).text();
			
			$.getJSON("../../campus/getIdByName.do?campusName="+campusName,function(data){
				console.log(data.campusId);
				document.cookie="campusId="+data.campusId;
				//$.cookie('campusId',data.campusId);
				$.post('/xProgram/campus/getCampusById.do',{
					campusId : data.campusId
				},function(data){	
						if(i>1){
							$("#dropdownMenu1").html(
									'<i class="result-icon result-fail"></i>'+
									data.campus.campusName+'<span class="caret"></span>');
						}else{
							$("#dropdownMenu1").html(
									'<i class="result-icon result-fail"></i>'+
									data.campus.campusName);
						}
				},"json");
				
				 campusId = getCookie("campusId");
				
				  $(" #table").bootstrapTable('refresh',{
						url : "../../mobileService/getAllFoods.do?campusId="+campusId+"&datepicker=null&datepicker2=null"
					});				
				
			});
			
		});
	});
	
	var $table = $("#table");
	var $addFoodCountBtn = $("#addFoodCountBtn");
	var obj = document.getElementById("categoryId2");
	var obj2 = document.getElementById("campusAdmin2");

	$addFoodCountBtn.click(function(){
		var array = $("#table").bootstrapTable('getSelections');
		if(array.length==0){
			$('#alertchar').text('请至少选中一条记录！');
			$('#commitFoodDataButton').hide();
			$('#alertdialog').modal("show");
		}else if(array.length>1){
			$('#alertchar').text('不能同时编辑多条记录！');
			$('#commitFoodDataButton').hide();
			$('#alertdialog').modal("show");
		}else if(array.length==1){
			//alert(array[0].foodCount);
			$("#campus_id_add_count").val(1);
			$("#addFoodCountModal").modal('show');
		}	

	});

	$("#table").bootstrapTable({
		url : "../../mobileService/getAllFoods.do?campusId="+campusId+"&datepicker=null&datepicker2=null"
	});
	
	 $("#QRCode").on("click",function(){
		   campusId = getCookie("campusId");
	    	$.post("../../mobileQrCode/getUrl.do",{
	    		campusId:campusId
	    	  },function(data){
	    		  var obj=JSON.parse(data);
	    		 var QRimg="<img style='width: 100%; height: 100%;' src='../../"+obj.url+"'>";
	    		 $(".weui_actionsheet_menu .weui_actionsheet_cell").html(QRimg);
	                 var mask = $('#mask');
	                 var weuiActionsheet = $('#weui_actionsheet');
	                 weuiActionsheet.addClass('weui_actionsheet_toggle');
	                 mask.show()
	                     .focus()//加focus是为了触发一次页面的重排(reflow or layout thrashing),使mask的transition动画得以正常触发
	                     .addClass('weui_fade_toggle').one('click', function () {
	                     hideActionSheet(weuiActionsheet, mask);
	                 });
	                 $('#actionsheet_cancel').one('click', function () {
	                     hideActionSheet(weuiActionsheet, mask);
	                 });
	                 mask.unbind('transitionend').unbind('webkitTransitionEnd');

	                 function hideActionSheet(weuiActionsheet, mask) {
	                     weuiActionsheet.removeClass('weui_actionsheet_toggle');
	                     mask.removeClass('weui_fade_toggle');
	                     mask.on('transitionend', function () {
	                         mask.hide();
	                     }).on('webkitTransitionEnd', function () {
	                         mask.hide();
	                     })
	                 }
	        
	    	  });
    });
	 
	 
});