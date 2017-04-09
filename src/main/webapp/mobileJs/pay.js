function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
    }


var foodId=getQueryString("foodId");
var foodName=getQueryString("foodName");
var price=getQueryString("price");

var amount=$("#foodCount").text();

$(".good-line .line-title").html(foodName);
$(".good-line .line-content").html("单价：¥"+price);
$(".money-line .line-content span").html(price*amount);

$(".plus-btn").click(function(){
	amount=$("#foodCount").text();
	amount++;
	if(amount>1){
		$(".minus-btn").removeAttr("disabled");
	}
	$("#foodCount").html(amount);
	$(".money-line .line-content span").html(price*amount);
});

$(".minus-btn").click(function(){
	amount=$("#foodCount").text();
	amount--;
	if(amount<=1){
		$(".minus-btn").attr("disabled","");
	}
	$("#foodCount").html(amount);
	$(".money-line .line-content span").html(price*amount);
});

$("#submit-btn").click(function(){
	$('#loadingToast').show();
});