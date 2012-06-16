<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ page import="java.util.ArrayList" %>
<%  
	String passerr = (String)session.getAttribute("passerr");
	String err = ""; 
	if ( passerr != null ){
		err = "Password is diffrent";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-16856970-2']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="js/createXMLobject.js"></script>
<script type="text/javascript" src="js/checkinput.js"></script>
<script type="text/javascript">
function inputcheck()
{
	var room = document.getElementById("roomName");
	var errroom = document.getElementById("roommsg");
	var user = document.getElementById("userName");	
	var erruser = document.getElementById("usermsg");
	var pass = document.getElementById("roomPass");	
	var errpass = document.getElementById("passmsg");
	
	var bool = true ;
	errroom.innerHTML ="";
	erruser.innerHTML ="";
	errpass.innerHTML ="";
	
	if ( room.value == "" ) {
		errroom.innerHTML = "No entry";
		bool = false ;
	} else {
		//alert(room.value);
		
		if ( checkinput(room.value) == false ){
			errroom.innerHTML = "The roomname is unavailable";
			bool = false ;
		}	
	}

	if ( user.value == "" ) {
		erruser.innerHTML = "No entry";
		bool = false ;
	} else {
		if ( checkinput( user.value ) == false ){
			erruser.innerHTML = "The username is unavailable";
			bool = false ;
		}
	}
	
	if ( user.value == "" ) {
	} else {
		if ( checkinput( pass.value ) == false ){
			errpass.innerHTML = "The password is unavailable";
			bool = false ;	
		}
	}
	
	return bool ;	
}
function repeat(){
	getRoomList();
	window.setInterval("getRoomList()", 10000);
}
function getRoomList()
{
	httpObj = createXMLHttpRequest(displayData);
	if (httpObj)
	{
		httpObj.open("POST","control",true);
		httpObj.setRequestHeader("Content-Type" ,"application/x-www-form-urlencoded; charset=UTF-8");
		httpObj.send("getroomlistdata=pressed");
	}
}

function displayData()
{
	if ((httpObj.readyState == 4) && (httpObj.status == 200))
	{
		//alert(httpObj.responseText);
		var data = eval("("+httpObj.responseText+")");	
		var div = document.getElementById("roomlist");
		div.innerHTML = data.roomlist;
	}
}
</script>
<title>Chat For YOU !</title>
</head>
<body onLoad="repeat()">
<div align="center">
<h1>Chat For YOU.</h1>
<p class="headexplain">Welcome to Chat For you. Make room and enjoy communication. Beta testing now.</p>
<div>
<form action="/chatforyou/control" method="post" name="index" >
<table id="table" >
<tr><td>RoomName  ：</td><td><input class="field" type="text" name="roomName" id="roomName" size="20" ></td><td><span id="roommsg" style="color:red"></span></td></tr>
<tr><td>UserName：</td><td><input class="field" type="text" name="userName" id="userName" size="20" ></td><td><span id="usermsg" style="color:red"></span></td></tr>
<tr><td>RoomPassword：</td><td><input class="field" type="password" name="roomPass" id="roomPass" size="10" ></td><td><span id="passmsg" style="color:red"><%= err %></span></td></tr>
<tr><td colspan="2" id="tablebutton"><input type="submit" value="into the room" name="make" onClick="return inputcheck()" class="rounded"></td><td></td></tr>
</table>
</form>  
</div>
<br>
<div>
<FORM name="rooms" METHOD="POST" ACTION="/chatforyou/control">
<table class="roomlist">
<tr><td>Room Maps maiking now.</td><td><input type="button" value="update room maps" name="listupdate" onClick="getRoomList()" class="rounded"></td></tr>
</table>
<div id="roomlist" class="roomlistfield">
</div>
</FORM>
</div>
</div>
</body>
</html>
