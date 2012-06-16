<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%  
	String room = (String)session.getAttribute("room");
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/chatroom.css">
<script type="text/javascript" src="js/createXMLobject.js"></script>
<script type="text/javascript">
<!--
function repeat(){
	getData();
	window.setInterval("getData()", 5000);
}
function getData()
{

	httpObj = createXMLHttpRequest(displayData);
	if (httpObj)
	{
		httpObj.open("POST","control",true);
		httpObj.setRequestHeader("Content-Type" ,"application/x-www-form-urlencoded; charset=UTF-8");
		httpObj.send("getdata=pressed");
	}
}

function displayData()
{
	if ((httpObj.readyState == 4) && (httpObj.status == 200))
	{
		//alert(httpObj.responseText);
		
		var data = eval("("+httpObj.responseText+")");
		
		var div = document.getElementById("divlist");
		div.innerHTML = data.userlist;
		
		var msg = document.getElementById("messages");
		msg.value = data.message;
		
		var btn = document.getElementById("sendmessage");
		btn.focus();
	}
}
// -->
</script>
<title>ChatRoom</title>
</head>
<body onload='repeat()'>

<form action="/chatforyou/control" method="post" name="userlist" >
<table id="table">
<tr><td>RoomName:</td><td><%=room %><%= "　　" %><input type="submit" value="logout" name="logout" class="button" ></td></tr>
<tr><td>UserList:</td><td id="divlist"></td></tr>
</table>
<input type="submit" value="userlist" name="getuserlist" style="display:none" >
</form>
<textarea name="messages" id="messages" readonly >
</textarea><br>
<form action="/chatforyou/control" method="post" name="chatroom" ><input type="text" name"dummytext" style="display:none">
<table>
<tr>
<td><input type="text" name="sendmessage" id="sendmessage" maxlength="200" onKeyPress="entersend();return false";></td><td><input type="submit" id="send" name="send" class="button" value="send"></tr>
</tr>
</table>
</form> 
</body>
</html>
