window.onload=function () {
    const E = window.wangEditor
    const editor = new E('#div1')
    const text1 = $('#text1')

    // ��ر仯��ͬ�����µ� textarea
    editor.config.onchange = function (html) {
        text1.val(html)
    }
    // ���� server �ӿڵ�ַ
    editor.config.uploadImgServer = 'upload-img.do';
    // ����altѡ��
    editor.config.showLinkImgAlt = false;
    // ���ó�����
    editor.config.showLinkImgHref = false;
    editor.create();
    // ��ʼ�� textarea ��ֵ
    text1.val(editor.txt.html())

    //����ͼƬ��ʾ
    $("#btn1").click(function(){
        // var text = "<p>div2</p>";
        var text = "<p><img src=\"../../upload/�ϼ���-����Ῠ.jpg\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>";
        // $("#div1").innerHTML += "<p>��ӭʹ��</p>";
        document.getElementById("div2").innerHTML += text;
    });
}