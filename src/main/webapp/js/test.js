window.onload=function () {
    const E = window.wangEditor;
    const editor = new E('#div1');

    // 配置 server 接口地址
    editor.config.uploadImgServer = 'html/upload-img.do';
    // 配置alt选项
    editor.config.showLinkImgAlt = false;
    // 配置超链接
    editor.config.showLinkImgHref = false;
    editor.create();
}