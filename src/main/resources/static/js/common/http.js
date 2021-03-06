layui.define(["layer"], function (exports) {
    const layer = layui.layer;

    const http = {

            get(uri, data, successMsg = true) {
                return this.request(this.params(uri, data), {method: 'GET'}, successMsg);
            },

            post(uri, data, successMsg = true) {
                return this.request(uri, {
                    method: 'POST',
                    body: JSON.stringify(data),
                    headers: {
                        "Content-type": "application/json; charset=UTF-8"
                    }
                }, successMsg);
            },

            put(uri, data, successMsg = true) {
                return this.request(uri, {
                    method: 'PUT',
                    body: JSON.stringify(data),
                    headers: {
                        "Content-type": "application/json; charset=UTF-8"
                    }
                }, successMsg);
            },

            del(uri, data, successMsg = true) {
                return this.request(uri, {
                    method: 'DELETE',
                    body: JSON.stringify(data),
                    headers: {
                        "Content-type": "application/json; charset=UTF-8"
                    }
                }, successMsg);
            },

            request(uri, init, successMsg = true) {
                let index = layer.load(2, {
                    shade: [0.2, '#393D49']
                });
                return new Promise((resolve, reject) => {
                    fetch(uri, init).then(r => r.json())
                        .then(res => {
                            layer.close(index);
                            if (res.success) {
                                if (successMsg) {
                                    layer.msg(res.msg, {icon: 6, time: 1000});
                                }
                                resolve(res);
                            } else {
                                layer.msg(res.msg, {icon: 5, time: 1000});
                            }
                        }).catch(err => {
                        layer.close(index);
                        layer.msg(err, {icon: 5, time: 1000});
                        reject(err);
                    });
                });
            },

            params(uri, data) {
                if (data) {
                    let paramsArray = [];
                    Object.keys(data).forEach(key => paramsArray.push(key + '=' + data[key]));
                    if (uri.search(/\?/) === -1) {
                        uri += '?' + paramsArray.join('&')
                    } else {
                        uri += '&' + paramsArray.join('&')
                    }
                }
                return uri;
            }
        }
    ;

    exports('http', http);
});
