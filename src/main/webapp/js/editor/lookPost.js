window.onload=function () {
    const E = window.wangEditor
    const editor = new E('#discuss');
    const text1 = $('#text1')
    // 监控变化，同步更新到 textarea
    editor.config.onchange = function (html) {
        text1.val(html)
    }
    editor.config.height = 50
    //只需要表情菜单
    editor.config.menus = ['emoticon']
    editor.config.showFullScreen = false
    editor.create();
}