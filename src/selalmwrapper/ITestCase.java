package selalmwrapper;

import selalmwrapper.HasAttachmentFeature;
import selalmwrapper.classes.RunFactory;

public abstract interface ITestCase
  extends HasAttachmentFeature
{
  public abstract RunFactory getRunFactory();
}