
var code = "";
$(document).ready(function(){
    $("title").html($("#article_title").text());
    // CodeMirror.commands.save = function() {
    //     var elt = editor.getWrapperElement();
    //     elt.style.background = "#def";
    //     setTimeout(function() { elt.style.background = ""; }, 300);
    // };
    var editor = CodeMirror.fromTextArea(document.getElementById("code"), {
        lineNumbers: true,
        // mode: "text/x-csrc",
        mode: "text/x-java",
        matchBrackets: true,
        readOnly:true,
        lineWiseCopyCut: true,
        keyMap: "emacs"
    });
    editor.setSize('auto','auto');

    code = editor.getValue();

    // $(".CodeMirror").append("<div class=\"hljs-button {2}\" onclick=\"copyCode()\">复制</div>");

    var btn = document.getElementById('copy_code');
    $("#copy_code").attr("data-clipboard-text",document.getElementById('code').innerText);

    var clipboard = new ClipboardJS(btn);
    clipboard.on('success', function(e) {
        console.log(e);
    });
    clipboard.on('error', function(e) {
        console.log(e);
    });
});
