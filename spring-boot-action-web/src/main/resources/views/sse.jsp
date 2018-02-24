<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>SSE Demo</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
</head>
<body>
<div id="msgFromPush"></div>
<script type="application/javascript" src="/scripts/jquery-1.8.0.min.js"></script>
<script type="application/javascript">
    if(!!window.EventSource){
        var source=new EventSource('push');
        var s='';
        source.addEventListener('message',function(e){
            s+=e.data+"<br/>";
            $("#msgFromPush").html(s);
        });
        source.addEventListener("open",function(e){
            console.log('连接打开');
        },false);
        source.addEventListener("error",function(e){
            if(e.readyState==EventSource.CLOSED){
                console.log('连接关闭');
            }else{
                console.log('==>'+e.readyState);
                console.log('==>{}',e);
            }

        },false)


    }else{
        console.log("您的浏览器不支持SSE");

    }


</script>

</body>
</html>