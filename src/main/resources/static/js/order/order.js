layui.config({
    base: '../static/js/common/'
});

const BASE_URI = '/api/order';

layui.use(['table', 't', 'http', 'verify'], () => {
    const table = layui.table,
        form = layui.form,
        t = layui.t,
        http = layui.http;
    const $ = layui.$, active = {
        reload: () => {
            table.reload('listReload', {
                page: {
                    curr: 1
                }, where: {
                    id: $('#searchId').val(),
                    sid: $('#searchSid').val(),
                    bid: $('#searchBid').val(),
                    studentName: $('#searchStudentName').val()
                }
            }, 'data');
        }
    };

    list(t);
    search(active, $);
    add(layer, table, form, active, $, http);
    operate(table, layer, active, http, $, form);
});

function add(layer, table, form, active, $, http) {
    table.on('toolbar(list)', obj => {
        let e = obj.event;
        if (e === 'add') {
            form.val("editorForm", {
                "id": null,
                "bid": null,
                "sid": null,
                "realRent": null,
                "cash": null,
                "remark": null
            });
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
                del(layer, http, active, ids);
            } else {
                layer.msg("请选择要删除的ID");
            }
        }
    });

    form.on('submit(editor-filter)', data => {
        let f = data.field;
        http.post(BASE_URI + "/add", {
            id: f.id,
            bid: f.bid,
            sid: f.sid,
            realRent: f.realRent,
            cash: f.cash,
            remark: f.remark
        }).then(r => {
            layer.closeAll();
            active.reload();
        });
        return false;
    });
}

function operate(table, layer, active, http, $, form) {
    table.on('tool(list)', obj => {
        const data = obj.data;
        const layEvent = obj.event;

        if (layEvent === 'del') {
            del(layer, http, active, [data.id]);
        } else if (layEvent === 'isReturn') {
            form.val("returnForm", {
                "id": data.id,
                "state": data.state,
                "remark": data.remark
            });
            layer.open({
                type: 1,
                title: "是否归还",
                area: ['30%', '53%'],
                content: $("#doReturn")
            });
        }
    });

    form.on('submit(return-filter)', data => {
        let f = data.field;
        //console.log("归还状态变更", f)
        http.post(BASE_URI + "/state_update", {
            id: f.id,
            state: f.state,
            remark: f.remark
        }).then(r => {
            layer.closeAll();
            active.reload();
        });
        return false;
    });
}

function del(layer, http, active, ids) {
    layer.confirm('真的删除么', index => {
        http.del(BASE_URI + '/del', {
            ids: ids
        }).then(r => {
            active.reload();
        });
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
        cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'id', title: 'ID', align: 'center', sort: true, width: 60},
            {field: 'bid', title: '单车ID', align: 'center', width: 80},
            {field: 'sid', title: '学生ID', align: 'center', width: 80},
            {field: 'studentName', title: '学生姓名', align: 'center', width: 110},
            {field: 'bicycleName', title: '单车名称', align: 'center', width: 100},
            {field: 'cash', title: '押金(元)', align: 'center', width: 100, totalRow: true},
            {field: 'realRent', title: '租金(元/小时)', align: 'center', width: 115, totalRow: true},
            {field: 'borrowTime', title: '出借时间', align: 'center', width: 165},
            {field: 'returnTime', title: '归还时间', align: 'center', width: 165},
            {field: 'state', title: '状态', align: 'center', width: 130},
            {field: 'remark', title: '备注', align: 'center', width: 130},
            {fixed: 'right', title: '操作', align: 'center', toolbar: '#barOperation', width: 150}
        ]],
        id: 'listReload',
        page: true,
        width: '100%',
        height: 'full-110',
        title: '订单列表',
        toolbar: '#toolbar',
        totalRow: true,
        defaultToolbar: [{title: '添加', layEvent: 'add', icon: 'layui-icon-add-1'}, 'exports']
    });
}