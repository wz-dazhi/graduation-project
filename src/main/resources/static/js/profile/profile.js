layui.config({
    base: '../static/js/common/'
});

const BASE_URI = '/api/user/change/pass';

layui.use(['form', 'http', 'verify'], () => {
    const form = layui.form, http = layui.http, verify = layui.verify;

    form.on('submit(editor-filter)', data => {
        let f = data.field;
        http.post(BASE_URI, {
            password: f.password,
            newPassword: f.newPassword,
            reNewPassword: f.reNewPassword
        }).then(r => {
            console.log(r);
            // if (r.success) {
            //     location.href = '/login.html';
            // }
        });
        return false;
    });
});