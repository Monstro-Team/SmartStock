$(function(){
    if("${error}".length == 0)
        $("#includedContent").style.visibility = "hidden";
});

$(function(){
    $("#includedContent").load("http://localhost:8080/SmartStock/menu.html");
});
