package selalmwrapper.classes;

import com.jacob.com.Dispatch;

import selalmwrapper.classes.AttachmentFactory;
import selalmwrapper.classes.StepFactory;
import selalmwrapper.ITestCaseRun;
import selalmwrapper.enums.StatusAs;


public class Run
  implements ITestCaseRun
{
  private Dispatch run;
  
  public Run(Dispatch run)
  {
    this.run = run;
  }
  
  public void setStatus(StatusAs as) {
    Dispatch.put(this.run, "Status", as.getStatus());
  }
  
  public void setName(String runName) {
    Dispatch.put(this.run, "Name", runName);
  }
  
  public void post() {
    Dispatch.call(this.run, "Post");
  }
  
  public int getID() {
    return Dispatch.call(this.run, "ID").getInt();
  }
  
  public StepFactory getStepFactory() {
    return new StepFactory(this.run);
  }
  
  public AttachmentFactory getAttachments()
  {
    return new AttachmentFactory(this.run);
  }
}