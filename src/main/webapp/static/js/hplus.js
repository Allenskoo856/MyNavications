//自定义js - MyNavications项目侧边栏菜单

$(document).ready(function () {
    // 初始化MetisMenu
    $('#side-menu').metisMenu();
    
    // 点击菜单时的高亮处理
    $("#side-menu a.smooth").click(function(){
        // 移除所有活动状态
        $("#side-menu li").removeClass("active");
        // 为当前点击的菜单项添加活动状态
        $(this).closest("li").addClass("active");
    });

    // iOS浏览器兼容性处理
    if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
        $('#content-main').css('overflow-y', 'auto');
    }
});

// 响应式处理
$(window).bind("load resize", function () {
    if ($(this).width() < 769) {
        $('body').addClass('mini-navbar');
    }
});
