layui.config({
    base: '../static/js/common/'
});

layui.use(['form', 'verify', 'http'], function () {
    const form = layui.form;
    const http = layui.http;

    form.on('submit(doLogin)', function (data) {
        let f = data.field;
        http.post('/api/user/login', {
            username: f.username,
            password: f.password
        }).then(r => {
            location.href = '/home.html';
        }).catch(e => {
            console.log(e);
            return false;
        });
        return false;
    });

});