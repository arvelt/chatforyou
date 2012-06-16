package info.chatforyou.main;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import info.chatforyou.main.ChatServer;;

/**
 * Application Lifecycle Listener implementation class CheckSession
 *
 */
public class CheckSession implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public CheckSession() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
    	HttpSession ses = arg0.getSession();
    	String id = ses.getId();
    	String room = (String)ses.getAttribute("room");
    	//String pass = (String)ses.getAttribute("pass");
    	String user = (String)ses.getAttribute("user");
    	//String login = (String)ses.getAttribute("login");
    	
    	ChatServer chatserver = ChatServer.getInstance() ;
    	chatserver.deleteUser(room, user , id );
    }
}
