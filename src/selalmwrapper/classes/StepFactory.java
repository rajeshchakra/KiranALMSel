package selalmwrapper.classes;

import com.jacob.com.Dispatch;

import selalmwrapper.classes.Step;


public class StepFactory
{
  private Dispatch stepFactory;
  private Dispatch run;
  
  public StepFactory(Dispatch run)
  {
    this.run = run;
    this.stepFactory = init();
  }
  
  private Dispatch init() {
    Dispatch stepFactory = Dispatch.get(this.run, "StepFactory").toDispatch();
    return stepFactory;
  }
  
  public Step addItem() {
    Dispatch step = Dispatch.call(this.stepFactory, "AddItem", new Object[] { "Null" }).toDispatch();
    
    return new Step(step);
  }
}