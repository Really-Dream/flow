layui.use(['layer'], function(){
    var layer = layui.layer;

    shadow_ = function(){
        shadow1 = layer.open({
            type: 1
            ,title: false //不显示标题栏
            ,closeBtn: false
            ,area: '30%'
            ,shade: 0.5
            ,id: 'shadow_'
            ,btnAlign: 'c'
            ,moveType: false
        });
    }
});

function alertText(data){
    layer.open({
        type: 1
        , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        , id: 'deleteModel' //防止重复弹出
        , content: '<div style="padding: 20px 100px;">' + data + '</div>'
        , btn: '关闭'
        , btnAlign: 'c' //按钮居中
        , shade: 0.5 //不显示遮罩
        , yes: function () {
            layer.closeAll();
        }
    })
}