$('#auser').click(function(){
   $('#content').load('pages/user.html');
   $('#content').attr('src','pages/user.html');
 });

$('#auserGroup').click(function(){
	   $('#content').load('pages/userGroup.html');
	   $('#content').attr('src','pages/userGroup.html');
	 });

$('#auserAnalyse').click(function(){
	   $('#content').load('pages/userAnalyse.html');
	   $('#content').attr('src','pages/userAnalyse.html');
	 });

$('#aeject').click(function(){
	   $('#content').load('pages/food.html');
	   $('#content').attr('src','pages/food.html');
	 });

$('#business').click(function(){
	   $('#content').load('pages/business.html');
	   $('#content').attr('src','pages/business.html');
	 });

$('#orders').click(function(){
	   $('#content').load('pages/orders.html');
	   $('#content').attr('src','pages/orders.html');
	 });

$('#setting').click(function(){
	   $('#content').load('pages/setting.html');
	   $('#content').attr('src','pages/setting.html');
	 });

$('#pageSlide').click(function(){
	   $('#content').load('pages/pageSlide.html');
	   $('#content').attr('src','pages/pageSlide.html');
	 });

$('#pageSpecial').click(function(){
	   $('#content').load('pages/pageSpecial.html');
	   $('#content').attr('src','pages/pageSpecial.html');
	 });

$('#pageGroom').click(function(){
	   $('#content').load('pages/pageGroom.html');
	   $('#content').attr('src','pages/pageGroom.html');
	 });

if(getCookie("type")==0)
 $('#brand').text("用户 : "+getCookie("campusAdmin")+" (管理员)");
else
  $('#brand').text("用户 : "+getCookie("campusAdmin")+" (普通商户)");

//定义全局变量
var adminType = getCookie("type");		//当前登录账号类型（总管理员、校区管理员）
console.log('admintype='+adminType);
var campusId = getCookie("campusId");	
var status = getCookie("status");		//校区状态
console.log(document.cookie);

//定义通用方法

function setSuccess(message) {
	if (!message) {
		$("#operate-success").text("Well, it works!");
	} else {
		$("#operate-success").text(message);
	}
	//$("#operate-success").show();
	$("#operate-success").css("visibility", "visible");
	window.setTimeout(function() {
		//$("#operate-success").hide();
		$("#operate-success").css("visibility", "hidden");
	}, 2000);
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
    }

$(function(){
	
	    var backUrl=getQueryString("backUrl");
        
	    if(backUrl=="food.html"){
	       $('#content').load('pages/food.html');
	 	   $('#content').attr('src','pages/food.html');
        }
        
		console.log('campsidgetcampusebyid==='+getCookie('campusId'));
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
			
			$.getJSON("campus/getIdByName.do?campusName="+campusName,function(data){
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
			//	$('#content').attr('src', $('li.active figure a').attr('href'));
				var pages=$('#content').attr('src');
				if(pages!=0){
				 $('#content').load(pages);
				}
			});
			
		});
	});
	
});