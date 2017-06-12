package selalmwrapper.classes;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.Dispatch;

import selalmwrapper.classes.BugFactory;
import selalmwrapper.classes.TestFactory;
import selalmwrapper.classes.TestSetTreeManager;
import selalmwrapper.classes.TreeManager;
import selalmwrapper.bean.ServerDetails;
import selalmwrapper.exceptions.ALMServiceException;






public class TDConnection
{
  private ActiveXComponent almObject = null;
  
  public TDConnection(ActiveXComponent almObject, ServerDetails serverDetails) {
    setAlmObject(almObject);
  }
  
  public BugFactory getBugFactory() {
    return new BugFactory(getAlmObject());
  }
  
  public TestFactory getTestFactory() {
    return new TestFactory(getAlmObject());
  }
  
  public TreeManager getTreeManager() {
    return new TreeManager(getAlmObject());
  }
  
  public TestSetTreeManager getTestSetTreeManager() {
    return new TestSetTreeManager(getAlmObject());
  }
  

  public boolean initConnectionEx(String url)
    throws ALMServiceException
  {
    try
    {
      Dispatch.call(getAlmObject(), "InitConnectionEx", new Object[] { url });
      return true;
    } catch (ComFailException e) {
      throw new ALMServiceException("Unable to Establish connection for the Given URL: " + url);
    }
  }
  







  public boolean login(String username, String password)
    throws ALMServiceException
  {
    try
    {
      Dispatch.call(getAlmObject(), "login", new Object[] { username, password });
      return true;
    } catch (ComFailException e) {
      throw new ALMServiceException("Invalid Username or Password");
    }
  }
  


  public boolean connect(String domain, String project)
    throws ALMServiceException
  {
    try
    {
      Dispatch.call(getAlmObject(), "connect", new Object[] { domain, project });
      return true;
    } catch (ComFailException e) {
      throw new ALMServiceException("Invalid Project or Domain");
    }
  }
  












  public boolean isConnected()
  {
    boolean isLoggedIn = false;
    try {
      isLoggedIn = Dispatch.call(getAlmObject(), "Connected").getBoolean();
    }
    catch (IllegalStateException e) {
      return isLoggedIn;
    }
    return isLoggedIn;
  }
  






  public boolean isLoggedIn()
  {
    boolean isLoggedIn = false;
    try {
      isLoggedIn = Dispatch.call(getAlmObject(), "loggedIn").getBoolean();
    } catch (IllegalStateException e) {
      return isLoggedIn;
    }
    return isLoggedIn;
  }
  







  public boolean disconnect()
  {
    Dispatch.call(getAlmObject(), "disconnectProject");
    return true;
  }
  




  public boolean logout()
  {
    Dispatch.call(getAlmObject(), "logout");
    return true;
  }
  




  public boolean releaseConnection()
  {
    Dispatch.call(getAlmObject(), "releaseConnection");
    return true;
  }
  


  public ActiveXComponent getAlmObject()
  {
    return this.almObject;
  }
  



  public void setAlmObject(ActiveXComponent almObject)
  {
    this.almObject = almObject;
  }
}
