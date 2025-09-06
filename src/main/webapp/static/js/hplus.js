//自定义js - MyNavications项目侧边栏菜单

$(document).ready(function () {
    // 初始化MetisMenu
    $('#side-menu').metisMenu();
    
    // 注释掉重复的点击事件处理，现在由index.html统一处理
    // 原有的点击菜单时的高亮处理已移至index.html中优化处理

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
