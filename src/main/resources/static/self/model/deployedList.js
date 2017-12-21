layui.use(['table','util','element','jquery','layer','form'], function(){
    $ = layui.jquery;
    var table = layui.table;
    var util = layui.util;
    var layer = layui.layer;
    var element = layui.element;

    tabAdd= function(deployedId,id){

        //删除同一流程下的tab页
        element.tabDelete('demo', deployedId);

        //新增一个Tab项
        element.tabAdd('demo', {
            title: id //用于演示
            // ,content: "sdasdadsa"
            ,id: deployedId //实际使用一般是规定好的id，这里以时间戳模拟下
        });

        //手动添加关闭按钮 可使用lay-allowclose="true" 设置全部可关闭
        $("#ul_").find("li").eq($("#ul_").find("li").length-1).append("<i class='layui-icon layui-unselect layui-tab-close' onclick=\"tabDelete("+deployedId+")\">ဆ</i>");

        //选中刚新建的tab项
        element.tabChange('demo', deployedId);

        //插入添加tab的位置
        $('.layui-show')[0].innerHTML = "<table class='layui-hide' id='"+deployedId+"'></table>"

        table.render({
            // elem: '.layui-show'
            elem: "#"+deployedId
            ,url:'/bpm/deployed/nodeList?procDefId='+encodeURIComponent(id)
            ,count: 'count'
            ,cols: [[
                {field:'nodeId', title: '节点ID',width:'30%',sort: true}
                ,{field:'nodeName', title: '节点名称',width:'40%',sort: true}
                ,{title: '操作',width:'30%', templet: '<div>' +
                '<button class="layui-btn layui-btn-sm" onclick="nodeEdit({{d.id}})">编辑</button>' +
                '</div>'}
            ]]
        });
    };

    tabDelete= function(id){
        //删除指定Tab项
        element.tabDelete('demo', id); //删除
    };

    deleteDeployed = function(deploymentId,id){
        shadow_();
        $.ajax({
            url: "/bpm/deployed/delete",
            data: {deploymentId: deploymentId,procDefId: id},
            dataType: "json",
            async: true,
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
                ,{title: '操作',width:'30%', templet: '<div><button class="layui-btn layui-btn-sm" onclick="deleteDeployed({{d.deploymentId}},&apos;{{d.ID}}&apos;)">删除</button>' +
                '<button class="layui-btn layui-btn-sm" onclick="tabAdd({{d.deploymentId}},&apos;{{d.ID}}&apos;)">配置</button>' +
                '</div>'}
            ]]
        });
    };

    modelList();

});

function nodeEdit(){
    create = layer.open({
        type: 2
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: ['70%','60%']
        ,shade: 0.5
        ,id: 'createModel' //设定一个id，防止重复弹出
        ,btnAlign: 'c'
        ,moveType: false //拖拽模式，0或者1
        ,content: '/bpm/deployed/nodeIndex'
    });
}