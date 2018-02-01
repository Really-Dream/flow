// JavaScript Document
//弹出层+窗口
function shadowFocusQuery(a,b){
	var window_W=$(window).width();
	var window_H=$(window).height();	
	var _msgX=(window_W-$("#"+b).width())/2;
	var _msgY=(window_H-$("#"+b).height())/2-30;
	$("body").css("overflow","hidden").append("<div class='shadowScreen' style='height:"+window_H+"'></div>");
	$("#"+b).fadeIn(200);
	if($("#"+b).find(".popw-mTitle").length==0){
		var poptitle=$("#"+b).attr("title");
	$("#"+b).removeAttr("title");
	var popt="<div class='popw-mTitle'><span class='popw-unread'>"+poptitle+"</span>";
        popt+="<span class='popw-closemsg' title='关闭' onClick='CloseMyWindow("+b+");'></span></div>";
	$("#"+b).find(".popw-mTitle").remove();	
	$("#"+b).css({"top":_msgY+"px","left":_msgX+"px"}).prepend(popt);
	};
};
//关闭弹窗
function CloseMyWindow(wid){
	$(wid).hide();
	$(".shadowScreen").remove();
	$("body").css("overflow","auto");
};
//清空查询条件
function clearInsert(){
	$("#pop-insert").val("").focus();
	}