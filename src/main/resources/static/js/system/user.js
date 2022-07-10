layui.config({
    base: '../static/js/common/'
});

const BASE_URI = '/api/user';

layui.use(['table', 't', 'http', 'verify'], () => {
    const table = layui.table,
        form = layui.form,
        t = layui.t,
        http = layui.http,
        upload = layui.upload,
        element = layui.element,
        verify = layui.verify,
        $ = layui.$;
    const active = {
        reload: () => {
            table.reload('listReload', {
                page: {
                    curr: 1
                }, where: {
                    name: $('#searchName').val()
                }
            }, 'data');
        }
    };

    uploadImg(upload, layer, element, $);
    list(t);
    search(active, $);
    add(layer, table, form, active, $, http, element);
    operate(table, layer, active, http, $, form, element);
});

function uploadImg(upload, layer, element, $) {
    const uploadInst = upload.render({
        elem: '#uploadBtn',
        url: 'https://httpbin.org/post',
        before: obj => {
            obj.preview((index, file, result) => {
                // console.log('上传完成', result);
                $('#img').val(result);
                $('#uploadImg').attr('src', result);
            });
            element.progress('uploadFilter', '0%');
            layer.msg('上传中', {icon: 16, time: 0});
        }, done: res => {
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            $('#uploadText').html('');
        }, error: () => {
            let demoText = $('#uploadText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs upload-reload">重试</a>');
            demoText.find('.upload-reload').on('click', function () {
                uploadInst.upload();
            });
        }, progress: (n, elem, e) => {
            element.progress('uploadFilter', n + '%');
            if (n === 100) {
                layer.msg('上传完毕', {icon: 1});
            }
        }
    });
}

function add(layer, table, form, active, $, http, element) {
    table.on('toolbar(list)', obj => {
        let e = obj.event;
        if (e === 'add') {
            let imgSrc = $('#uploadImg').attr('src');
            if (imgSrc !== undefined) {
                $('#uploadImg').removeAttr('src');
                $('#uploadText').html('');
                element.progress('uploadFilter', '0%');
            }

            editor(form, null);
            layer.open({
                type: 1,
                title: "添加",
                area: ["35%"],
                content: $("#editor")
            });
        } else if (e === 'batchDel') {
            let array = table.checkStatus(obj.config.id).data;
            let ids = Array.from(array, ({id}) => id);
            if (ids.length !== 0) {
                http.del(BASE_URI + '/del', {
                    ids: ids
                }).then(r => {
                    active.reload();
                });
            } else {
                layer.msg("请选择要删除的ID");
            }
        }
    });

    form.on('submit(editor-filter)', data => {
        let f = data.field;
        let uri = BASE_URI + (f.id === null || f.id === '' ? "/add" : "/update");
        http.post(uri, {
            id: f.id,
            avatar: f.avatar,
            name: f.name,
            username: f.username,
            phone: f.phone,
            remark: f.remark
        }).then(r => {
            layer.closeAll();
            active.reload();
        });
        return false;
    });
}

function operate(table, layer, active, http, $, form, element) {
    table.on('tool(list)', obj => {
        const data = obj.data;
        const layEvent = obj.event;

        if (layEvent === 'del') {
            layer.confirm('真的删除行么', index => {
                http.del(BASE_URI + '/del', {
                    ids: [data.id]
                }).then(r => {
                    active.reload();
                });
            });
        } else if (layEvent === 'edit') {
            if (data.avatar !== null && data.avatar !== '') {
                $('#uploadImg').attr('src', data.avatar);
                $('#uploadText').html('');
                element.progress('uploadFilter', '100%');
            }

            editor(form, data);
            layer.open({
                type: 1,
                title: "编辑",
                area: ["30%"],
                content: $("#editor")
            });
        } else if (layEvent === 'detail') {
            if (data.avatar !== null) {
                layer.photos({
                    photos: {
                        "title": data.name,
                        "id": data.id,
                        "start": 0,
                        "data": [
                            {
                                "alt": data.name,
                                "pid": data.id,
                                "src": data.avatar,
                                "thumb": data.avatar
                            }
                        ]
                    }, anim: 5
                });
            }
        }
    });
}

function editor(form, data) {
    let isNull = data === null;
    form.val("editorForm", {
        "id": isNull ? null : data.id,
        "avatar": isNull ? null : data.avatar,
        "name": isNull ? null : data.name,
        "username": isNull ? null : data.username,
        "phone": isNull ? null : data.phone,
        "remark": isNull ? null : data.remark
    });
}

function search(active, $) {
    $('.searchTable .layui-btn').on('click', function () {
        const type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
}

function list(t) {
    t.rander({
        elem: '#list',
        url: BASE_URI + '/page',
        cellMinWidth: 155,
        cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'id', title: 'ID', align: 'center', sort: true, width: 80},
            {field: 'avatar', title: '头像', align: 'center', templet: '#imgTpl', width: 130},
            {field: 'name', title: '姓名', align: 'center', width: 150},
            {field: 'username', title: '账号', align: 'center', width: 180},
            {field: 'phone', title: '电话', align: 'center'},
            {field: 'loginTime', title: '登录时间', align: 'center', width: 250},
            {field: 'remark', title: '备注', align: 'center', width: 250},
            {fixed: 'right', title: '操作', align: 'center', toolbar: '#barOperation', width: 200}
        ]],
        id: 'listReload',
        page: true,
        width: '100%',
        height: 'full-110',
        title: '用户列表',
        toolbar: '#toolbar',
        totalRow: true,
        defaultToolbar: [{title: '添加', layEvent: 'add', icon: 'layui-icon-add-1'}, 'exports']
    });
}