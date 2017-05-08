<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../header.jsp"%>


<title>根据关键字本地搜索</title>

<style type="text/css">
body, html, #allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=sCZGeyQuO8TBPFXlIRNCdQsF3mz8aPdo"></script>
</head>
<body>
	<script>
		$(document).ready(function() {

		});
	</script>


	<div class="container-fluid">
	   <form class="form-inline" role="form">
			  <div class="form-group">
			    <label for="map_address" class="control-label"> 地址：</label>
			    <input type="text" class="form-control" id="map_address" placeholder="地址">
			  </div>
			  &nbsp;  &nbsp;
			  <div class="form-group">
			    <label for="map_degree"  class="control-label">经纬度：</label>
			    <input type="text" class="form-control" id="map_degree" placeholder="经纬度">
			  </div>
			    &nbsp;  &nbsp;
			<!--   <button type="button" class="btn btn-primary" onclick="locateAxis()">查询</button> -->
	</form>
		
		<div id="allmap"></div>

	</div>



	<script type="text/javascript">
		
		// 百度地图API功能
		var map = new BMap.Map("allmap");
		var point = new BMap.Point(116.331398,39.897445);
		map.centerAndZoom(point,12);
		map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
		map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		
		map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
		map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
		map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开
		
		var myCity = new BMap.LocalCity();
		myCity.get(getCity);
		
		
		  var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
				{"input" : "map_address"
				,"location" : map
			});


			var myValue;
			ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
			var _value = e.item.value;
				myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				locateAxis(myValue);
				setPlace();
			});

			function setPlace(){
				map.clearOverlays();    //清除地图上所有覆盖物
				function myFun(){
					var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
					map.centerAndZoom(pp, 18);
					map.addOverlay(new BMap.Marker(pp));    //添加标注
				}
				var local = new BMap.LocalSearch(map, { //智能搜索
				  onSearchComplete: myFun
				});
				local.search(myValue);
			}
		
		
		
		function getCity(result){
			var cityName = result.name;
			map.setCenter(cityName);
		}
		
	    var localSearch = new BMap.LocalSearch(map);
	    
		function locateAxis(keyword) {
		    localSearch.enableAutoViewport(); //允许自动调节窗体大小
		    localSearch.setSearchCompleteCallback(function (searchResult) {
		        var poi = searchResult.getPoi(0);
		        var value= poi.point.lng + "," + poi.point.lat;
		        $("#map_degree").val(value);
		    });
		    localSearch.search(keyword);
		} 
		
		function locateAxis() {
			 map.clearOverlays();//清空原来的标注
		    localSearch.enableAutoViewport(); //允许自动调节窗体大小
		    localSearch.setSearchCompleteCallback(function (searchResult) {
		        var poi = searchResult.getPoi(0);
		        map.centerAndZoom(poi.point, 18);
		        var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
		        map.addOverlay(marker);
		        var value= poi.point.lng + "," + poi.point.lat;
		        $("#map_degree").val(value);
		    });
		    localSearch.search($("#map_address").val());
		} 
		
	</script>



</body>
</html>

