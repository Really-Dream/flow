$(function(){
    var html = "";
    $.ajax({
        url: "/menu/list",
        data:{rand:Math.random()},
        dataType: "json",
        async: false,
        success: function(data){
            for(var i=0;i<data.length;i++){
                html += "<li class='layui-nav-item layui-nav-itemed'>"
                if(data[i].menuAction != undefined && data[i].menuAction.length>0){
                    html += "<a href='"+data[i].menuAction+"'>"+data[i].menuName+"</a>";
                }else {
                    html += "<a href='javascript:;'>"+data[i].menuName+"</a>";
                }
                if(data[i].list != undefined && data[i].list.length > 0){
                    html += "<dl class='layui-nav-child'>";
                    for(var j=0 ; j<data[i].list.length ; j++){
                        if(window.location.href.indexOf(data[i].list[j].menuAction)>0){
                            html += "<dd class='layui-this'><a href='"+data[i].list[j].menuAction+"'>"+data[i].list[j].menuName+"</a></dd>";
                        }else{
                            html += "<dd><a href='"+data[i].list[j].menuAction+"'>"+data[i].list[j].menuName+"</a></dd>";
                        }
                    }
                    html += "</dl>";
                }
                html += "</li>";
            }

            $("#header_").html(html);
        },
        error:function () {

        }
    })
})
