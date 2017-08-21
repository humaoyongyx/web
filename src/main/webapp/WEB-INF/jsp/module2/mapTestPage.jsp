<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../header.jsp"%>

<title>地图测试</title>

 <style>
        * {
            margin: 0;
            padding: 0;
            border: 0;
        }
        body {
            position: absolute;
            width: 100%;
            height: 100%;
            text-align: center;
        }
        #pos-area {
            background-color: #009DDC;
            margin-bottom: 10px;
            width: 100%;
            overflow: scroll;
            text-align: left;
            color: white;
        }
        #demo {
            padding: 8px;
            font-size: small;
        }
        #btn-area {
            height: 100px;
        }
        button {
            margin-bottom: 10px;
            padding: 12px 8px;
            width: 42%;
            border-radius: 8px;
            background-color: #009DDC;
            color: white;
        }
    </style>
   <script type="text/javascript" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js"></script> 
 <!--     <script type="text/javascript" src="//apis.map.qq.com/tools/geolocation/min?key=6TGBZ-TPYKI-A63GM-5PMVP-35LIJ-Z2B43&referer=关爱通滴滴应用"></script> -->
    
</head>
<body>
<script type="text/javascript">

</script>



	<div class="container-fluid">
		<center>
			<h1>地图测试</h1>
		</center>

<p id="demo">点击按钮获取您当前坐标（可能需要比较长的时间获取）：</p>
<button onclick="getLocation()">点我</button>
<script>
var geolocation = new qq.maps.Geolocation();
var x=document.getElementById("demo");
function getLocation()
{
	if (navigator.geolocation)
	{
		geolocation.getLocation(showPosition,showError);
	}
	else
	{
		x.innerHTML="该浏览器不支持定位。";
	}
}
function showPosition(position)
{
	x.innerHTML="纬度: " + position.coords.latitude + 
	"<br>经度: " + position.coords.longitude;	
}
function showError(error)
{
	switch(error.code) 
	{
		case error.PERMISSION_DENIED:
			x.innerHTML="用户拒绝对获取地理位置的请求。"
			break;
		case error.POSITION_UNAVAILABLE:
			x.innerHTML="位置信息是不可用的。"
			break;
		case error.TIMEOUT:
			x.innerHTML="请求用户地理位置超时。"
			break;
		case error.UNKNOWN_ERROR:
			x.innerHTML="未知错误。"
			break;
	}
}
        
        
        
</script>

<div id="pos-area">
        <p id="demo">点击下面的按钮，获得对应信息：<br /></p>
    </div>
 
    <div id="btn-area">
        <button onClick="geolocation.getLocation(showPosition, showErr, options)">获取精确定位信息</button>
        <button onClick="geolocation.getIpLocation(showPosition, showErr)">获取粗糙定位信息</button>
        <button onClick="showWatchPosition()">开始监听位置</button>
        <button onClick="showClearWatch()">停止监听位置</button>
    </div>
    <script type="text/JavaScript">
        var geolocation = new qq.maps.Geolocation("OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77", "myapp");
 
        document.getElementById("pos-area").style.height = (document.body.clientHeight - 110) + 'px';
 
        var positionNum = 0;
        var options = {timeout: 8000};
        function showPosition(position) {
            positionNum ++;
            document.getElementById("demo").innerHTML += "序号：" + positionNum;
            document.getElementById("demo").appendChild(document.createElement('pre')).innerHTML = JSON.stringify(position, null, 4);
            document.getElementById("pos-area").scrollTop = document.getElementById("pos-area").scrollHeight;
        };
 
        function showErr() {
            positionNum ++;
            document.getElementById("demo").innerHTML += "序号：" + positionNum;
            document.getElementById("demo").appendChild(document.createElement('p')).innerHTML = "定位失败！";
            document.getElementById("pos-area").scrollTop = document.getElementById("pos-area").scrollHeight;
        };
 
        function showWatchPosition() {
            document.getElementById("demo").innerHTML += "开始监听位置！<br /><br />";
            geolocation.watchPosition(showPosition);
            document.getElementById("pos-area").scrollTop = document.getElementById("pos-area").scrollHeight;
        };
 
        function showClearWatch() {
            geolocation.clearWatch();
            document.getElementById("demo").innerHTML += "停止监听位置！<br /><br />";
            document.getElementById("pos-area").scrollTop = document.getElementById("pos-area").scrollHeight;
        };
    </script>
		
	</div>	
		
</body>
</html>