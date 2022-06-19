layui.define(["table"], function (exports) {
    const table = layui.table;

    const t = {
        rander: (p) => {
            // {elem:'#user_list', url:'/rest/user/list/', method:'get', cols:[[]], id:'userReload', page:true, height:'full-220', width:780}
            //方法级渲染
            table.render({
                elem: p.elem,
                url: p.url,
                method: 'POST',
                request: {
                    pageName: 'current',
                    limitName: 'size'
                },
                contentType: 'application/json',
                cols: p.cols,
                id: p.id,
                page: p.page,
                cellMinWidth: p.cellMinWidth,
                height: p.height,
                width: p.width,
                title: p.title,
                toolbar: p.toolbar,
                totalRow: p.totalRow,
                defaultToolbar: p.defaultToolbar,
                parseData: res => {
                    //console.log(res);
                    return {
                        "code": res.code,
                        "msg": res.msg,
                        "count": res.data.total,
                        "data": res.data.records
                    }
                }
            })
        }
    };

    exports('t', t);
});