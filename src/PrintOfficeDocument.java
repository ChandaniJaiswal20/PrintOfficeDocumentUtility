package PrintOfficeDocumentUtility;



import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.ipas.commons.CDocumentImage;
import org.ipas.commons.CFileId;
import org.ipas.commons.CMergeData;
import org.ipas.commons.CMergeDataList;
import org.ipas.commons.COfficedoc;
import org.ipas.commons.COfficedocList;
import org.ipas.commons.COutputFieldCodeList;
import org.ipas.commons.COutputFieldList;
import org.ipas.commons.CUserId;
import org.ipas.commons.CommonsProxyFactory;
import org.ipas.commons.ICommonServices;
import org.ipas.commons.IOfficedoc;
import org.ipas.commons.IOutputField;
import org.ipas.proxy.IpasDateTime;
import org.ipas.proxy.IpasInteger;


public class PrintOfficeDocument {

	public static Logger logger	= Logger.getLogger(PrintOfficeDocument.class);
	
	public   void printOfficedoc(String userId,String folder, CommonsProxyFactory commonsProxyFactory, String counter) throws Exception
	    {

		  //-------------------------------------------------------------------------

	    	// the office docs from the connected user are printed and rendered in the browser
	    	// get interfaces from SessionInfo
			COfficedocList originalOfficedocList =	new COfficedocList(); ;
			Integer	noOfIteration	=	Integer.parseInt(counter);
			IOfficedoc iOfficedoc = commonsProxyFactory.getIOfficedoc();
			IOutputField iOutputField = commonsProxyFactory.getIOutputField();
			ICommonServices iCommonServices = commonsProxyFactory.getICommonServices();
			
			//---------------------------------------------------------------------

			// get folder containing the officedocs templates
			//	String folder = iCommonServices.mConfigParamRead("DirectorioOficios"); //$NON-NLS-1$

			CUserId connectedUserId = new CUserId();
			connectedUserId.setUserNbr(new IpasInteger(userId));
			
			// get list of office documents to be printed (criteria is just "from connected user")
			COfficedocList officedocList = iOfficedoc.mGetToBePrintedList("", new CFileId(), new IpasDateTime(), //$NON-NLS-1$
					new IpasDateTime(), new Boolean(true), new Boolean(false),
					connectedUserId);
			
			logger.debug("Number of office documents that are in (to be printed) status"+officedocList.size());
			
			originalOfficedocList	=	officedocList;
		
			// set message text
			Integer qty = officedocList.size();
			if (qty.intValue() == 0) {
			
				
				logger.debug("No OfficeDocuments Were Pending To Be Printed");
				logger.debug("Going to exit programme");
				
				System.exit(0);
			} else {
				
				logger.debug("Going to print OfficeDocuments");
			
			}
			
			int count =0;
			
			
			while(officedocList.size()!=0){
				
				
			 logger.debug("going to execute  slot.....::" + count);
			 
			 
			 
				if((officedocList.size()<=noOfIteration)){
					
					noOfIteration	=	officedocList.size();
				}
				
			for(int i1=0;i1<noOfIteration;i1++){
				
				
				
				COfficedoc officedoc = officedocList.get(i1);
				
				
				logger.debug("going to process::"+ officedoc.getOfficedocName());
				logger.debug("office doc "+ officedoc.getOfficedocType() +" "+officedoc.getOfficeDocId() );
				
				
				
				// add the template to the list (if not already there)
					String path=folder + "\\" + officedoc.getTemplateFilePath(); //$NON-NLS-1$
					
					
					logger.debug("template path of office doc :: "+ path);
		
					CDocumentImage documentImage = new CDocumentImage();
					
					//logger.debug(documentImage.);
					
					String instanceNbrListAsString = ""; //$NON-NLS-1$
					// get list of field codes used in the template
					COutputFieldCodeList templateOutputFieldCodeList = iOutputField
							.mGetTemplateOutputFieldCodeList(path);
		
					CMergeDataList mergeDataList = new CMergeDataList();
					COutputFieldCodeList includeOutputFieldCodeList = templateOutputFieldCodeList;
					COutputFieldCodeList excludeOutputFieldCodeList = new COutputFieldCodeList();
					COutputFieldList outputFieldList = iOfficedoc
							.mGetMailmergeFieldList(officedoc.getOfficeDocId(),
									includeOutputFieldCodeList,
									excludeOutputFieldCodeList);
					// add merge data to the list
					CMergeData mergeData = new CMergeData();
					mergeData.setOutputFieldList(outputFieldList);
					mergeDataList.add(mergeData);
		
					// run MailMerge
					documentImage = iOutputField.mMergeDocument(path, mergeDataList,
							new COutputFieldList(), "1", "", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					officedoc.setContentData(documentImage.getDocumentImage());
					officedoc.setContentType("DOC"); //$NON-NLS-1$
					
					logger.debug("office doc image::"+documentImage.getDocumentImage());
					
					iOfficedoc.mUpdate(officedoc); 
					
					
					officedocList.remove(i1);
		
				}
			
			count++;
			
			}
			

			// generate the "print" action in the processes of the just printed
			// officedocs
			for (COfficedoc officedoc : originalOfficedocList) {
				
				logger.debug("going to generate  print action on officedoc::"+officedoc.getOfficeDocId());
				
				iOfficedoc.mUpdateAsPrinted(officedoc.getOfficeDocId());
			}
	    
		  
		  
		  
		  
	    }
	

}

