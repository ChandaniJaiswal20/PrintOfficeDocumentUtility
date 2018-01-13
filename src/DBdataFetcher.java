package PrintOfficeDocumentUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.ipas.proxy.IpasInteger;

public class DBdataFetcher {
	
	UtilityConfiguration  uc	=	null;
	
	
	private String databaseHost	= null; 
	private String databasePort	=	null;
	private String databaseTool	=	null;
	private String databaseName	=	null;
	private	String userName		=	null;
	private	String password		=	 null;
	private	Connection connection	=	null;
	
	public static Logger logger	=	Logger.getLogger(DBdataFetcher.class);
	
	
	
	
	public DBdataFetcher(String databaseHost, String databasePort,
			String databaseTool, String databaseName,String userName,String password ){
		
		
		 this.databaseHost	= databaseHost; 
		 this.databasePort	=	databasePort;
		 this.databaseTool	=	databaseTool;
		 this.databaseName	=	databaseName;
		 this.userName		=	userName;
		 this.password		=	 password;
		 
			init();
		
	}
	
	public void init(){
		
		if(connection==null)
			configureConnection();
		
	}
	
	public Connection getConnection(){
		
		return connection;
	}
	
	
	public void closeConnection(){
		
		 if(connection!=null){
		 
		 try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
		
	}
	
	
	
	public  void 	configureConnection() {
		// TODO Auto-generated method stub
		//Connection conn = null;
		if( databaseTool.equalsIgnoreCase("oracle") ){
			
			logger.info("Database Type is Oracle....");
			
		//("jdbc:oracle:thin:@" + databaseHost + ":" + databasePort + ":"+databaseName,userName,password  );
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection	=	DriverManager.getConnection("jdbc:oracle:thin:@" + databaseHost + ":" + databasePort + ":"+databaseName,userName,password  );
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			logger.error("Error Occured while loading OracleDriver class ", e);
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error Occured while obtaining connection ", e);
		//	e.printStackTrace();
		}
		}
		else if(databaseTool.equalsIgnoreCase("sql")){
			
			logger.info("Database Type is MSSQL....");
			
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				
				connection	=	DriverManager.getConnection( "jdbc:sqlserver://"+databaseHost+ ":" + databasePort +";" +
			    		  "databaseName="+databaseName+";"+"user="+userName+";"+"password="+password);
			} catch (ClassNotFoundException e) {
				logger.error("Error Occured while loading SQLDriver class ", e);
			//	e.printStackTrace();
			} catch (SQLException e) {
				logger.error("Error Occured While obtaining  Connection: ", e);
			//	e.printStackTrace();
			}
			
			
		}
		
		
	
		
		
	}
	
	
	

	
	
	
	
		
	

	

		

		public String getTemplateFolderPath() {
			
			ResultSet rs	= null;
			Statement ps	= null;
	
			String query	=	"SELECT VALUE FROM CF_CONFIG_PARAM where config_code = 'DirectorioOficios' ";
			String folderPath	=	null;
		
	
		 try {
			 init();
			ps	=	connection.createStatement();
			
			
			
			rs	=	 ps.executeQuery(query);
			
			while(rs.next()){
				
			folderPath	=	rs.getString("VALUE");
	
			}
			
			logger.debug("template folder path::"+folderPath);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error Occured while Reading templateFolderPath from CF_CONFIG_PARAM table. ", e);
			e.printStackTrace();
		}
		 
			finally{
				 if (rs != null){
					 
					 try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				 
				 
				 if(ps!=null){
				 
					 try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				 
				 
		
		}
		 
		return folderPath;
		 
	
			
		}

}
