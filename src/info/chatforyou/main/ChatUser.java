package info.chatforyou.main;

public class ChatUser {
	private String id;
    private String name;
    
    ChatUser(){
    	name = "";
    }
    
    public String getName(){
    	return name ;
    }
    
    public void setName( String userName ){
    	name = userName ;
    }
    
    public String getId(){
    	return id ;
    }

    public void setId( String reciveid){
    	id = reciveid ;
    }
    
}
