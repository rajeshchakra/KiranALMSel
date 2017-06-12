# Integrating Selenium Tests with HP ALM.

The requirement is to execute the Selenium scripts from ALM and to update back the results in ALM. From the research done till now we have got to know that it is possible by using VAPI-XP script, XTools.Run and Ant.

However the below solution is compatible with mercury ALM.

Register HP ALM Client via ALM>>Help>>Addins page.
Download Jacob dll(Both 64,32 bit), jacob jar
Add it to your project path.(DLL--> Just copypaste to project path), Jacob.jar-->Add it to build path.
Create a class
Find the SeleniumALMIntegrationExample.java file which gives you some idea about how to use COM API in java+Jacob


