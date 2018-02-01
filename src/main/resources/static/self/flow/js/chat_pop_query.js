// JavaScript Document
//弹出讨论窗口
function getChatWinQuery(chatid){
	var window_W=$(window).width();
	var window_H=$(window).height();	
	var _msgX=(window_W-$("#"+chatid).width())/2;
	var _msgY=(window_H-$("#"+chatid).height())/2;
	$("#"+chatid).fadeIn(100);
	$("#"+chatid).css({"top":_msgY+"px","left":_msgX+"px"});
	}
$(function(){ 
//拖拽 
dragAndDropChat();  
}); 
//拖拽 
function dragAndDropChat(){ 
var _move=false;//移动标记 
var _x,_y;//鼠标离控件左上角的相对位置 
$(".chat-mTitle").mousedown(function(e){ 
_move=true; 
_x=e.pageX-parseInt($("#chatwin").css("left")); 
_y=e.pageY-parseInt($("#chatwin").css("top")); 
$(".chat-mTitle").fadeTo(20,0.85);//点击开始拖动并透明显示 
}); 
$(document).mousemove(function(e){ 
if(_move){ 
var x=e.pageX-_x;//移动时鼠标位置计算控件左上角的绝对位置 
var y=e.pageY-_y; 
$("#chatwin").css({top:y,left:x});//控件新位置 
} 
}).mouseup(function(){ 
_move=false; 
$(".chat-mTitle").fadeTo("fast",1);//松开鼠标后停止移动并恢复成不透明 
}); 
}
//关闭讨论窗口
function CloseChatWindow(){
	$("#chatwin").hide();
	} 