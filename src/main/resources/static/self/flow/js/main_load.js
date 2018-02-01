//JavaScript Document
/**
 * @fileoverview 统一视图页面默认加载结构控制方法
 * about based on jQuery
 * @author 0928 Jeff
 * @version 1.0.0
*/
$(function(){
	//一级大标题左侧的横线宽度自适应
	for(var i=0;i<$(".titleLine").length;i++){
		var h1W=$(".titleLine").eq(i).parent().children(".LH1").width();
		var h2W=$(".titleLine").eq(i).siblings(".LH2").width();
		var LheaderW=$(".titleLine").eq(i).parent().width();
		$(".titleLine").eq(i).css("width",LheaderW-h2W-h1W-100+"px");	
	};
	//一级大标题左侧的横线宽度适应浏览器窗口大小改变
	$(window).resize(function(){
		for(var i=0;i<$(".titleLine").length;i++){
		var h1W=$(".titleLine").eq(i).siblings(".LH1").width();
		var h2W=$(".titleLine").eq(i).siblings(".LH2").width();
		var LheaderW=$(".titleLine").eq(i).parent().width();
		$(".titleLine").eq(i).css("width",LheaderW-h2W-h1W-100+"px");
		};
	});
});
//页面滚动事件
$(function(){
	var rbl=($(window).width()-$(".u-main").width())/2;
	$("#right-bar").css({"right":rbl+"px"});
	$(window).resize(function(){//浏览器窗口改变时
		var rrbl=($(window).width()-$(".u-main").width())/2;
		$("#right-bar").css({"right":rrbl+"px"});
		})
	$(window).scroll(function(){
		//页面滚动，回到顶部控制
		if($(window).scrollTop()>30){
			$("#back-top").fadeIn(300);
		}else{
			$("#back-top").fadeOut(300);
		};
		//页面滚动，右侧导航目录位置控制
		if($(window).scrollTop()>70){
			$("#right-bar").css({"position":"fixed","top":10+"px"});
			$(".right-man-part").hide();
		}else{
			$("#right-bar").css({"position":"absolute","top":20+"px"});
			$(".right-man-part").show();
		};
	});
});

//页面内容收起展开效果
$(function(){
	$(".clickIcon").click(function(){
		var order=$(".clickIcon").index(this);
		$(this).toggleClass("currentClickIcon");
		$(".u-content-box").eq(order).slideToggle(200);
	});
});
//页面中平铺展示标签
function selectTakeLi(obj,li_num){
	$(obj).addClass("tab4ul-li-active");
	$(obj).siblings().removeClass("tab4ul-li-active");
};
//显示窗口右侧条形区
$(function(){
	var _Wheight=$(window).height();
	$(".right-fixed-part").css("height",_Wheight+"px");
	$(window).resize(function(){
		$(".right-fixed-part").css("height",$(window).height()+"px");
		})
	$(".R-fixed-item").hover(function(){
		$(this).children("span").stop().show().css("display","block");
		},function(){
			$(this).children("span").css("display","none");
			});
	});
//鼠标移动到目标元素，增加title属性
function GetTittle(obj){
	$(obj).attr("title",$(obj).text());
};

$(function(){
	getScrollToBottom()
	});
function getScrollToBottom(){
	var win_h=document.body.clientHeight-document.documentElement.clientHeight+10;
	javascript:scroll(0,win_h);
	$(".u-left-column").find(".u-chapter-block:last").find(":input:first").focus();
}