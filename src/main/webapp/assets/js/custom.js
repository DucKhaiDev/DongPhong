/*
    General
*/
(function ($) {
    "use strict";
    const mainApp = {

        main_fun: function () {
            /*====================================
            METIS MENU
            ======================================*/
            $('#main-menu').metisMenu();

            /*====================================
              LOAD APPROPRIATE MENU BAR
           ======================================*/
            $(window).bind("load resize", function () {
                if ($(this).width() < 768) {
                    $('div.sidebar-collapse').addClass('collapse')
                } else {
                    $('div.sidebar-collapse').removeClass('collapse')
                }
            });

            /*====================================
            MORRIS BAR CHART
         ======================================*/
            Morris.Bar({
                element: 'morris-bar-chart',
                data: [{
                    y: '2006',
                    a: 100,
                    b: 90
                }, {
                    y: '2007',
                    a: 75,
                    b: 65
                }, {
                    y: '2008',
                    a: 50,
                    b: 40
                }, {
                    y: '2009',
                    a: 75,
                    b: 65
                }, {
                    y: '2010',
                    a: 50,
                    b: 40
                }, {
                    y: '2011',
                    a: 75,
                    b: 65
                }, {
                    y: '2012',
                    a: 100,
                    b: 90
                }],
                xkey: 'y',
                ykeys: ['a', 'b'],
                labels: ['Series A', 'Series B'],
                hideHover: 'auto',
                resize: true
            });

            /*====================================
          MORRIS DONUT CHART
       ======================================*/
            Morris.Donut({
                element: 'morris-donut-chart',
                data: [{
                    label: "Download Sales",
                    value: 12
                }, {
                    label: "In-Store Sales",
                    value: 30
                }, {
                    label: "Mail-Order Sales",
                    value: 20
                }],
                resize: true
            });

            /*====================================
         MORRIS AREA CHART
      ======================================*/

            Morris.Area({
                element: 'morris-area-chart',
                data: [{
                    period: '2010 Q1',
                    iphone: 2666,
                    ipad: null,
                    itouch: 2647
                }, {
                    period: '2010 Q2',
                    iphone: 2778,
                    ipad: 2294,
                    itouch: 2441
                }, {
                    period: '2010 Q3',
                    iphone: 4912,
                    ipad: 1969,
                    itouch: 2501
                }, {
                    period: '2010 Q4',
                    iphone: 3767,
                    ipad: 3597,
                    itouch: 5689
                }, {
                    period: '2011 Q1',
                    iphone: 6810,
                    ipad: 1914,
                    itouch: 2293
                }, {
                    period: '2011 Q2',
                    iphone: 5670,
                    ipad: 4293,
                    itouch: 1881
                }, {
                    period: '2011 Q3',
                    iphone: 4820,
                    ipad: 3795,
                    itouch: 1588
                }, {
                    period: '2011 Q4',
                    iphone: 15073,
                    ipad: 5967,
                    itouch: 5175
                }, {
                    period: '2012 Q1',
                    iphone: 10687,
                    ipad: 4460,
                    itouch: 2028
                }, {
                    period: '2012 Q2',
                    iphone: 8432,
                    ipad: 5713,
                    itouch: 1791
                }],
                xkey: 'period',
                ykeys: ['iphone', 'ipad', 'itouch'],
                labels: ['iPhone', 'iPad', 'iPod Touch'],
                pointSize: 2,
                hideHover: 'auto',
                resize: true
            });

            /*====================================
    MORRIS LINE CHART
 ======================================*/
            Morris.Line({
                element: 'morris-line-chart',
                data: [{
                    y: '2006',
                    a: 100,
                    b: 90
                }, {
                    y: '2007',
                    a: 75,
                    b: 65
                }, {
                    y: '2008',
                    a: 50,
                    b: 40
                }, {
                    y: '2009',
                    a: 75,
                    b: 65
                }, {
                    y: '2010',
                    a: 50,
                    b: 40
                }, {
                    y: '2011',
                    a: 75,
                    b: 65
                }, {
                    y: '2012',
                    a: 100,
                    b: 90
                }],
                xkey: 'y',
                ykeys: ['a', 'b'],
                labels: ['Series A', 'Series B'],
                hideHover: 'auto',
                resize: true
            });


        },

        initialization: function () {
            mainApp.main_fun();

        }

    };
    // Initializing ///

    $(document).ready(function () {
        mainApp.main_fun();
    });

}(jQuery));

<!--===============================================================================================-->
$(function () {
    const numProduct = $('.num-product');
    numProduct.each(function () {
        if (parseInt($(this).prop('value')) === 0 || parseInt($(this).next().next().prop('value')) === 1) {
            $(this).addClass('pointer-events-none');
            $(this).prev().addClass('pointer-events-none');
            $(this).next().addClass('pointer-events-none');
        }
    });

    $('.btn-num-product-down').each(function () {
        $(this).on('click', function () {
            if (parseInt($(this).next().prop('value')) < 2) {
                $(this).next().prop('value', '1');
                $(this).addClass('pointer-events-none');
            }
            $(this).next().next().removeClass('pointer-events-none');
        });
    });

    $('.btn-num-product-up').each(function () {
        $(this).on('click', function () {
            if (parseInt($(this).prev().prop('value')) >= parseInt($(this).next().prop('value'))) {
                $(this).prev().prop('value', $(this).next().prop('value'));
                $(this).addClass('pointer-events-none');
            }
            $(this).prev().prev().removeClass('pointer-events-none');
        });
    });

    numProduct.each(function () {
        $(this).on('change', function () {
            const btnDown = $(this).prev();
            const btnUp = $(this).next();
            if (parseInt($(this).prop('value')) < 2) {
                $(this).prop('value', '1');
                btnDown.addClass('pointer-events-none');
            } else if ($(this).prop('value') >= $(this).next().next().prop('value')) {
                $(this).prop('value', $(this).next().next().prop('value'));
                btnUp.addClass('pointer-events-none');
            } else {
                btnDown.removeClass('pointer-events-none');
                btnUp.removeClass('pointer-events-none');
            }
        });
    });
});

<!--===============================================================================================-->
$(function () {
    $('.js-addwish-b2, .js-addwish-detail').on('click', function (e) {
        e.preventDefault();
    });

    $('.js-addwish-b2').each(function () {
        const addwishB2 = $(this);
        const nameProduct = addwishB2.parent().parent().find('.js-name-b2').html();
        addwishB2.on('click', function () {
            if ($('#checkAccount').prop('value') !== 'null') {
                swal(nameProduct, "???? ???????c th??m v??o danh s??ch y??u th??ch!", "success").then(function () {
                    addwishB2.parent().submit();
                });
            } else {
                swal({
                    text: 'Vui l??ng ????ng nh???p ????? ti???p t???c!',
                    icon: 'warning'
                }).then(function () {
                    const base_url = window.location.origin;
                    location.href = base_url + '/login';
                });
            }

            addwishB2.addClass('js-addedwish-b2');
            addwishB2.off('click');
        });
    });

    $('.js-addwish-detail').each(function () {
        const addwishDetail = $(this);
        let nameProduct = '';
        if (addwishDetail.prop('id') !== 'btn-addwishlist-detail') {
            nameProduct = addwishDetail.parent().parent().parent().find('.js-name-detail').children().html();
        } else {
            nameProduct = addwishDetail.parent().parent().parent().find('.js-name-detail').html();
        }

        addwishDetail.on('click', function () {
            if ($('#checkAccount').prop('value') !== 'null') {
                swal(nameProduct, "???? ???????c th??m v??o danh s??ch y??u th??ch!", "success").then(function () {
                    addwishDetail.parent().submit();
                });
            } else {
                swal({
                    text: 'Vui l??ng ????ng nh???p ????? ti???p t???c!',
                    icon: 'warning'
                }).then(function () {
                    const base_url = window.location.origin;
                    location.href = base_url + '/login';
                });
            }

            addwishDetail.addClass('js-addedwish-detail');
            addwishDetail.off('click');
        });
    });

    /*---------------------------------------------*/

    $('.js-addcart-detail').each(function () {
        const addCartDetail = $(this);

        let nameProduct = '';
        if (addCartDetail.prop('id') !== 'btn-addcart-detail') {
            nameProduct = addCartDetail.parent().parent().parent().parent().find('.js-name-detail').children().html();
        } else {
            nameProduct = addCartDetail.parent().parent().parent().parent().find('.js-name-detail').html();
        }

        addCartDetail.on('click', function () {
            if ($('#checkAccount').prop('value') !== 'null') {
                swal(nameProduct, "???? ???????c th??m v??o gi??? h??ng!", "success").then(function () {
                    addCartDetail.parent().submit();
                });
            } else {
                swal({
                    text: 'Vui l??ng ????ng nh???p ????? ti???p t???c!',
                    icon: 'warning'
                }).then(function () {
                    const base_url = window.location.origin;
                    location.href = base_url + '/login';
                });
            }
        });
    });
});

<!--===============================================================================================-->
$(function () {
    const readURL = function (input) {
        if (input.files && input.files[0]) {
            const reader = new FileReader();
            reader.onload = function (e) {
                $('.avatar').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    };

    $(".file-upload").on('change', function () {
        readURL(this);
    });
});

<!--===============================================================================================-->
$(function () {
    //N???u URL kh??ng ch???a parameter th?? x??a h???t parameter trong sessionScope
    const url = new URL(location.href);
    const params = url.searchParams;

    if (!params.has('brand')) {
        $('input[name="brand"]').prop('checked', false).each(function () {
            sessionStorage.removeItem(this.id);
        });
    }

    if (!params.has('category')) {
        $('input[name="category"]').prop('checked', false).each(function () {
            sessionStorage.removeItem(this.id);
        });
    }

    if (!params.has('room')) {
        $('input[name="room"]').prop('checked', false).each(function () {
            sessionStorage.removeItem(this.id);
        });
    }

    if (!params.has('price')) {
        $('input[name="price"]').prop('checked', false).each(function () {
            sessionStorage.removeItem(this.id);
        });
    }

    if (!params.has('rating')) {
        $('input[name="rating"]').prop('checked', false).each(function () {
            sessionStorage.removeItem(this.id);
        });
    }

    if (!params.has('page')) {
        sessionStorage.removeItem('currPage');
    }

    //?????t l???i b??? l???c t??m ki???m
    $('#filter-reset').on('click', function () {
        $('.filter-input').prop('checked', false);
        sessionStorage.clear();
        params.delete('brand');
        params.delete('category');
        params.delete('room');
        params.delete('price');
        params.delete('rating');
        window.location = url.href;
    });
});

<!--===============================================================================================-->
$(function () {
    //append parameter v??o url
    $('input[class="filter-input"]').click(function () {
        let seasoning = '', tempArray = [];
        $('input[name="brand"]:checked').each(function () {
            tempArray.push($(this).val());
        });
        if (tempArray.length !== 0) {
            seasoning += '&brand=' + tempArray.toString();
            tempArray = [];
        }

        $('input[name="category"]:checked').each(function () {
            tempArray.push($(this).val());
        });
        if (tempArray.length !== 0) {
            seasoning += '&category=' + tempArray.toString();
            tempArray = [];
        }

        $('input[name="room"]:checked').each(function () {
            tempArray.push($(this).val());
        });
        if (tempArray.length !== 0) {
            seasoning += '&room=' + tempArray.toString();
            tempArray = [];
        }

        $('input[name="price"]:checked').each(function () {
            tempArray.push($(this).val());
        });
        if (tempArray.length !== 0) {
            seasoning += '&price=' + tempArray.toString();
            tempArray = [];
        }

        $('input[name="rating"]:checked').each(function () {
            tempArray.push($(this).val());
        });
        if (tempArray.length !== 0) {
            seasoning += '&rating=' + tempArray.toString();
            tempArray = [];
        }

        //X??a parameter c??
        const url = new URL(location.href);
        const params = url.searchParams;
        params.delete('brand');
        params.delete('category');
        params.delete('room');
        params.delete('price');
        params.delete('rating');
        window.location = url.href + seasoning;
    });
});

<!--===============================================================================================-->
$(function () {
    //L??u gi?? tr??? l???c th????ng hi???u, gi?? b??n v??o sessionScope
    $('input[type="checkbox"][class="filter-input"]').each(function () {
        $(this)
            .prop('checked', sessionStorage.getItem(this.id) === 'true')
            .on('change', function () {
                sessionStorage.setItem($(this).prop('id'), this.checked)
            })
            .trigger('change');
    });

    //L??u gi?? tr??? l???c ????nh gi??
    $('input[type="radio"][class="filter-input"]')
        .each(function () {
            $(this).prop('checked', sessionStorage.getItem(this.id) === 'true').trigger('change');
        })
        .on('click', function () {
            $('input[type="radio"]').each(function () {
                sessionStorage.setItem(this.id, this.checked);
            });
        });
});

<!--===============================================================================================-->
$(function () {
    //T??m ki???m s???n ph???m
    const searchProduct = $('#search-product');
    searchProduct.on('keyup', function (e) {
        if (e.key === 'Enter' || e.keyCode === 13) {
            appendParameterSearch(searchProduct);
        }
    });

    $('#search-product-button').click(function () {
        appendParameterSearch(searchProduct);
    });

    function appendParameterSearch(searchProduct) {
        let keyword = searchProduct.val();
        keyword = keyword.trim().replace(/\s\s+/g, ' ');
        const url = new URL(location.href);
        url.searchParams.delete('search');
        url.searchParams.delete('page');
        url.searchParams.append('search', keyword);
        location.href = url.href;
    }
});

<!--===============================================================================================-->
$(function () {
    //S???p x???p s???n ph???m
    $('.filter-link').removeClass('filter-link-active');
    const pVal = (new URL(location.href)).searchParams.get('sortBy');
    const filter_link_default = $('#filter-link-default');
    const filter_link_priceAsc = $('#filter-link-priceAsc');
    const filter_link_priceDesc = $('#filter-link-priceDesc');
    const filter_link_rateAsc = $('#filter-link-rateAsc');
    const filter_link_rateDesc = $('#filter-link-rateDesc');
    const filter_link_saleAsc = $('#filter-link-saleAsc');
    const filter_link_saleDesc = $('#filter-link-saleDesc');

    if (pVal === null) {
        filter_link_default.addClass('filter-link-active');
    } else if (pVal === 'priceAsc') {
        filter_link_priceAsc.addClass('filter-link-active');
    } else if (pVal === 'priceDesc') {
        filter_link_priceDesc.addClass('filter-link-active');
    } else if (pVal === 'rateAsc') {
        filter_link_rateAsc.addClass('filter-link-active');
    } else if (pVal === 'rateDesc') {
        filter_link_rateDesc.addClass('filter-link-active');
    } else if (pVal === 'saleAsc') {
        filter_link_saleAsc.addClass('filter-link-active');
    } else if (pVal === 'saleDesc') {
        filter_link_saleDesc.addClass('filter-link-active');
    }

    filter_link_default.on('click', function () {
        appendParameterSort('default');
    });

    filter_link_priceAsc.on('click', function () {
        appendParameterSort('priceAsc');
    });

    filter_link_priceDesc.on('click', function () {
        appendParameterSort('priceDesc');
    });

    filter_link_rateAsc.on('click', function () {
        appendParameterSort('rateAsc');
    });

    filter_link_rateDesc.on('click', function () {
        appendParameterSort('rateDesc');
    });

    filter_link_saleAsc.on('click', function () {
        appendParameterSort('saleAsc');
    });

    filter_link_saleDesc.on('click', function () {
        appendParameterSort('saleDesc');
    });

    function appendParameterSort(parameter) {
        const url = new URL(location.href);
        url.searchParams.delete('sortBy');
        if (parameter !== 'default') {
            url.searchParams.append('sortBy', parameter);
        }
        location.href = url.href;
    }
});

<!--===============================================================================================-->
$(function () {
    $('.btn-add-item').on('click', function () {
        $('.input-add-item').attr('value', location.href);
    });
});

<!--===============================================================================================-->
$(function () {
    $('.js-show-modal').each(function () {
        $(this).on('click', function (e) {
            e.preventDefault();
            //T??m js-modal v?? th??m class show
            $(this).parent().parent().parent().next().addClass('show-modal');
        });
    });

    $('button[class*="js-hide-modal"]').each(function () {
        $(this).on('click', function () {
            //T??m js-modal v?? x??a class show
            $(this).parent().parent().parent().removeClass('show-modal');
        })
    });

    $('div[class*="js-hide-modal"]').each(function () {
        $(this).on('click', function () {
            //T??m js-modal v?? x??a class show
            $(this).parent().removeClass('show-modal');
        })
    });
});

<!--===============================================================================================-->
$(function () {
    $('.btn-remove-item').on('click', function () {
        $('.input-remove-item').attr('value', location.href);
    });
});
