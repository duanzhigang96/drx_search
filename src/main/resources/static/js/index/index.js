
window.onload = function () {
}

window.addEventListener('beforeunload', e => {
    console.log('窗口即将关闭！');
})

document.onkeydown = function (e) {//键盘按键控制
    e = e || window.event;
    if ((e.ctrlKey && e.keyCode == 82) || //ctrl+R
        e.keyCode == 116) {//F5刷新，禁止
        $("#result_body").empty();
        setTimeout(function () {
            // alert('按下F5或者CTRL+R');
            query();
        }, 100);//延时提醒，要不alert会导致return false被alert挂起从而浏览器执行了刷新
        return false
    }
}
$(document).ready(function(){
    //回车事件绑定
    $('#search_input').bind('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
            query();
        }
    });

});

function query() {
    var query = $("#search_input").val();
    if (query != "") {
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/findWithAll/" + query,
            data: {
                item: query
            },
            success: function (data) {
                $("#result_body").empty();
                $("#result_body").append("<span class=\"result_num\">为您找到相关结果约" + data.length + "个</span>");
                if (data.length > 0) {
                    for (var i = 0; i<data.length; i++) {
                        $("#result_body").append("<div id=\"result_item\" class=\"result_item\">\n" +
                            "                <a class=\"item_title\" id='" + data[i].id + "' onclick='searchDetail(this)'>这是<span style=\"color: red\">查询</span>内容title</a>\n" +
                            "                <div class=\"item_describe\">\n" +
                            "                <span>" + data[i].version + "&nbsp-&nbsp</span>" + data[i].fun_describe +
                            "                </div>\n" +
                            "                <div class=\"item_author\">" + data[i].create_user + "</div>\n" +
                            "            </div>");
                    }
                }
            }
        });
    } else {
        window.location.href = "../index";
    }
}

function searchReady() {
    if ($('#search_input').val() != "") {
        $("#logo-box").removeClass("logo-box");
        $("#logo-box").addClass("logo-box-search");
        $("#logo").removeClass("show-logo");
        $("#logo").addClass("logo");
        $("#search_container").removeClass("search_container");
        $("#search_container").addClass("search_container_top");
        $("#search_title").addClass("search_title");
        $("#search_result").css("display", "block");
    }
}

function searchFocus() {
    $("#search_container").css("box-shadow", "0px 0px 1px 0px #fff,0px 0px 5px 0px rgb(100, 100, 100)");
}

function searchBlur() {
    $("#search_container").css("box-shadow", "0px 0px 1px 0px #fff,0px 0px 1px 1px rgb(188, 188, 188)");
}

function scrollSearchContent() {
    var scrollTop = document.documentElement.scrollTop;
    if (scrollTop != 0) {
        $("#search_title").addClass("box-shadow-title");
    } else {
        $("#search_title").removeClass("box-shadow-title");
    }
}

function searchDetail(object) {
    alert(object.id);
    window.location.href = "../getByIdFromSolr/" + object.id;
}
