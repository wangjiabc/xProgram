/**
 * 
 */
var valid1,valid2,valid3,valid4,valid5;
    valid1=valid2=valid3=valid4=valid5=false;
 
$(document).ready(function() {
	
//---------------------------username-----------------------------------------------------	
   var trem='4-16个字符,可使用字母，数字，下划线';
   var text='<span id="mobile_Tip"><span class="action_po"><div class="action_po_top"><p>'+trem+'</p></div></span></span>';
  
   
   $("#username input").focus(function(){
	   $('#mobile_Tip').remove();
	   $('#username input').css('border-color','');
	   $(text)
	   .insertAfter('#username input');
	 });
  $('#username input').blur(function(){
     var reque=$('#username input')[0].value;
   	$.post('/xProgram/register/testName.do',{
   	   username:reque
   	   },function(text){ 
   		var obj=JSON.parse(text);
		 data=obj.data;
    	if(data!='succeed'){
   	     $('#mobile_Tip .action_po').attr('class','wrong');
   	 	 $('#mobile_Tip .action_po_top').text(data);
   	 	 $('#username input').css('border-color','red');
     	}else{
   		 $('#mobile_Tip .action_po').attr('class','right');
   	 	 $('#mobile_Tip .action_po_top').text('');
   	     $('#username input').css('border-color','');
   	     valid1=true;
   	   }
     });
   	
   });
 
 

//------------------------------password----------------------------------------   


//-------------------------------javascript密码强度测试模块----------------- 
   function CharMode(iN){            //CharMode函数 测试某个字符是属于哪一类.
   if (iN>=48 && iN <=57) //数字 
   return 1; 
   if (iN>=65 && iN <=90) //大写字母 
   return 2; 
   if (iN>=97 && iN <=122) //小写 
   return 4; 
   else 
   return 8; //特殊字符 
   } 

   function bitTotal(num){          //bitTotal函数 计算出当前密码当中一共有多少种模式 
   modes=0; 
   for (i=0;i<4;i++){ 
   if (num & 1) modes++; 
   num>>>=1; 
   } 
   return modes; 
   } 

   function checkStrong(sPW){       //checkStrong函数返回密码的强度级别 
   if (sPW.length<=6) 
   return 0; //密码太短 
   Modes=0; 
   for (i=0;i<sPW.length;i++){      //测试每一个字符的类别并统计一共有多少种模式. 
   Modes|=CharMode(sPW.charCodeAt(i)); 
   } 
   return bitTotal(Modes); 
   } 

   function pwStrength(pwd){     //!!!pwStrength函数当用户放开键盘或密码输入框失去焦点时,根据不同的级别显示不同的颜色 
   O_color="#eeeeee"; 
   L_color="#FF0000"; 
   M_color="#FF9900"; 
   H_color="#33CC00"; 
   var word;
   if (pwd==null||pwd==''){ 
   Lcolor=Mcolor=Hcolor=O_color; 
   } 
   else{ 
   S_level=checkStrong(pwd); 
   switch(S_level) { 
   case 0: 
   Lcolor=Mcolor=Hcolor=O_color;
   word="密码强度:弱";
   case 1: 
   Lcolor=L_color; 
   Mcolor=Hcolor=O_color; 
   word="密码强度:弱";
   break; 
   case 2: 
   Lcolor=Mcolor=M_color; 
   Hcolor=O_color;
   word="密码强度:中";
   break; 
   default: 
   Lcolor=Mcolor=Hcolor=H_color; 
   word="密码强度:强";
   } 
   } 
   document.getElementById("strength_L").style.background=Lcolor; 
   document.getElementById("strength_M").style.background=Mcolor; 
   document.getElementById("strength_H").style.background=Hcolor; 
   return word; 
   }
//-------------------------------密码强度测试模块结束-------------------------------------------------


   var trempw='6-16位字符(字母，数字，特殊符号)';
   var textpw='<span id="moblie_pw"><span class="action_po"><div class="action_po_top"><p>'+trempw+'</p></div></span></span>';
   var intension='<div id="inten"><table ><tr><td id="strength_L" width="40px"></td> <td  id="strength_M" width="40px"></td> <td id="strength_H" width="40px"></td> </tr> </table> </div>';
     
   $('#password input').focus(function(){
	   $('#moblie_pw').remove();
	   $('#password input').css('border-color','');
	   $(textpw)
	    .insertAfter('#password input');
	  
	   $(this).keyup(function(data){
		   $('#inten').remove();
		   $('#moblie_pw .action_po').attr('class','action_pw');
		   Streng=$('#password input')[0].value;
		   
		   $(intension)
		    .insertAfter('#moblie_pw');
		   word=pwStrength(Streng);
		   $('#moblie_pw').hide();     // bootstrap样式冲突
		   $('#moblie_pw .action_po_top').text(word);
	   });
   });
   $('#password input').blur(function(){
	   $('#inten').remove();
	   $('#moblie_pw').show();
	   $('#moblie_pw .action_pw').attr('class','action_po');
	   var reque=$('#password input')[0].value;
	   
	   $.post('/xProgram/register/testPassword.do',{
		  password:reque
		 },function(text){
			var obj=JSON.parse(text);
			data=obj.data;
		    if(data!='succeed'){
		     $('#moblie_pw .action_po').attr('class','wrong');
		     $('#moblie_pw .action_po_top').text(data);
		     $('#password input').css('border-color','red');
		    }else{
			 $('#moblie_pw .action_po').attr('class','right');
			 $('#moblie_pw .action_po_top').text('');
			 valid2=true;
		    }
	    });
    });

  
 
//--------------------------------Validate password------------------------------- 

    var tremreg='请再次输入密码';
    var textreg='<span id="moblie_reg"><span class="action_po"><div class="action_po_top"><p>'+tremreg+'</p></div></span></span>';
     $('#validate input').focus(function(){
	   $('#moblie_reg').remove();
	   $('#validate input').css('border-color','');
	   $(textreg)
	   .insertAfter('#validate input');
     });
     
     $('#validate input').blur(function(){
    	var first=$('#password input')[0].value;
    	var second=$('#validate input')[0].value;
    	if(first!=second||first==''){
    		$('#validate .action_po').attr('class','wrong');
    		$('#validate .action_po_top').text('密码不一致，请再次确认');
    		$('#validate input').css('border-color','red');
    	}else{
    		$('#validate .action_po').attr('class','right');
    		$('#validate .action_po_top').text('');
    		$('#validate input').css('border-color','');
    		valid3=true;
    	}
     });

//--------------------------------------------------------------------------------
     var trphone='请输入电话号码';
     var textphone='<span id="mobile_phone"><span class="action_po"><div class="action_po_top"><p>'+trphone+'</p></div></span></span>';
    
     $("#telephone input").focus(function(){
  	   $('#mobile_phone').remove();
  	   $('#telephone input').css('border-color','');
  	   $(textphone)
  	   .insertAfter('#telephone input');
  	 });
     
     $('#telephone input').blur(function(){
  	   var reque=$('#telephone input')[0].value;

     	$.post('/xProgram/register/testPhone.do',{
     		telephone:reque
     		},function(text){
     		var obj=JSON.parse(text);
     		 data=obj.data;
     	     if(data!='succeed'){
     		   $('#mobile_phone .action_po').attr('class','wrong');
     	 	   $('#mobile_phone .action_po_top').text(data);
     	 	   $('#telephone input').css('border-color','red');
     	    }else{
     	    	$('#mobile_phone .action_po').attr('class','right');
     	 	    $('#mobile_phone .action_po_top').text('');
     	        $('#telephone input').css('border-color','');
     	       valid4=true;
     	  } 
        });     	
     });  
     
   
//---------------------------------email--------------------------------------------------  
   var trmail='请输入合作商家的门店地址';
   var textmail='<span id="mobile_mail"><span class="action_po"><div class="action_po_top"><p>'+trmail+'</p></div></span></span>';
  
   $("#email input").focus(function(){
	   $('#mobile_mail').remove();
	   $('#email input').css('border-color','');
	   $(textmail)
	   .insertAfter('#email input');
	 });
  $('#email input').blur(function(){
	 var reque=$('#email input')[0].value;

   	$.post('/xProgram/register/testEmail.do',{
   	   email:reque
   	  },function(text){  
   	    var obj=JSON.parse(text);
		data=obj.data;
     	if(data!='succeed'){
   		  $('#mobile_mail .action_po').attr('class','wrong');
   	 	  $('#mobile_mail .action_po_top').text(data);
   	 	  $('#email input').css('border-color','red');
    	}else{
   		  $('#mobile_mail .action_po').attr('class','right');
   	 	  $('#mobile_mail .action_po_top').text('');
   	      $('#email input').css('border-color','');
   	     valid5=true;
     	}
   	});
   	
  });

//---------------------获取同意协议复选框状态--------------------------------------
   var chk=document.getElementById('chkagreement');  
   
   var trchk='请查看《Weixin使用协议》，并选择！';
   var textchk='<span id="mobile_chk"><span class="wrong"><div class="action_po_top"><p>'+trchk+'</p></div></span></span>';
  
    $('#agreement input').focus(function(){
    	$('#mobile_chk').remove();
    });
    
    $('#agreement input').blur(function(){
    	 if(!chk.checked){
    		 $(textchk)
    		   .insertAfter('#agreement input');
    	 }else{
    		 
    		 $('#mobile_chk .wrong').attr('class','right');
    	   	 	$('#mobile_chk .action_po_top').text('');	 
    	 }
    });
   
//-----------------------------------------------------------------------------
//---------------------------注册和登录------------------------------------------
//--------------------------------------------------------------------------   
 
   function autoclick(name)    //JavaScript自动点击链接
   {   
   	if(document.all)   
   	{   
   		//alert(1);
     		document.getElementById(name).click();   
   	}   
     	else   
   	{   
       	var evt = document.createEvent("MouseEvents");   
      		evt.initEvent("click", true, true);   
   		//alert(2);
       	document.getElementById(name).dispatchEvent(evt);   
     	}   
   } 
   
   
   $("#re_ver_input").focus(function(){
	   $("#moblie_ver").remove();
   });
   
  $("#register").click(function(e) {   
   if(valid1==true&&valid2==true&&valid3==true&&valid4==true){
		
	 if(!chk.checked){                                   //没有同意协议复选框选择 
		 $('#agreement input').focus();
		 $('#agreement input').blur();
	   }else{                                            //同意协议复选框选择

       //-----------------------------------验证码----------------------------------------
	 
	   var textver='<span id="moblie_ver"><span class="wrong"><div class="action_po_top"><p>未输入验证码</p></div></span></span>';
	   var textver2='<span id="moblie_ver"><span class="wrong"><div class="action_po_top"><p>验证码错误</p></div></span></span>';
	    $.post('/xProgram/register/insert.do', 
	    	$('#mainform').serialize() +'&action='+ $(event.target).attr('id')
	      , function(text) {
	    	var obj=JSON.parse(text);
	  		data=obj.data;
            var code = data;
            console.log('data='+data);
            $('#moblie_ver').remove();   
	       if(code == 'succeed') {
	    	   swal({title: "注册成功", text: "请点击确定跳转到登陆页面!",   type: "success",confirmButtonColor: "#44b549",   confirmButtonText: "确定",   closeOnConfirm: true },
	    			   function(){  
	    		         location.href="login.html";
	    		       });
	       }else if(code=='regtlx_null'){	    	   
	    	   $(textver)
	    	   .insertAfter('#ver input');
	           }else if(code=='regtlx_error'){
	        	   
	        	   $(textver2)
		    	   .insertAfter('#ver input');
	        	   autoclick("re_imgclick");   //更换验证码
	                }else if(code == 'error') {
	                	$("#username input").focus();
	         		    $("#username input").blur();
	         		    $('#password input').focus();
	       		        $('#password input').blur();
	       		        $('#validate input').focus();
	      		        $('#validate input').blur();
	      		        $('#telephone input').focus();
	       		        $('#telephone input').blur();
	      		        $('#email input').focus();
	       		        $('#email input').blur();
	       		     autoclick("re_imgclick");   //更换验证码
	              }else if(code=="false"){
	            	  swal({title: "注册失败", type: "error",confirmButtonColor: "#44b549",   confirmButtonText: "确定",   closeOnConfirm: true });
	              }
	      }); 
	    
	      return e.preventDefault();	
	    
	   } 
     }else{
    	   e.preventDefault();
    	   if(valid1==false){
    		   $("#username input").focus();
    		   $("#username input").blur();
    	   }
    	   if(valid2==false){
    		   $('#password input').focus();
    		   $('#password input').blur();
    	   }
    	   if(valid3==false){
    		   $('#validate input').focus();
    		   $('#validate input').blur();
    	   }
    	   if(valid4==false){
    		   $('#telephone input').focus();
    		   $('#telephone input').blur();
    	   }
    	   if(valid5==false){
    		   $('#email input').focus();
    		   $('#email input').blur();
    	   }
       }  
   });

    
   
   $("body").keypress(function(e) {  
	   
	   if(event.keyCode==13){
	   if(valid1==true&&valid2==true&&valid3==true&&valid4==true){
			
		 if(!chk.checked){                                   //没有同意协议复选框选择 
			 $('#agreement input').focus();
			 $('#agreement input').blur();
		   }else{                                            //同意协议复选框选择

	       //-----------------------------------验证码----------------------------------------
		 
		   var textver='<span id="moblie_ver"><span class="wrong"><div class="action_po_top"><p>未输入验证码</p></div></span></span>';
		   var textver2='<span id="moblie_ver"><span class="wrong"><div class="action_po_top"><p>验证码错误</p></div></span></span>';
		    $.post('/xProgram/register/insert.do', 
		       $('#mainform').serialize() +'&action='+ 'register'
		      ,function(text) {
		    	 var obj=JSON.parse(text);
		  		 data=obj.data;
	             var code = data;
	             console.log('data='+data);
	             $('#moblie_ver').remove();   
		        if(code == 'succeed') {
		        	 swal({title: "注册成功", text: "请点击确定跳转到登陆页面!",   type: "success",confirmButtonColor: "#44b549",   confirmButtonText: "确定",   closeOnConfirm: true },
		      			   function(){  
		        		     location.href="login.html";
		      		       });   
		         }else if(code=='regtlx_null'){	    	   
		    	   $(textver)
		    	   .insertAfter('#ver input');
		           }else if(code=='regtlx_error'){
		        	   
		        	   $(textver2)
			    	   .insertAfter('#ver input');
		        	   autoclick("re_imgclick");   //更换验证码
		                }else if(code == 'error') {
		                	$("#username input").focus();
		         		    $("#username input").blur();
		         		    $('#password input').focus();
		       		        $('#password input').blur();
		       		        $('#validate input').focus();
		      		        $('#validate input').blur();
		      		        $('#telephone input').focus();
		       		        $('#telephone input').blur();
		      		        $('#email input').focus();
		       		        $('#email input').blur();
		       		     autoclick("re_imgclick");   //更换验证码
		              }else if(code=="false"){
		            	  swal({title: "注册失败", type: "error",confirmButtonColor: "#44b549",   confirmButtonText: "确定",   closeOnConfirm: true });
		              }
		      }); 
		    
		      return e.preventDefault();	
		    
		   } 
	     }else{
	    	   e.preventDefault();
	    	   if(valid1==false){
	    		   $("#username input").focus();
	    		   $("#username input").blur();
	    	   }
	    	   if(valid2==false){
	    		   $('#password input').focus();
	    		   $('#password input').blur();
	    	   }
	    	   if(valid3==false){
	    		   $('#validate input').focus();
	    		   $('#validate input').blur();
	    	   }
	    	   if(valid4==false){
	    		   $('#telephone input').focus();
	    		   $('#telephone input').blur();
	    	   }
	    	   if(valid5==false){
	    		   $('#email input').focus();
	    		   $('#email input').blur();
	    	   }
	       }  
	    }
	  
     });
   
   
});