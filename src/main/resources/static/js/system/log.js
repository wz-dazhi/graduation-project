layui.config({
    base: '../static/js/common/'
});

const BASE_URI = '/api/log';

layui.use(['table', 't', 'http'], () => {
    const table = layui.table, t = layui.t;
    const $ = layui.$, active = {
        reload: () => {
            table.reload('listReload', {
                page: {
                    curr: 1
                },
                where: {
                    operatorName: $('#searchOperatorName').val(),
                    msg: $('#searchMsg').val()
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
        cols: [[
            {field: 'operatorName', title: '操作人', align: 'center', width: 260},
            {field: 'msg', title: '备注', align: 'center', width: 1183},
        ]],
        id: 'listReload',
        page: true,
        width: '100%',
        height: 'full-110',
        title: '日志列表',
        // toolbar: '#toolbar',
        totalRow: true
    });
}