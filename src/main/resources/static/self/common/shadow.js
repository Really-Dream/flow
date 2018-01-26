/**
 * 显示loading画面
 * @return
 */
function showProcessing() {
    $("body").append("<div id=\"processingdiv\" class='shadow-mark' style='height:"+$(window).height()+"px'></div>");
}

/**
 * 关闭loading画面
 * @param desc
 * @return
 */
function hideProcessing() {
    $("#processingdiv").remove();
}