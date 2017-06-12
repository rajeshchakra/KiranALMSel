package selalmwrapper;

import selalmwrapper.HasAttachmentFeature;
import selalmwrapper.classes.StepFactory;

public abstract interface ITestCaseRun
  extends HasAttachmentFeature
{
  public abstract StepFactory getStepFactory();
}