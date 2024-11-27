const btf = {
    animateIn: (ele, text) => {
        ele.css({
            display: 'block',
            animation: text
        });
    },

    animateOut: (ele, text) => {
        $(ele).on('animationend', function() {
            $(this).css({
                'display': 'none',
                'animation': ''
            }).off('animationend');
        });
        $(ele).css('animation', text);
    }
}