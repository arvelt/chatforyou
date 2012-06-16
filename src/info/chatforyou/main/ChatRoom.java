package info.chatforyou.main;

import java.util.ArrayList;
import java.util.Iterator;
import info.chatforyou.main.ChatUser;

public class ChatRoom {
    private String roomName;
    private String roomPass;
    private ArrayList<ChatUser> chatusers;
    private ArrayList<Message> message ;
    
    ChatRoom(){
    	roomName = "";
    	roomPass= "";
    	message = new ArrayList<Message>();
    	chatusers = new ArrayList<ChatUser>();
    }

    public String getRoomName(){
    	return roomName ;
    }
    
    public void setRoomName( String name ){
    	roomName = name;
    }
    
    public String getRoomPass(){
    	return roomPass ;
    }
    
    public void setRoomPass( String pass ){
    	roomPass = pass ;
    }
    
    public ChatUser getChatUser( String name ){
    	
    	Iterator<ChatUser> itrUserList = chatusers.iterator();
    	while ( itrUserList.hasNext() ){
    		ChatUser user = itrUserList.next();
    		if ( user.getName().equals(name)){
    			return user ;
    		}
    	}
    	
    	return null ;
    }
    
    public ArrayList<ChatUser> getChatUsers(){
    	return chatusers ;
    }

    public int getCountChatUsers(){
    	return chatusers.size();
    }
    
//    public void setChatUsesr( String name ){
    public void setChatUsesr( ChatUser user ){
    	//ChatUser user = new ChatUser();
    	//user.setName(name);
    	chatusers.add( user);
    }
    
    public ArrayList<Message> getMessage(){    	
    	return message ;
    }
    
    //”­Œ¾‚ğ•”‰®‚É“o˜^
    public void setMessage( String user , String saidMessage ){
    	Message msg = new Message();
    	msg.setSaidUser(user);
    	msg.setSaidMessage(saidMessage);
    	msg.setSaidTime();
    	message.add( msg )  ;
    }
    
    //usaríœAƒZƒbƒVƒ‡ƒ“‚h‚c‚Å”»•Ê
    public void removeChatUser( String user , String id ) {
    	
    	for ( int i = 0 ; i < chatusers.size() ; i++ ){
    		if ( chatusers.get(i).getId().equals( id ) ){
    			chatusers.remove(i);
    			break ;
    		}
    	}
    }
    
}
