(function ($) {
    "use strict";
    var mainApp = {

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

    }
    // Initializing ///

    $(document).ready(function () {
        mainApp.main_fun();
    });

}(jQuery));

/*
    my-account.jsp
*/
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

/*
    product-list-by-category.jsp
*/
$(function () {
    //Nếu URL không chứa parameter thì xóa hết parameter trong sessionScope
    const url = new URL(location.href);
    const params = url.searchParams;

    if (!params.has('brand')) {
        $('input[name="brand"]').prop('checked', false).each(function () {
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

    //Đặt lại bộ lọc tìm kiếm
    $('#filter-reset').on('click', function () {
        $('.filter-input').prop('checked', false);
        sessionStorage.clear();
        params.delete('brand');
        params.delete('price');
        params.delete('rating');
        window.location = url.href;
    });
});

<!--===============================================================================================-->
$(function () {
    //append parameter vào url
    $('input[type="checkbox"], input[type="radio"]').click(function () {
        let seasoning = '', tempArray = [];
        $('input[name="brand"]:checked').each(function () {
            tempArray.push($(this).val());
        });
        if (tempArray.length !== 0) {
            seasoning += '&brand=' + tempArray.toString();
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

        //Xóa parameter cũ
        const url = new URL(location.href);
        const params = url.searchParams;
        params.delete('brand');
        params.delete('price');
        params.delete('rating');
        window.location = url.href + seasoning;
    });
});

<!--===============================================================================================-->
$(function () {
    //Lưu giá trị lọc thương hiệu, giá bán vào sessionScope
    $('input[type="checkbox"]').each(function () {
        $(this)
            .prop('checked', sessionStorage.getItem(this.id) === 'true')
            .on('change', function () {
                sessionStorage.setItem($(this).prop('id'), this.checked)
            })
            .trigger('change');
    });

    //Lưu giá trị lọc đánh giá
    $('input[type="radio"]')
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
    //Tìm kiếm sản phẩm
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
        url.searchParams.append('search', keyword);
        location.href = url.href;
    }
});

<!--===============================================================================================-->
$(function () {
    //Sắp xếp sản phẩm
    $('.filter-link').removeClass('filter-link-active');
    const pVal = (new URL(location.href)).searchParams.get('sortBy');
    const filter_link_default = $('#filter-link-default');
    const filter_link_priceAsc = $('#filter-link-priceAsc');
    const filter_link_priceDesc = $('#filter-link-priceDesc');

    if (pVal === null) {
        filter_link_default.addClass('filter-link-active');
    } else if (pVal === 'priceAsc') {
        filter_link_priceAsc.addClass('filter-link-active');
    } else if (pVal === 'priceDesc') {
        filter_link_priceDesc.addClass('filter-link-active');
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
            //Tìm js-modal và thêm class show
            $(this).parent().parent().parent().next().addClass('show-modal');
        });
    });

    $('button[class*="js-hide-modal"]').each(function () {
        $(this).on('click', function () {
            //Tìm js-modal và xóa class show
            $(this).parent().parent().parent().removeClass('show-modal');
        })
    });

    $('div[class*="js-hide-modal"]').each(function () {
        $(this).on('click', function () {
            //Tìm js-modal và xóa class show
            $(this).parent().removeClass('show-modal');
        })
    });
});

/*
    wishList.jsp
*/
$(function () {
   $('.btn-remove-item').on('click', function () {
      $('.input-remove-item').attr('value', location.href);
   });
});
