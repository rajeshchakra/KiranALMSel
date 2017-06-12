package selalmwrapper.classes;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import selalmwrapper.classes.AttachmentFactory;
import selalmwrapper.classes.TSTestFactory;
import selalmwrapper.ITestSet;


public class TestSet
  implements ITestSet
{
  private ActiveXComponent almObject;
  private Dispatch testSet;
  
  public TestSet(ActiveXComponent almObject, Dispatch testSet)
  {
    this.almObject = almObject;
    this.testSet = testSet;
  }
  
  public TSTestFactory getTSTestFactory() {
    return new TSTestFactory(this.almObject, this.testSet);
  }
  
  public String getName() {
    String name = Dispatch.call(this.testSet, "Name").getString();
    return name;
  }
  
  public AttachmentFactory getAttachments() {
    return new AttachmentFactory(this.testSet);
  }
}