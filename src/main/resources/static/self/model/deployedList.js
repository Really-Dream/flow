layui.use(['table','util','element','jquery','layer','form'], function(){
    $ = layui.jquery;
    var table = layui.table;
    var util = layui.util;
    var layer = layui.layer;
    var element = layui.element;

    $("#ul_").find("i").eq(0).remove();



    var active = {
        tabAdd: function(){
            //新增一个Tab项
            element.tabAdd('demo', {
                title: '新选项'+ (Math.random()*1000|0) //用于演示
                ,content: '内容'+ (Math.random()*1000|0)
                ,id: "ss" //实际使用一般是规定好的id，这里以时间戳模拟下
            })
//                alert($(".layui-tab-close").length);
//                $("#ul_").find("i").eq(0).remove();
               $("#ul_").find("li").eq($("#ul_").find("li").length-1).append("<i class='layui-icon layui-unselect layui-tab-close' onclick=\"tabDelete('ss')\">ဆ</i>");
//             alert($("#ul_").find("li").eq($("#ul_").find("li").length-1).html("<i>ဆ</i>"));
        },

    };
    tabDelete= function(id){
        //删除指定Tab项
        element.tabDelete('demo', id); //删除：“商品管理”
    }

    $('.site-demo-active').on('click', function(){
        var othis = $(this), type = othis.data('type');
        active[type] ? active[type].call(this, othis) : '';
    });

    //Hash地址的定位
    var layid = location.hash.replace(/^#test=/, '');
    element.tabChange('test', layid);

    element.on('tab(test)', function(elem){
        location.hash = 'test='+ $(this).attr('lay-id');
    });

    deleteDeployed = function(deploymentId){
        $("i").remove();
        return;
        shadow_();
        $.ajax({
            url: "/bpm/deployed/delete",
            data: {deploymentId: deploymentId},
            dataType: "json",
            async: false,
            success: function (data) {
                alertText(data);
                modelList();
            },
            error: function (data) {
                alertText(data);
                modelList();
            }
        })
    };

    modelList = function(){
        table.render({
            elem: '#demo'
            ,url:'/bpm/deployed/list'
            ,count: 'count'
            ,cols: [[
                {field:'ID', title: '流程定义ID',width:'20%',sort: true}
                ,{field:'name', title: '流程名称',width:'30%',sort: true}
                ,{field:'deploymentId', title: '部署ID',width:'20%',sort: true}
                ,{title: '操作',width:'30%', templet: '<div><button class="layui-btn layui-btn-sm" onclick="deleteDeployed({{d.deploymentId}})">删除</button>' +
                '<button class="layui-btn layui-btn-sm" onclick="deleteDeployed({{d.deploymentId}})">配置</button>' +
                '</div>'}
            ]]
        });
    };

    modelList();

});