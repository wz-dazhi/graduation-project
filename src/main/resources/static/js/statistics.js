layui.config({
    base: '../static/js/common/'
});

const BASE_URI = '/api/statistics';

Date.prototype.Format = function (fmt) {
    const o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (const k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

layui.use(['util', 'http'], () => {
    const util = layui.util, $ = layui.$, http = layui.http;
    util.countdown(new Date(2099, 1, 1).getTime(), null, (date, serverTime, timer) => {
        const currentTime = new Date().Format("yyyy-MM-dd hh:mm:ss");
        $('#current_date').html(currentTime);
    });

    bicycle(http);
    student(http);
    income(http);
    http.get(BASE_URI + '/total', null, false).then(r => {
        let html;
        if (r.data === null) {
            html = '当月收入: 0.00 <br/>总收入: 0.00';
        } else {
            html = '当月收入: ' + r.data.currentMonthTotal.toFixed(2) + '<br/>总收入: ' + r.data.total.toFixed(2);
        }
        $('#total').html(html);
    })
});

function bicycle(http) {
    http.get(BASE_URI + '/bicycle', null, false).then(r => {
        let data = r.data;
        let option = {
            title: {
                text: '车辆',
                left: 'center',
                top: 'center'
            },
            series: [
                {
                    type: 'pie',
                    label: {
                        show: true,
                        formatter: arg => {
                            return arg.data.name + ": " + arg.data.value;
                        }
                    },
                    data: data,
                    roseType: 'area',
                    radius: ['20%', '70%']
                }
            ]
        };

        initEcharts('bicycle', option);
    });
}

function student(http) {
    http.get(BASE_URI + '/student', null, false).then(r => {
        let data = r.data;
        let option = {
            title: {
                left: 'center',
                top: 'center'
            },
            series: [
                {
                    type: 'pie',
                    label: {
                        show: true,
                        formatter: arg => {
                            return arg.data.name + ": " + arg.data.value;
                        }
                    },
                    data: data
                }
            ]
        };

        initEcharts('student', option);
    });
}

function income(http) {
    http.get(BASE_URI + '/income', null, false).then(r => {
        let data = r.data;
        let option = {
            xAxis: {
                data: data.names
            },
            yAxis: {},
            series: [
                {
                    data: data.values,
                    type: 'line',
                    smooth: true,
                    label: {
                        show: true,
                        position: 'bottom',
                        textStyle: {
                            fontSize: 12
                        }
                    }
                }
            ]
        };

        initEcharts('income', option);
    });
}

function initEcharts(elementId, option) {
    const myChart = echarts.init(document.getElementById(elementId));
    myChart.setOption(option)
}