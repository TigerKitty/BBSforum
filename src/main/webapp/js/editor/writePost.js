window.onload=function () {
    const E = window.wangEditor
    const editor = new E('#div1')
    const text = $('#text')

    // 监控变化，同步更新到 textarea
    editor.config.onchange = function (html) {
        text.val(html)
    }
    // 配置 server 接口地址
    editor.config.uploadImgServer = 'upload-img.do';
    // 配置alt选项
    editor.config.showLinkImgAlt = false;
    // 配置超链接
    editor.config.showLinkImgHref = false;
    editor.create();
    // 初始化 textarea 的值
    text.val(editor.txt.html())

    //获取当前时间
    function getCurrentTime(){
        let now= new Date();
        let _month = ( 10 > (now.getMonth()+1) ) ? '0' + (now.getMonth()+1) : now.getMonth()+1;
        let _day = ( 10 > now.getDate() ) ? '0' + now.getDate() : now.getDate();
        let _hour = ( 10 > now.getHours() ) ? '0' + now.getHours() : now.getHours();
        let _minute = ( 10 > now.getMinutes() ) ? '0' + now.getMinutes() : now.getMinutes();
        let _second = ( 10 > now.getSeconds() ) ? '0' + now.getSeconds() : now.getSeconds();
        return now.getFullYear() + '-' + _month + '-' + _day + ' ' + _hour + ':' + _minute + ':' + _second;
    }
    //发表帖子
    $("#submit").click(function () {
        var type = $("#selectType").val();
        var title = $("#title").val();
        var content = $("#text").val();
        var time = getCurrentTime();
        $.post('publishPost.do',{type:type,title:title,content:content,time:time},function (back) {
            console.log(back);
        });
    });
}