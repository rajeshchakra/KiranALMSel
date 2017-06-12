package selalmwrapper.classes;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import selalmwrapper.classes.Bug;


public class BugFactory
{
  private ActiveXComponent almObject;
  private Dispatch bugFactory;
  
  public BugFactory(ActiveXComponent almObject)
  {
    this.almObject = almObject;
    this.bugFactory = init();
  }
  
  private Dispatch init() {
    Dispatch bugFactory = Dispatch.call(this.almObject, "BugFactory").toDispatch();
    
    return bugFactory;
  }
  
  public Bug addItem() {
    return new Bug(this.bugFactory);
  }
}