package PrintOfficeDocumentUtility;


import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;




public class UtilityConfiguration {

	ConfigParam	config	=	null;
	public String iiopPort;
	public String iiopHost;
	public String databaseHost;
	public String databasePort;
	public String databaseTool;
	public String databaseName;
	public String userName;
	public String password;
	public String ipasUserId;
	public String counter;
//counter
	public static Logger logger	= Logger.getLogger(UtilityConfiguration.class);

	public UtilityConfiguration loadConfiguration() {
		logger.debug("loading configuration.....");
			config	= new ConfigParam("printOfficeDocConfiguration.properties");
		//	patentAnnuityUtilityFolder	=	config.getProperty("patentAnnuityUtilityFolder");
			iiopPort	= config.getProperty("iiop.port");
			iiopHost	= config.getProperty("iiop.host");
			databaseHost	= config.getProperty("database.host");
			databasePort	= config.getProperty("database.port");
			databaseTool	= config.getProperty("database.tool");
			databaseName	=	config.getProperty("database.sid");
			userName		=	config.getProperty("database.user.name");
			password		=	config.getProperty("database.user.password");
			ipasUserId		=	config.getProperty("ipas.user.id");
			counter		=	config.getProperty("counter");
			
			logger.debug("IIOP PORT:::"+iiopPort);
			logger.debug("iiopHost:::"+iiopHost);
			logger.debug("databaseHost:::"+databaseHost);
			logger.debug("databasePort:::"+databasePort);
			logger.debug("databaseTool:::"+databaseTool);
			logger.debug("databaseName:::"+databaseName);
			logger.debug("userName:::"+userName);
			logger.debug("ipasUserId:::"+ipasUserId);
			logger.debug("ipasUserId:::"+counter);
		
			
			
			return this;
				
	}
	
	
	public void checkValuesOfConfigFile(){
		
	
		

		if(iiopPort==null||iiopPort.isEmpty()){
			
			logger.error("Please specify value of  iiopPort in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  iiopPort in patentAnnuitityConfig.properties");
		}
		
		if(iiopHost==null||iiopHost.isEmpty()){
			
			logger.error("Please specify value of  iiopHost in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  iiopHost in patentAnnuitityConfig.properties");
		}
		
		if(databaseHost==null||databaseHost.isEmpty()){
			
			
			logger.error("Please specify value of  databaseHost in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  databaseHost in patentAnnuitityConfig.properties");
		}
		
		if(databasePort==null||databasePort.isEmpty()){
			
			
			logger.error("Please specify value of  databasePort in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  databasePort in patentAnnuitityConfig.properties");
		}
		
		if(databaseTool==null||databaseTool.isEmpty()){
			
			logger.error("Please specify value of  databaseTool in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  databaseTool in patentAnnuitityConfig.properties");
			
		}
		else if(!(databaseTool.equalsIgnoreCase("sql")||databaseTool.equalsIgnoreCase("oracle"))){
			
			logger.error("Please specify correct database tool in patentAnnuitityConfig.properties ie. for oracle database::<oracle> and for sql database::<sql>");
			throw new MissingConfigurationException("Please specify correct database tool in patentAnnuitityConfig.properties ie. for oracle database::<oracle> and for sql database::<sql>");
		}
		
		if(databaseName==null||databaseName.isEmpty()){
			
			
			logger.error("Please specify value of  databaseName in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  databaseName in patentAnnuitityConfig.properties");
		}
		
		if(userName==null||userName.isEmpty()){
			
			logger.error("Please specify value of  userName in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  userName in patentAnnuitityConfig.properties");
		}
		
		if(password==null||password.isEmpty()){
			
			
			logger.error("Please specify value of  password in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  password in patentAnnuitityConfig.properties");
		}
		
		
	if(ipasUserId==null||ipasUserId.isEmpty()){
			
			logger.error("Please specify value of  ipasUserId in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  ipasUserId in patentAnnuitityConfig.properties");
		}
		
		if(counter==null||counter.isEmpty()){
			
			
			logger.error("Please specify value of  counter in patentAnnuitityConfig.properties");
			throw new MissingConfigurationException("Please specify value of  counter in patentAnnuitityConfig.properties");
		}
		
	
		
	}
	

	public static void main(String[] args) {
		
		UtilityConfiguration uc = new UtilityConfiguration();
	
				
		
		String		log4jConfigFile	=	"D://PatentAnnuityUtility//log4j.properties";
			
			PropertyConfigurator.configure(log4jConfigFile);
			logger.info("log4jConfigFile:::"+log4jConfigFile);	
	
		uc.loadConfiguration();
		uc.checkValuesOfConfigFile();
		
		
		
	}
	
}
