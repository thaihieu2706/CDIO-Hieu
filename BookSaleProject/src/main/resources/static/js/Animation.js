console.clear();

$(document).on('click', '.add_to_cart', function (e) {
    const p = $(this).parent();
    console.log({ p })
    const c = p.find('img').clone();
    c.css({
        position: 'absolute',
        top: p.offset().top,
        left: p.offset().left,
        width: p.width(),
        height: p.height(),
        zIndex: 99999
    })
    const dest = $('#panier')
    $('.container').append(c);
    c.animate({
        top: dest.offset().top + dest.height() / 2,
        left: dest.offset().left + dest.width() / 2,
        width: 0,
        height: 0,
        opacity: 0
    }, 600, function () { c.remove() });
});