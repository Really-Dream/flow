//JavaScript Document
/**
 * @fileoverview 统一视图页面见操作区弹窗控制方法
 * about based on jQuery
 * @author 0928 Jeff
 * @version 1.0.0
*/
//展示处理意见操作区弹窗
function GetDoArea(obj){
	var _objY=$(obj).offset().top-$(window).scrollTop();
	var _itemW=$(".right-fixed-part").width();
	$("#doarea").css({"right":_itemW+"px","top":_objY+"px"}).slideDown(300);
	};

$(function(){ 
	//拖拽 
	dragAndDrop(); 
	//初始化位置 
	initPosition(); 
	//点击关闭按钮 
	closeQuery(); 
}); 
//拖拽 
function dragAndDrop(){ 
var _move=false;//移动标记 
var _x,_y;//鼠标离控件左上角的相对位置 
$(".mTitle").mousedown(function(e){ 
_move=true; 
_x=e.pageX-parseInt($("#doarea").css("left")); 
_y=e.pageY-parseInt($("#doarea").css("top")); 
$(".mTitle").fadeTo(20,0.9);//点击开始拖动并透明显示 
}); 
$(document).mousemove(function(e){ 
if(_move){ 
var x=e.pageX-_x;//移动时鼠标位置计算控件左上角的绝对位置 
var y=e.pageY-_y; 
$("#doarea").css({top:y,left:x});//控件新位置 
} 
}).mouseup(function(){ 
_move=false; 
$(".mTitle").fadeTo("fast",1);//松开鼠标后停止移动并恢复成不透明 
}); 
} 
//初始化拖拽div的位置 
function initPosition(){ 
//计算初始化位置 
var itop=($(document).height()-$("#doarea").height())/2; 
var ileft=($(document).width()-$("#doarea").width())/2; 
//设置被拖拽div的位置 
$("#doarea").css({top:itop,left:ileft}); 
};
//关闭操作意见窗口
function closeQuery(){
	$("#doarea").hide();
	
} ;