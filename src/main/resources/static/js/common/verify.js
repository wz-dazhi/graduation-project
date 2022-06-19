layui.define(['form'], function (exports) {
    const form = layui.form;

    form.verify({
        password: [
            /^[\S]{6,12}$/,
            '密码必须6到12位，且不能出现空格'
        ],
        file: (value, item) => {
            if (value === null || value === '') {
                return '文件不能为空';
            }
        }
    });
    exports('verify');
});