//JavaScript Document
/**
 * @fileoverview 统一视图页面右侧导航目录控制方法
 * about based on jQuery
 * @author 0928 Jeff
 * @version 1.0.0
*/
//右侧目录导航点击事件
$(function(){
	$(".u-tableNo3 td").click(function(){
		var n=$(".u-tableNo3 td").index(this);
		$(".u-tableNo3 td").removeClass("active");
		$(this).addClass("active");
	})	
})
//随页面滚动更新目录状态
$(function(){        
	//遍历锚点  
	var mds = $(".LH2 a");
	var arrMd = [];  
	for(var i = 0, len = mds.length;i<len;i++){  
	arrMd.push($(mds[i]));  
	};    
	function update(){  
		var scrollH = $(window).scrollTop();  
		for(var i = 0;i<len;i++){  
		var mdHeight = arrMd[i].offset().top;  
		if(mdHeight < scrollH+10){
			$(".u-tableNo3 td").removeClass("active");
				$(".u-tableNo3 td").eq(i).addClass("active");
		};  
	}; 
};  
	//绑定滚动事件  
	$(window).bind('scroll',update);  
});