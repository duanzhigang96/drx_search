
window.onload = function () {
}

$(document).ready(function(){
    //回车事件绑定
    $('#search_input').bind('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
            var query = $("#search_input").val();
            if (query != "") {
                // $.ajax({
                //     type: "post",
                //     dataType: "json",
                //     url: "/login",
                //     data: {
                //         query: query
                //     },
                //     success: function (data) {
                //     }
                // });
                var data = "";
            }
        }
    });
});

function searchReady() {
    // alert("111");
    $("#logo-box").removeClass("logo-box");
    $("#logo-box").addClass("logo-box-search");
    $("#logo").removeClass("show-logo");
    $("#logo").addClass("logo");
    $("#search_container").removeClass("search_container");
    $("#search_container").addClass("search_container_top");
    // $("#search_title").addClass("box-shadow-title");
    $("#search_title").addClass("search_title");
    $("#search_content").css("display", "block");
}

function scrollSearchContent() {
    var scrollTop = document.documentElement.scrollTop;
    if (scrollTop != 0) {
        $("#search_title").addClass("box-shadow-title");
    } else {
        $("#search_title").removeClass("box-shadow-title");
    }
}
