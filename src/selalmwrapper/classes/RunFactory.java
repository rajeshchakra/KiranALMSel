package selalmwrapper.classes;

import com.jacob.com.Dispatch;

import selalmwrapper.classes.Run;


public class RunFactory
{
  private Dispatch runFactory;
  private Dispatch tsTest;
  
  public RunFactory(Dispatch tsTest)
  {
    this.tsTest = tsTest;
    this.runFactory = init();
  }
  
  private Dispatch init() {
    Dispatch runFactory = Dispatch.get(this.tsTest, "RunFactory").toDispatch();
    return runFactory;
  }
  
  public Run addItem() {
    Dispatch run = Dispatch.call(this.runFactory, "AddItem", new Object[] { "Null" }).toDispatch();
    
    return new Run(run);
  }
}