var params = new URLSearchParams(window.location.search);
var id = params.get('id');
var privateKey = params.get('privateKey');
// function downloadImg(url) {
//     fetch(url)
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Network response was not ok ' + response.statusText);
//             }
//             return response.blob(); // 将响应体转换为 Blob 对象
//         })
//         .then(blob => {
//             // 创建一个临时的 URL
//             const url = window.URL.createObjectURL(blob);
//             // 创建一个隐藏的 <a> 元素
//             const a = document.createElement('a');
//             a.style.display = 'none';
//             a.href = url;
//             a.download = '认证码.png'; // 设置下载的文件名
//             // 将 <a> 元素添加到文档中
//             document.body.appendChild(a);
//
//             // 触发点击事件
//             a.click();
//
//             // 清理：移除 <a> 元素并释放 URL 对象
//             document.body.removeChild(a);
//             window.URL.revokeObjectURL(url);
//         })
//         .catch(error => {
//             console.error('There has been a problem with your fetch operation:', error);
//         });
// }

let code = -1

function showAddDialog() {
    btf.animateIn($('.mask'), 'mask_show 0.25s');
    btf.animateIn($('#addDialog'), 'dialog_show .25s cubic-bezier(0.22, 0.61, 0.01, 0.98)');
}

function showTradeDialog() {
    btf.animateIn($('.mask'), 'mask_show 0.25s');
    btf.animateIn($('#addTradeDialog'), 'dialog_show .25s cubic-bezier(0.22, 0.61, 0.01, 0.98)');
}

function init() {
    $.ajax({
        url: '/queryAllCodeByMusicId', // API 的 URL 地址
        type: 'POST', // 请求类型，可以是 GET, POST, PUT, DELETE 等
        contentType: 'application/json',
        data: JSON.stringify({
            id,
            privateKey
        }),
        success: function(data) {
            const { o } = data;
            // 在这里处理从服务器返回的数据
            code = o.code;
            $('#authCode').text(o.code);
            $('#id').text(o.id);
            $('#musicName').text(o.musicName);
            $('#artistName').text(o.artistName);
            $('#address').text(o.address);
            $('#createTime').text(o.createTime);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // 处理请求失败的情况
            console.error('Error: ' + textStatus + ' ' + errorThrown);
        }
    });
}

let loading = false;
function handleConfirm() {
    if(loading) return;
    loading = true;
    $.ajax({
        url: '/toChain', // API 的 URL 地址
        type: 'POST', // 请求类型，可以是 GET, POST, PUT, DELETE 等
        contentType: 'application/json',
        data: JSON.stringify({
            privateKey,
            code
        }),
        success: function(data) {
            // 在这里处理从服务器返回的数据
            const { code, msg } = data;
            if(code === '1') {
                closeAddDialog();
                alert('新增成功');
            } else  {
                alert(msg);
            }
            loading = false;
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // 处理请求失败的情况
            console.error('Error: ' + textStatus + ' ' + errorThrown);
            loading = false;
        }
    });
}

function tradeConfirm() {
    if(loading) return;
    loading = true;
    to = $('#toAddress').val();
    content = $('#tradeContent').val();
    $.ajax({
        url: '/trade', // API 的 URL 地址
        type: 'POST', // 请求类型，可以是 GET, POST, PUT, DELETE 等
        contentType: 'application/json',
        data: JSON.stringify({
            privateKey,
            code,
            to,
            content
        }),
        success: function(data) {
            // 在这里处理从服务器返回的数据
            const { code, msg } = data;
            if(code === '1') {
                closeTradeDialog();
                alert('交易成功');
            } else  {
                alert(msg);
            }
            loading = false;
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // 处理请求失败的情况
            console.error('Error: ' + textStatus + ' ' + errorThrown);
            loading = false;
        }
    });
}

function closeAddDialog() {
    btf.animateOut($('#addDialog'), 'dialog_dismiss .25s cubic-bezier(0.22, 0.61, 0.01, 0.98)');
    btf.animateOut($('.mask'), 'mask_dismiss 0.25s');
}

function closeTradeDialog() {
    $('#tradeContent').val('');
    $('#toAddress').val('');
    btf.animateOut($('#addTradeDialog'), 'dialog_dismiss .25s cubic-bezier(0.22, 0.61, 0.01, 0.98)');
    btf.animateOut($('.mask'), 'mask_dismiss 0.25s');
}

init()