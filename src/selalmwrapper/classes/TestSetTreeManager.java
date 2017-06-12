package selalmwrapper.classes;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.Dispatch;

import selalmwrapper.classes.TestSetFolder;
import selalmwrapper.exceptions.ALMServiceException;



public class TestSetTreeManager
{
  private ActiveXComponent almObject;
  private Dispatch testSetTreeManager;
  private static final String ROOT = "Root";
  
  public TestSetTreeManager(ActiveXComponent almObject)
  {
    this.almObject = almObject;
    this.testSetTreeManager = init();
  }
  
  private Dispatch init() {
    Dispatch testSetTreeManager = Dispatch.get(this.almObject, "TestSetTreeManager").toDispatch();
    
    return testSetTreeManager;
  }
  
  public TestSetFolder getNodeByPath(String testSetFolderPath) throws ALMServiceException
  {
    Dispatch testSetFolder;
    try {
      testSetFolder = Dispatch.call(this.testSetTreeManager, "NodeByPath", new Object[] { ROOT+"\\" + testSetFolderPath }).toDispatch();
    }
    catch (ComFailException e)
    {
      throw new ALMServiceException("The Given Test Set Folder Path \"" + testSetFolderPath + "\" Not Found");
    }
    
    return new TestSetFolder(this.almObject, testSetFolder);
  }
}