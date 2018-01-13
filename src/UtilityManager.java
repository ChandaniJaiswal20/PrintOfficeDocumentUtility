package PrintOfficeDocumentUtility;


import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.ipas.commons.CPatent;
import org.ipas.commons.CProcessId;
import org.ipas.commons.CUserdoc;
import org.ipas.commons.CommonsProxyFactory;
import org.ipas.proxy.IpasException;


public class UtilityManager {

	 static{
	        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
	        System.setProperty("current.date.time", dateFormat.format(new Date()));
	    }
	
	 
	 private static UtilityManager uniqueInstance =	null;
	
	
	  private UtilityConfiguration configuration 	=	null;
	  private 	final	DBdataFetcher	dataFetcher	; 
	  private PrintOfficeDocument printOfficeDoc	=	new PrintOfficeDocument();
	  private	CommonsProxyFactory commonsProxyFactory;
	
	  private 	static final	String  LOG4JCONFIGFILE	= "log4j.properties";
	  private	static Connection conn	=null;
	  private 	static Logger logger	=	Logger.getLogger(UtilityManager.class);
	  
	
	private UtilityManager()
	{

		PropertyConfigurator.configure(LOG4JCONFIGFILE);
		logger.info("Initializing Utility...........................................!!!!!");
		logger.debug("################################################");
		logger.debug("=================================================");
		
		logger.debug("LOG4JCONFIGFILE:::"+LOG4JCONFIGFILE);
		configuration 	=	new UtilityConfiguration();	//aggregation (has a)
		configuration.loadConfiguration();
		configuration.checkValuesOfConfigFile();
		commonsProxyFactory = new CommonsProxyFactory(configuration.iiopHost +":"+configuration.iiopPort);
		logger.debug("Obtaining Database Connection....");
		dataFetcher	= new DBdataFetcher(configuration.databaseHost,configuration.databasePort,
					  configuration.databaseTool,configuration.databaseName,configuration.userName,configuration.password);
		
		logger.info("Initializing Completed...............................................!!!!!!");		
		logger.debug("=================================================");
		logger.debug("################################################");
	}
	

	
		public static  UtilityManager getInstance(){
			
					if(uniqueInstance==null)
					{	
						uniqueInstance	=	 new UtilityManager();
					}
					
					
			return uniqueInstance;
					
		}
	
		public UtilityConfiguration getConfiguration(){
			return configuration;
						
		}
	


	
	public void configureLogger(){
	
		
		PropertyConfigurator.configure(LOG4JCONFIGFILE);
			
	}
		
	private   String getTemplateFolderPath() {
		
	 return	dataFetcher.getTemplateFolderPath();
		
	}	

	
	public void  printOfficeDocument(){
	 
		String templateFolderPath	=	getTemplateFolderPath();
		
		try {
			
			printOfficeDoc.printOfficedoc(configuration.ipasUserId, templateFolderPath,commonsProxyFactory,configuration.counter);
			
			
			logger.debug("All the pending office documents are printed successfully......");
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	}



	
	
	public void stopUtility(){
		if(dataFetcher.getConnection()!=null){
			logger.debug("Closing database connection..........!!");
			dataFetcher.closeConnection();
			logger.debug("Database connection closed..........!!");
			
		}
	
		logger.info("Processing Completed.......................!!!");
		logger.info("=================================================");
		logger.info("Please check processed details in log file generated in log folder");
		logger.info("********************************************************");
	}
	
}
