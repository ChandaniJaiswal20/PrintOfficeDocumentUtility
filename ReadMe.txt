			++ READ ME ++
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
PRINT OFFICE Document Utility
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Functionality of the utility:
This utility has been created for an office that had lot of pending office documents to be printed.

Issue: In IPAS 2.7.0, when lot of pending office documents need to be printed, at times the IPAS application throws java heap size issue.
In order to avoid, this utility uses the Ipas API to do the Printing action.
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

REQUIREMENT OF UTILITY:
While executing printing for large number of pending Office documents, the IPAS Application fails due to JAVA Heap Size issue.
Offices that do not perform the Printing action on a regular basis will be having issues in getting the printing done at a later stage due to the issue mentioned above.
Hence, the alternative solution is to create a dummy user and move the pending office documents in the name of the dummy user and then executing the printing action in batches of size that can be printed without encountering java heap size error. 
Since, this process can be cumbersome for offices that have lot of pending office documents; a utility has been developed to automate this process. 
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

SCOPE OF UTILITY
This utility is generic to all IP Offices running IPAS2.7.0 and helpful for those who would want to execute the printing action for a large number of pending office documents. 
This utility is packaged with IPAS 2.7.0c version of IpasApi.jar file.
The utility can also be run by replacing the IpasApi.jar file (placed in the lib folder of the utility) with the IpasApi.jar file of another version of IPAS 2.7.0.
The utility has been tested to run with both Oracle 11G and MSSQL 2008 databases and the same can be configured using the printOfficeDocConfiguration.properties file in this utility.

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

Execution steps:

This utility reads the pending office documents for a particular user configured in the properties folder.
Then it runs the printing office documents for them.


++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

Prerequisites:

Jdk(Java version 1.6.*) should be installed in the running system.
Set JAVA_HOME in environment variables to point to the jdk directory.



++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
OfficeDocPrintingUtility folder should contains following folders/files:

1)printOfficeDocConfiguration.properties		(file)
2)lib      			(folder)
3)PrintOfficeDocumentUtility.jar	(file)
4)ReadMe.txt			(file)
5)run.bat			(file)
6)log				(folder: will be generated and contains logs)
7)log4j.properties 	(file)





'printOfficeDocConfiguration.properties' files contains the details of domain, the IPAS user and the number of files for which the printing needs to be done in a batch.
 The property values need to be modified accordingly before running this program.

'lib' folder contains all jar files that is required for this program.

'PrintOfficeDocumentUtility.jar' is main executable jar .

'ReadMe.txt' file contains the instructions to run this program.

'run.bat' file contains script to execute this batch utility program.

'log' folder will get generated on running the utility and will contain the details of the run.

'log4j.properties' contains configuration details about the logging (Log generation) of the utility program

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
HOW TO RUN PROGRAM?
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

1) Open 'printOfficeDocConfiguration.properties' file and change properties of all the property attributes and save it.
2) Ensure that the GLASSFISH HOME & JAVA HOME is set correctly in run.bat file.
3) Double click on 'run.bat' to start the utility program.
4) After successful executing of this script, check the log file generated for the run.












