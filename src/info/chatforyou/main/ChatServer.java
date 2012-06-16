package info.chatforyou.main;
import java.security.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
//import org.apache.commons.lang.RandomStringUtils;
import java.util.UUID;

//import info.chatforyou.main.DatabaseAccess;
import info.chatforyou.main.ChatRoom;
import info.chatforyou.main.ChatUser;

public class ChatServer {
    private ArrayList<ChatRoom> chatRoomList;
    private ArrayList<ChatUser> chatUserAllList;
    private static ChatServer instance = new ChatServer();
    
    private ChatServer(){
    	chatRoomList = new ArrayList<ChatRoom>();
    	chatUserAllList = new ArrayList<ChatUser>();
    }

    public static ChatServer getInstance(){
        return instance;
    }

    //チャットルームを追加
    public boolean addChatRoom(String roomName, String user ,String roomPass){
  	
    	if( getChatRoom(roomName ) == null ){  		
    		insertChatRoom(roomName,roomPass);
    		return true ;
    	}else{
        	return false ;
    	}
    }

    //実際にチャットルームリストに挿入
    public boolean insertChatRoom(String roomName , String roomPass){
    	
    	ChatRoom chatroom = new ChatRoom();
    	chatroom.setRoomName(roomName);
    	chatroom.setRoomPass(roomPass);
    	//chatroom.setMessage(null, null);
    	chatRoomList.add(chatroom);
    	return true ;
    }

    //チャットルームのインスタンスを返す
    public ChatRoom getChatRoom( String roomName ){
    	
    	if ( chatRoomList == null ){
    		return null ;
    		
    	}
    	Iterator<ChatRoom> itrRoomList = chatRoomList.iterator();
    	
    	while ( itrRoomList.hasNext() ){
    		ChatRoom chatroom = itrRoomList.next();
    		if ( chatroom.getRoomName().equals(roomName)){
    			return chatroom ;
    		}
    	}
        return null;
    }
    
    //全てのチャットルームを返す
    public ArrayList<ChatRoom> getAllChatRooms(){	
    	return chatRoomList;
    }
    
    //発言を登録する
    public boolean sayMessage( String roomName ,String user ,String saidMessage ) {	
    	if( getChatRoom(roomName ) == null ){
    		return false ;
    	}else{
    		getChatRoom(roomName).setMessage(user, saidMessage);
        	return true ;
    	}
	}

    //全ての発言を返す
    public ArrayList<Message> returnMessages( String roomName ,String user ) {
    	if( getChatRoom(roomName ) == null ){
    		return null ;
    	}else{
    		if (getChatRoom(roomName).getMessage().size() == 0){
    			return null ;
    		}else{
    			return getChatRoom(roomName).getMessage();   			
    		}
    	}
	}
    
    //ユーザーを追加する
    public boolean addUser(String roomName , String chatUser , String id){
    	if( getChatRoom(roomName ) == null ){
    		return false ;
    	}else{
//    		getChatRoom(roomName).setChatUsesr(chatUser ,id);
    		ChatUser tmpuser = new ChatUser();
    		tmpuser.setId(id);
    		tmpuser.setName(chatUser);
    		getChatRoom(roomName).setChatUsesr( tmpuser );
    	}
    		return true ;
    }
    
    //ユーザーを削除する    
    public boolean deleteUser(String roomName , String chatUser , String id ){	
    	if( getChatRoom(roomName ) == null ){
    		return false ;    		
    	}else{
    		getChatRoom(roomName).removeChatUser( chatUser , id );
    		
    		//だれもいなければ部屋を削除する
    		if ( getChatRoom(roomName).getChatUsers().size() < 1 ){
    	    	//int deleteindex = chatRoomList.indexOf( roomName ) ;
    	    	
    	    	for ( int i = 0 ; i < chatRoomList.size() ; i++ ) {
    	    		if ( chatRoomList.get(i).getRoomName().equals(roomName)){
    	    			chatRoomList.remove(i);
    	    			break ;
    	    		}
    	    	}
    	    	return true ;
    		}
    		return true ;
    	}
    }

    //チャットルームのユーザーを返す
    public ArrayList<ChatUser> getUsers(String roomName ){    	
    	if( getChatRoom(roomName ) == null ){
    		return null ;
    	}else{
    		return getChatRoom(roomName).getChatUsers();
    	}
    }
   
    //全てのユーザーを返す
    public ArrayList<ChatUser> getAllChatUsers(){
    	return chatUserAllList ;
    }
    
    //ルームのパスワードと比較
    public boolean checkPass( String room , String pass ){
    	
    	if( getChatRoom( room ) == null ){
    		return false ;
    	} else {
    		if ( getChatRoom( room ).getRoomPass().equals(pass) ){
    			return true ;
    		}else{
    			return false ;
    		}
    	}
    }
    
    //private String toHexString(byte[] arr) {
    //    StringBuffer buff = new StringBuffer(arr.length * 2);
    //    for (int i = 0; i < arr.length; i++) {
    //        String b = Integer.toHexString(arr[i] & 0xff);
    //        if (b.length() == 1) {
    //            buff.append("0");
    //        }
    //        buff.append(b);
    //    }
    //    return buff.toString();
    //}

}
