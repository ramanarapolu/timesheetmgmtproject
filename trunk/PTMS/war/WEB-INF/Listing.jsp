<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
<head>
<sx:head debug="false" cache="false" compressed="true"/>
<title>Listing</title>
</head>
<script>
     function show_details() {
         alert("Hello");
     dojo.event.topic.publish("show_detail");
     }
    </script>
<body>
<s:form id="frm_demo" name="frm_demo" theme="simple">
	<table border="0">
		<tr>
			<td><s:select list="lstList1" name="lst"
				onchange="javascript:show_details();return false;"></s:select></td>
			<td><s:url id="d_url" action="DetailAction" />
			 <sx:div  showLoadingText="false"
    id="details" href="%{d_url}"  theme="ajax"
    listenTopics="show_detail"  formId="frm_demo">
     </sx:div></td>
		</tr>
	</table>
</s:form>
</body>
</html>