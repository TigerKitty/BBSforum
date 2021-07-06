window.onload=function () {
    const E = window.wangEditor
    const editor = new E('#div1')
    const text1 = $('#text1')

    // 监控变化，同步更新到 textarea
    editor.config.onchange = function (html) {
        text1.val(html)
    }
    // 配置 server 接口地址
    editor.config.uploadImgServer = 'upload-img.do';
    // 配置alt选项
    editor.config.showLinkImgAlt = false;
    // 配置超链接
    editor.config.showLinkImgHref = false;
    editor.create();
    // 初始化 textarea 的值
    text1.val(editor.txt.html())

    //测试图片显示
    $("#btn1").click(function(){
        // var text = "<p>div2</p>";
        var text = "<p><img src=\"../../upload/毕加索-格尔尼卡.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>";
        // $("#div1").innerHTML += "<p>欢迎使用</p>";
        document.getElementById("div2").innerHTML += text;
    });
}