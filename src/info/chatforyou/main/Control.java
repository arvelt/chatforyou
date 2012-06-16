package info.chatforyou.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import info.chatforyou.main.ChatServer;
import info.chatforyou.main.Message;

/**
 * Servlet implementation class Control
 */
public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;
      	
	private ChatServer chatserver = ChatServer.getInstance();;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Control() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String disp = "/chatforyou/index.jsp";
	    response.sendRedirect(disp);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader( "Cache-Control", "no-cache" ); 
		
		//トップ画面で作成押下
		if ( request.getParameter("make") != null )
		{
		    String room = request.getParameter("roomName");
		    String user = request.getParameter("userName");
		    String pass = request.getParameter("roomPass");
		    
		    HttpSession session = request.getSession(true);
		    session.setAttribute("room", room);
		    session.setAttribute("pass", pass);
		    session.setAttribute("user", user);
		    session.removeAttribute("passerr");
		    
		    String id = session.getId();
		    
		    if ( chatserver.addChatRoom(room,user,pass) ){

			    //ルーム作成時
			    chatserver.addUser(room,user,id);		    
			    
		    	ArrayList<ChatUser> users = new ArrayList<ChatUser>();
		    	users = chatserver.getUsers( room );
		    	Iterator<ChatUser> itrUserList = users.iterator();
		    	ArrayList<String> userList = new ArrayList<String>();
		    	while ( itrUserList.hasNext() ){
		    		ChatUser objuser = itrUserList.next();
		    		userList.add(objuser.getName());
		    	}

			    session.setAttribute("login", "OK");
			    
			    String disp = "/chatforyou/chatroom.jsp";
			    response.sendRedirect(disp);
			    
		    }else{
		    
		    	//パスワードチェック
		    	if ( chatserver.checkPass(room, pass) ) {
		    	
		    		//すでに存在するロームにログイン
				    chatserver.addUser(room,user , id);
				    
			    	ArrayList<ChatUser> users = new ArrayList<ChatUser>();
			    	users = chatserver.getUsers( room );
			    	Iterator<ChatUser> itrUserList = users.iterator();
			    	ArrayList<String> userList = new ArrayList<String>();
			    	while ( itrUserList.hasNext() ){
			    		ChatUser objuser = itrUserList.next();
			    		userList.add(objuser.getName());
			    	}

				    session.setAttribute("login", "OK");
				    
				    String disp = "/chatforyou/chatroom.jsp";
				    response.sendRedirect(disp);
				    	
		    	}else{
		    		
		    		//だめならログイン不可
				    session.setAttribute("passerr", "NG");
		    		String disp = "/chatforyou/index.jsp";
				    response.sendRedirect(disp);
		    	}
		    }
		}
		
		//チャットルームページ、送信ボタン
		if ( request.getParameter("send") != null )
		{
			
		    String message = request.getParameter("sendmessage");

		    HttpSession session = request.getSession(true);
		    String room = (String)session.getAttribute("room");
		    String pass = (String)session.getAttribute("pass");
		    String user = (String)session.getAttribute("user");
		    
		    Date logindate = new Date( (long)session.getCreationTime() );
		    SimpleDateFormat sdfymdhms = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		    String logintime = sdfymdhms.format(logindate);
		    
		    ChatServer chatserver = ChatServer.getInstance();
		    chatserver.sayMessage(room,user ,message);
		    
		    //ページ遷移
		    String disp = "/chatforyou/chatroom.jsp";
		    response.sendRedirect(disp);
		}
		
		//チャットルームページ、ログアウト
		if ( request.getParameter("logout") != null )
		{
		    HttpSession session = request.getSession(true);
		    String room = (String)session.getAttribute("room");
		    String pass = (String)session.getAttribute("pass");
		    String user = (String)session.getAttribute("user");
		    String id = session.getId();
		    
	    	ChatServer chatserver = ChatServer.getInstance() ;
	    	chatserver.deleteUser(room, user , id );
	    	
	    	session.invalidate();
	    	
		    String disp = "/chatforyou/index.jsp";
		    response.sendRedirect(disp);
		}
		
		//トップページ非同期通信
		if ( request.getParameter("getroomlistdata") != null ){
				
			ArrayList<ChatRoom> allRooms = new ArrayList<ChatRoom>();
	    	allRooms = chatserver.getAllChatRooms();
	    	
	    	//現在あるルームのリストを整形済みで返す

	    	JSONObject jobj = new JSONObject();
	    	if ( allRooms.size() != 0 ){
		    
    			Iterator<ChatRoom> itrRoomList = allRooms.iterator();
		    	ArrayList<String> roomList = new ArrayList<String>();
		    		
		    	String str1 = "<input type=\"text\" name=\"rooms\" size=\"30\" value=\"";
		    	String str2 = "\" class=\"roomlistfield\" readonly /><br>";
		    	String tmpstr1 ="";
		    	//こうしたい
		    	//String tmpstr3 = "[ tmpstr2 , tmpstr2 ]";	
		    	//String tmpstr3 = "[";
		    	while ( itrRoomList.hasNext() ){
		    		ChatRoom tmproom = itrRoomList.next();
		    		tmpstr1 += str1 + tmproom.getRoomName() + str2  ;
		    		
		    	}
		    	jobj.put("roomlist", tmpstr1 );
	    	} else {
		    	jobj.put("roomlist", "" );
	    	}
	    	
	    	String jsonText = JSONValue.toJSONString(jobj);
	    	PrintWriter out = response.getWriter() ;
			out.println( jsonText );
	
		}
		
		
	    //チャットルーム非同期通信
	    if ( request.getParameter("getdata") != null ){
	    	
		    HttpSession session = request.getSession(true);
		    String room = (String)session.getAttribute("room");
		    String user = (String)session.getAttribute("user");

			String str = "";
		    
		    JSONObject jobj = new JSONObject();

		    //ユーザーリストの取得
	    	ArrayList<ChatUser> users = new ArrayList<ChatUser>();
	    	users = chatserver.getUsers( room );

	    	if (users != null ){
	    		Iterator<ChatUser> itrUserList = users.iterator();
		    	while ( itrUserList.hasNext() ){
		    		ChatUser objuser = itrUserList.next();
		    		str += objuser.getName() +" ";
		    	}	
		    	jobj.put("userlist", str );
			}else{
				jobj.put("userlist", "" );
	    	}
	    	
	    	
			//メッセージの取得
		    Date logindate = new Date( (long)session.getCreationTime() );
		    SimpleDateFormat sdfymdhms = new SimpleDateFormat("yyyyMMddHHmmss");
		    String logintime = sdfymdhms.format(logindate);
		    
		    ArrayList<Message> array = new ArrayList<Message>();		    
		    array = chatserver.returnMessages(room,user);
		    
		    if (array != null){

		    	StringBuilder strb = new StringBuilder();
		    	//for ( int i = 0 ; i < array.size(); i++ ){
		    	for ( int i = array.size() - 1  ; i >= 0 ; i-- ){
				   	if ( logintime.compareTo( array.get(i).getSaidTimeYYYYMMDDHHMMSS() ) < 0 ){
		    			strb.append( array.get(i).toString() + "\n" );
			    	}
		    	}
		    	jobj.put("message", strb.toString() );
		    }
		    else
		    {
		    	jobj.put("message" , "");
		    }
		    
		    String jsonText = JSONValue.toJSONString(jobj);
		    PrintWriter out = response.getWriter() ;
			out.println( jsonText );
		    
	    }
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void doOption(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
