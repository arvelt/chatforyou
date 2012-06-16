package info.chatforyou.main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

	private String user ;
	private Date date ;
	private String message ;
	
	Message(){		
		user = "";
		date = null;
		message = "";
	}
	
    public String getSaidUser(){
    	return user ;
    }
    
    public void setSaidUser( String saidUser ){
    	user = saidUser;
    }
    
    public Date getSaidTime(){
    	return date ;
    }

    public String getSaidTimeYYYYMMDDHHMMSS(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	return sdf.format(date) ;
    }
    
    public String getSaidTimeHHMMSS(){
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    	return sdf.format(date) ;
    }
    
    public void setSaidTime(){
    	
    	date = new Date() ;
    }
	
    public String getSaidMessage(){
    	return message ;
    }
    
    public void setSaidMessage( String saidMessage){
    	
    	message = saidMessage;
    }
    
    public String toString(){
    	String str = new String("");
    	str = this.getSaidTimeHHMMSS();
    	str += "(";
    	str += this.getSaidUser();
    	str += ")";
    	str += this.getSaidMessage();
    	
    	return str ;
    }
}
