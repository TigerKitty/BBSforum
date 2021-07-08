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
    //通过地址栏传过来的信息自动向后端发起请求获取帖子,个人信息,评论信息。渲染页面
    // $.get('viewPost.do',{pid:args.pid}),success(function(back){
    //     console.log("请求成功"+back);
    // }).complete(function(back){
    //     console.log("请求完成"+back);
    // }).error(function(xhr,errorText,errorType){
    //     console.log("请求访问帖子数据失败");
    // });
    $.ajax({
        type:"get",
        url:"viewPost.do",
        dataType:"text",
        data:{pid:args.pid},
        success:function(back){
            console.log(back);
            var obj = eval( "(" + back + ")" );//转换后的JSON对象
            document.getElementById("typeMes").innerHTML += obj.post.ptype;
            document.getElementById("titleMes").innerHTML += obj.post.ptitle;
            document.getElementById("timeMes").innerHTML += obj.post.ptime;
            document.getElementById("post").innerHTML += obj.post.pcontent;
            document.getElementById("perMesName").innerHTML += obj.user.realname;
            document.getElementById("perMesMajor").innerHTML += obj.user.major;
            document.getElementById("perMesCollege").innerHTML += obj.user.college;
            //修改页面title
            $(document).attr('title',obj.ptitle);

        },
        error:function(){
            console.log("请求访问帖子数据失败");
        }
    });
}