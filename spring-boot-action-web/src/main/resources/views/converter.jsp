<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>HttpMessageConvert Demo</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
</head>
<body>
<script type="application/javascript" src="scripts/jquery-1.8.0.min.js"></script>
<div id="resp"></div>
<input type="button" onclick="req();" value="请求">
<script>
    function req() {
        $.ajax({
            url:'convert',
            data:'1-fangguangming',
            type:'POST',
            contentType:'application/x-wisely',
            success:function(data){
                $("#resp").html(data);
            }
        });
    }
</script>
</body>
</html>