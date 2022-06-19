layui.config({
    base: '../static/js/common/'
});

const BASE_URI = '/api/category';

layui.use(['table', 't', 'http'], () => {
    const table = layui.table, form = layui.form, t = layui.t, http = layui.http;
    const $ = layui.$, active = {
        reload: () => {
            table.reload('categoryReload', {
                page: {
                    curr: 1
                },
                where: {
                    name: $('#searchReload').val()
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
    table.on('toolbar(category_list)', obj => {
        let e = obj.event;
        if (e === 'add') {
            editor(form, null);
            layer.open({
                type: 1,
                title: "添加",
                area: ["30%"],
                content: $("#editor-category")
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
        console.log(f);
        let uri = BASE_URI + (f.id === null || f.id === '' ? "/add" : "/update");
        http.post(uri, {
            id: f.id,
            name: f.name,
            remain: f.remain,
            price: f.price,
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
    table.on('tool(category_list)', obj => {
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
            editor(form, data);
            layer.open({
                type: 1,
                title: "编辑",
                area: ["30%"],
                content: $("#editor-category")
            });
        }
    });
}

function editor(form, data) {
    let isNull = data === null;
    form.val("editorForm", {
        "id": isNull ? null : data.id,
        "name": isNull ? null : data.name,
        "remain": isNull ? null : data.remain,
        "price": isNull ? null : data.price,
        "realRent": isNull ? null : data.realRent,
        "cash": isNull ? null : data.cash,
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
        elem: '#category_list',
        url: BASE_URI + '/page',
        cellMinWidth: 173,
        cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'id', title: 'ID', align: 'center', sort: true, totalRowText: '合计'},
            {field: 'name', title: '品牌名称', align: 'center'},
            {field: 'remain', title: '剩余数量', align: 'center', totalRow: true, sort: true},
            {field: 'price', title: '采购单价(元)', align: 'center', totalRow: true},
            {field: 'realRent', title: '租金(元/小时)', align: 'center', totalRow: true},
            {field: 'cash', title: '押金(元)', align: 'center', totalRow: true},
            {field: 'remark', title: '备注', align: 'center'},
            {fixed: 'right', title: '操作', align: 'center', toolbar: '#barOperation'}
        ]],
        id: 'categoryReload',
        page: true,
        width: '100%',
        height: 'full-110',
        title: '分类列表',
        toolbar: '#toolbar',
        totalRow: true,
        defaultToolbar: [{title: '添加', layEvent: 'add', icon: 'layui-icon-add-1'}, 'exports']
    });
}