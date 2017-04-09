$.post("../mobileService/getFoodCountById.do", {
                 foodId:${articles.id}
			}, function(text) {
			   $("#count").html(text);
			});

$.get("/xProgram/oauth/test.do", {

     campusId:1
   }, function(data) {
      if(data="false"){
        console.log("data="+false);
    	  
      }
});

 console.log("cookie="+document.cookie);