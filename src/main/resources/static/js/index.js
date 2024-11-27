let selectedItem, musicId = -1

function musicItemSelected(element, id){
    if(selectedItem != null){
        selectedItem.removeClass("coBodyItemSelected");
    }
    selectedItem = $(element);
    selectedItem.addClass("coBodyItemSelected");
    musicId = id;
}

function showCodeDialog() {
    if(musicId === -1){
        alert("请选择一首歌曲");
        return;
    }
    setTimeout(() => $('#codePrivateKey').focus());
    btf.animateIn($('.mask'), 'mask_show 0.25s');
    btf.animateIn($('#addCodeDialog'), 'dialog_show .25s cubic-bezier(0.22, 0.61, 0.01, 0.98)');
}

function jumpCodeDialog() {
    if(musicId === -1){
        alert("请选择一首歌曲");
        return;
    }
    setTimeout(() => $('#jumpCodePrivateKey').focus());
    btf.animateIn($('.mask'), 'mask_show 0.25s');
    btf.animateIn($('#jumpCodeDialog'), 'dialog_show .25s cubic-bezier(0.22, 0.61, 0.01, 0.98)');
}

function init() {
    $.ajax({
        url: '/queryMusics', // API 的 URL 地址
        type: 'POST', // 请求类型，可以是 GET, POST, PUT, DELETE 等
        success: function (data) {
            // 在这里处理从服务器返回的数据
            const {o} = data
            let html = ''
            o.forEach(item => {
                const str = `
                        <div class="coBodyItem" onclick="musicItemSelected(this, ${item.id})">
                            <div class="item">${item.id}</div>
                            <div class="item">${item.musicName}</div>
                            <div class="item">${item.albumName}</div>
                            <div class="item">${item.artistName}</div>
                            <div class="item">${item.address}</div>
                            <div class="item">${item.createTime}</div>
                        </div>`
                html += str
            })

            $('.coBody').html(html)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // 处理请求失败的情况
            console.error('Error: ' + textStatus + ' ' + errorThrown);
        }
    });
}

function showMusicDialog() {
    setTimeout(() => $('#musicName').focus());
    btf.animateIn($('.mask'), 'mask_show 0.25s');
    btf.animateIn($('#addMusicDialog'), 'dialog_show .25s cubic-bezier(0.22, 0.61, 0.01, 0.98)');
}

let loading = false

function handleConfirm() {
    if (loading) return
    loading = true
    const musicName = $('#musicName').val()
    const albumName = $('#albumName').val()
    const artistName = $('#artistName').val()
    const privateKey = $('#privateKey').val()
    const address = $('#address').val()
    $.ajax({
        url: '/music/add', // API 的 URL 地址
        type: 'POST', // 请求类型，可以是 GET, POST, PUT, DELETE 等
        contentType: 'application/json',
        data: JSON.stringify({
            musicName,
            albumName,
            artistName,
            privateKey,
            address
        }),
        success: function (data) {
            // 在这里处理从服务器返回的数据
            const {code, msg} = data
            if (code === '1') {
                closeMusicDialog()
                init()
                alert('新增成功')
            } else {
                alert(msg)
            }
            loading = false
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // 处理请求失败的情况
            console.error('Error: ' + textStatus + ' ' + errorThrown);
            loading = false
        }
    });
}

function closeMusicDialog() {
    $('#musicName').val('');
    $('#albumName').val('');
    $('#artistName').val('');
    $('#privateKey').val('');
    $('#address').val('');
    btf.animateOut($('#addMusicDialog'), 'dialog_dismiss .25s cubic-bezier(0.22, 0.61, 0.01, 0.98)');
    btf.animateOut($('.mask'), 'mask_dismiss 0.25s');
}

let codeLoading = false

function addCode() {
    if (codeLoading) return
    codeLoading = true
    const codePrivateKey = $('#codePrivateKey').val()
    $.ajax({
        url: '/createCode', // API 的 URL 地址
        type: 'POST', // 请求类型，可以是 GET, POST, PUT, DELETE 等
        contentType: 'application/json',
        data: JSON.stringify({
            id: musicId,
            privateKey: codePrivateKey
        }),
        success: function (data) {
            // 在这里处理从服务器返回的数据
            const {code, msg} = data
            if (code === '1') {
                closeCodeDialog()
                alert('新增成功')
            } else {
                alert(msg)
            }
            codeLoading = false
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // 处理请求失败的情况
            console.error('Error: ' + textStatus + ' ' + errorThrown);
            codeLoading = false
        }
    })
}

function closeCodeDialog() {
    $('#codePrivateKey').val('')
    btf.animateOut($('#addCodeDialog'), 'dialog_dismiss .25s cubic-bezier(0.22, 0.61, 0.01, 0.98)');
    btf.animateOut($('.mask'), 'mask_dismiss 0.25s');
}

function closeJumpCodeDialog() {
    $('#jumpCodePrivateKey').val('')
    btf.animateOut($('#jumpCodeDialog'), 'dialog_dismiss .25s cubic-bezier(0.22, 0.61, 0.01, 0.98)');
    btf.animateOut($('.mask'), 'mask_dismiss 0.25s');
}

function jumpCode() {
    const privateKey = $('#jumpCodePrivateKey').val()
    window.location.href = `/code?id=${musicId}&privateKey=${privateKey}`
    closeJumpCodeDialog()
}

init()