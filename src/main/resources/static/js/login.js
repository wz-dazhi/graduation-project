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
            let u = r.data;
            save(layui, 'name', u.name);
            save(layui, 'username', u.username);
            save(layui, 'avatar', u.avatar);
            location.href = '/home.html';
        }).catch(e => {
            console.log(e);
            return false;
        });
        return false;
    });

});

function save(layui, key, value) {
    layui.sessionData('u', {key: key, value: value});
}