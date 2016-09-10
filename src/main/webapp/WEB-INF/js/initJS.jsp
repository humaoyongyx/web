<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	//init plugins defaults
	$.extend($.fn.datepicker.defaults, {
		language : 'zh-CN'
	});
	$.extend($.fn.DataTable.defaults, {
		language : {
			"sProcessing" : "处理中...",
			"sLengthMenu" : "显示 _MENU_ 项结果",
			"sZeroRecords" : "没有匹配结果",
			"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
			"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
			"sInfoPostFix" : "",
			"sSearch" : "搜索:",
			"sUrl" : "",
			"sEmptyTable" : "表中数据为空",
			"sLoadingRecords" : "载入中...",
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上页",
				"sNext" : "下页",
				"sLast" : "末页"
			},
			"oAria" : {
				"sSortAscending" : ": 以升序排列此列",
				"sSortDescending" : ": 以降序排列此列"
			}
		}
	});

	//extend plugins
	$.fn.DataTableServer = function(config) {
		var defautConfig = {
			"dom" : "tip",
			"processing" : true,
			"serverSide" : true,
			"lengthChange" : false,//是否允许用户自定义显示数量
			"pageLength" : 10
		//"searching": false,//本地搜索
		//  "ordering": false, //排序功能
		// "Info": true,//页脚信息
		// "bPaginate": true, //翻页功能
		//  "bFilter": false, //列筛序功能 
		};
		if (config) {
			$.extend(true, defautConfig, config);
		}
		return $(this).DataTable(defautConfig);
	}
</script>