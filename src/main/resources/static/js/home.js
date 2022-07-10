layui.config({
    base: '../static/js/common/'
});

layui.use(['element', 'layer', 'http'], function () {
    const element = layui.element, $ = layui.$, http = layui.http;
    let u = layui.sessionData('u');
    if ($.isEmptyObject(u)) {
        location.href = '/login.html';
        return;
    }
    $('#avatar').attr('src', u.avatar).attr('alt', u.username);
    $('#name').html(u.name);

    element.on('nav(menuId)', function (elem) {
        // 找到所有兄弟节点, 移除layui-nav-itemed
        elem.parent("li").siblings().removeClass("layui-nav-itemed");
        elem.parent("li").addClass("layui-nav-itemed");
        $("#body-content").attr("src", elem.attr("data-url"));
    });

    element.on('nav(quit)', function (elem) {
        let uri = elem.attr("data-url");
        if (null === uri || undefined === uri || "" === uri) {
            return;
        }
        layer.confirm('确定要退出吗?', index => {
            http.get(uri).then(r => {
                layui.sessionData('u', null);
                location.href = '/login.html';
            });
        });
    });
});