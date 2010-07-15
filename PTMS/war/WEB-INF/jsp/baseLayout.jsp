<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css"> @import "<%=request.getContextPath() %>/style/basestyle.css";</style>

<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body id="layout">
<table id="basetable">

	<tr id="headerrow">
		<td id="headercol"><tiles:insertAttribute name="header" /></td>
	</tr>
	<tr id="bodyrow">
		<td id="bodycol">
		<table id="bodytable">
			<tr id="bodytablerow">
				<td id="menutablecol"><tiles:insertAttribute name="menu" /></td>
				<td id="bodytablecol"><tiles:insertAttribute name="body" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr id="footerrow">
		<td id="footercol"><tiles:insertAttribute name="footer" /></td>
	</tr>
</table>






</body>
</html>
