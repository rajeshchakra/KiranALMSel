package selalmwrapper.classes;

import com.jacob.com.Dispatch;


public class DesignStepFactory
{
  private Dispatch test;
  
  public DesignStepFactory(Dispatch test)
  {
    this.test = test;
    init();
  }
  
  private Dispatch init() {
    Dispatch designStepFactory = Dispatch.get(this.test, "DesignStepFactory").toDispatch();
    
    return designStepFactory;
  }
}