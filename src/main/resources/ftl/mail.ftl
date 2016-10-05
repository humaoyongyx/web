<Html>
    <head>
        <title>Web Email</title>
    </head>
    <body  background="cid:image">
			         <!-- 根节点 -->
			<div style="width:600px;text-align:left;color:#000;font:normal 12px/15px arial,simsun;">
			    This is a freemarker  email template 
			</div>
			<!-- 根节点-邮件内容居中 -->
			<div style="text-align:center;height:300px;">
			    <div style="width:600px;margin:0 auto;text-align:left;color:#000;font:normal 12px/15px arial,simsun;">
			                              <div><h1>${content}</h1></div>
			    </div>
			</div>
			<!-- 如果使用语义化标签，那么需要多写一些style，以避免被环境中的css覆盖 -->
			<h2 style="width:100px;height:100px;margin:0;padding:0;fong-weight:normal;font-size:12px;"></h2>
			<!-- 而使用无语义标签，就可以省下很多style -->
			<div style="width:100px;height:100px;"></div>
         
    <body>
</html>