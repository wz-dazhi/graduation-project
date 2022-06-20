layui.config({
    base: '../static/js/common/'
});

const BASE_URI = '/api/bicycle';

layui.use(['table', 't', 'http', 'verify'], () => {
    const table = layui.table,
        form = layui.form,
        t = layui.t,
        http = layui.http,
        laydate = layui.laydate,
        upload = layui.upload,
        element = layui.element;
    const $ = layui.$, active = {
        reload: () => {
            table.reload('listReload', {
                page: {
                    curr: 1
                }, where: {
                    name: $('#searchName').val(),
                    categoryName: $('#searchCategory').val()
                }
            }, 'data');
        }
    };
    laydate.render({
        elem: '#inTime',
        type: 'datetime'
    });

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
                area: ["30%"],
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
            name: f.name,
            cid: f.cid,
            img: f.img,
            state: f.state,
            inTime: f.inTime,
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
            if (data.img !== null) {
                $('#uploadImg').attr('src', data.img);
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
            if (data.img !== null) {
                layer.photos({
                    photos: {
                        "title": data.name,
                        "id": data.id,
                        "start": 0,
                        "data": [
                            {
                                "alt": data.name,
                                "pid": data.id,
                                "src": data.img,
                                "thumb": data.img
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
        "name": isNull ? null : data.name,
        "cid": isNull ? null : data.cid,
        "categoryName": isNull ? null : data.categoryName,
        "img": isNull ? null : data.img,
        "state": isNull ? null : data.state,
        "inTime": isNull ? null : data.inTime,
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
            {field: 'id', title: 'ID', align: 'center', sort: true, width: 100},
            {field: 'name', title: '单车名称', align: 'center'},
            {field: 'cid', title: '分类ID', align: 'center'},
            {field: 'categoryName', title: '分类名称', align: 'center'},
            {field: 'img', title: '单车图片', align: 'center', templet: '#imgTpl'},
            {field: 'state', title: '状态', align: 'center', hide: true},
            {field: 'stateDesc', title: '状态', align: 'center', width: 175},
            {field: 'inTime', title: '入库时间', align: 'center', width: 165},
            {field: 'remark', title: '备注', align: 'center'},
            {fixed: 'right', title: '操作', align: 'center', toolbar: '#barOperation', width: 180}
        ]],
        id: 'listReload',
        page: true,
        width: '100%',
        height: 'full-110',
        title: '单车列表',
        toolbar: '#toolbar',
        totalRow: true,
        defaultToolbar: [{title: '添加', layEvent: 'add', icon: 'layui-icon-add-1'}, 'exports']
    });
}