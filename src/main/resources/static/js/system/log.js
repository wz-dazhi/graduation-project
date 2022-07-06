layui.config({
    base: '../static/js/common/'
});

const BASE_URI = '/api/log';

layui.use(['table', 't', 'http'], () => {
    const table = layui.table, t = layui.t;
    const $ = layui.$, active = {
        reload: () => {
            table.reload('reload', {
                page: {
                    curr: 1
                },
                where: {
                    operatorName: $('#operatorName').val(),
                    msg: $('#msg').val()
                }
            }, 'data');
        }
    };
    list(t);
    search(active, $);
});

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
        cellMinWidth: 173,
        cols: [[
            {field: 'operatorName', title: '品牌名称', align: 'center'},
            {field: 'msg', title: '备注', align: 'center', width: 350},
        ]],
        id: 'reload',
        page: true,
        width: '100%',
        height: 'full-110',
        title: '日志列表',
        toolbar: '#toolbar',
        totalRow: true
    });
}