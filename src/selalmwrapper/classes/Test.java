package selalmwrapper.classes;

import com.jacob.com.Dispatch;

import selalmwrapper.classes.DesignStepFactory;


public class Test
{
  private Dispatch tsTest;
  private Dispatch test;
  
  public Test(Dispatch tsTest)
  {
    this.tsTest = tsTest;
    this.test = init();
  }
  
  private Dispatch init() {
    Dispatch test = Dispatch.get(this.tsTest, "Test").toDispatch();
    return test;
  }
  
  public DesignStepFactory getDesignStepFactory() {
    return new DesignStepFactory(this.test);
  }
}


/* Location:              Z:\Downloads\ALM_Selenium\ATU ALM Service Wrapper\version .\ATU_ALM_ServiceWrapper_..jar!\atu\alm\wrapper\classes\Test.class
 * Java compiler version:  (.)
 * JD-Core Version:       ..
 */