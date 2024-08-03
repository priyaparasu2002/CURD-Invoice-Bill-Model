package systech.pp.ulib;

import java.sql.Connection;
import java.sql.DriverManager;

public class UDatabase 
{
	private String driver;
	private String protocol;
	private String serverIP;
	private int serverPort;
	private String name;
	private String user;
	private String password;
	
	private String cnStr; //Connection String
	
	/**
	 * 
	 * @param value:=jdbc:myql
	 */
	public void setProtocol(String value) {
		this.protocol=value;
	}
	/**
	 *@param value:=com.mysql.jdbc.Driver 
	 * 
	 */
	public void setDriver(String value) {
		this.driver=value;
	}
	/**
	 * @param value:=127.0.0.1 OR localhost
	 * 
	 */
	public void setServerIP(String value) {
		this.serverIP=value;
	}
	/**
	 * @param value:=3306
	 */
	public void setServerPort(int value) {
		this.serverPort=value;
	}
	/**
	 * @param value:=cmrdb or sprdb or cmr2sprdb
	 * 
	 */
	public void setName(String value) {
		this.name=value;
	}
	/**
	 * @param value:=root
	 * 
	 */
	public void setUser(String value) {
		this.user=value;
	}
	/**
	 * @param value:=null
	 */
	public void setPassword(String value) {
		this.password=value;
	}
	
	private void getConnectionString() {
		driver=driver==null?"com.mysql.jdbc.Driver":driver;
		protocol=protocol==null?"jdbc:mysql":protocol;
		serverIP=serverIP==null?"127.0.0.1":serverIP;
		serverPort=serverPort<=0?3306:serverPort;
		user=user==null?"root":user;
		password=password==null?"":password;
		
		/**
		 * 
		 * localhost or 127.0.0.1
		 * 
		 * dbName="cmrdb" or "sprdb" or "cmr2sprdb"
		 * jdbc:mysql://127.0.0.1:3306/cmr2sprdb
		 * 
		 */
	}
	public Connection getConnection() {
		try {
			getConnectionString();
			cnStr=protocol+"://"+serverIP+":"+serverPort+"/"+name;
			
			Class.forName(driver);
			//above statement must in Web App,not in Console App
		return DriverManager.getConnection(cnStr,user,password);	
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
