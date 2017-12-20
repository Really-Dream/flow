function editorModel(id){
    window.open("/modeler.html?modelId="+id);
}

function deploy(id){
    shadow_();
    $.getJSON("/bpm/model/deploy?modelId="+id,function (data) {
        alertText(data);
        modelList();
    })
};

function editXML(id){
    var bpmnXML;
    $("#modelId").val(id);

    $.ajax({
        url: "/bpm/model/getBpmnXML",
        data:{rand:Math.random(),modelId:id},
        dataType: "json",
        async: false,
        success: function(data){
            bpmnXML = data;
        },
        error:function () {
            bpmnXML = "获取数据失败！";
        }
    });

    create = layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: ['70%','60%']
        ,shade: 0.5
        ,id: 'createModel' //设定一个id，防止重复弹出
        ,btnAlign: 'c'
        ,moveType: false //拖拽模式，0或者1
        ,content: $("#editXML").html()
    });

    $("#createModel").find("textarea").each(function(){
        $(this).val(bpmnXML);
    });
};

function saveModelXML(){
    shadow_();
    var html;
    $("#createModel").find("textarea").each(function(){
        html = $(this).val();
    });
    $.ajax({
        type: "post",
        url: "/bpm/model/saveModelXML",
        data: {
            modelId : $("#modelId").val(),
            bpmnXML : html
        },
        dataType: "json",
        async: false,
        success: function(data){
            alertText(data);
            modelList();
        },
        error:function (data) {
            alertText(data);
            modelList();
        }
    });
}

function exportXML(id){
    location.href = "export?modelId="+id;
};

function deleteModel(id){
    shadow_();
    $.getJSON("/bpm/model/delete?modelId="+id,function(data) {
        alertText(data);
        modelList();
    })
};

function createModel(){
    //示范一个公告层
    create = layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: '30%'
        ,shade: 0.5
        ,id: 'createModel' //设定一个id，防止重复弹出
        ,btnAlign: 'c'
        ,moveType: false //拖拽模式，0或者1
        ,content: $("#info").html()
    });
};