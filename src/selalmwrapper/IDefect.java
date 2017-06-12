package selalmwrapper;

import selalmwrapper.HasAttachmentFeature;
import selalmwrapper.classes.AttachmentFactory;
import selalmwrapper.enums.DefectPriority;
import selalmwrapper.enums.DefectSeverity;
import selalmwrapper.enums.DefectStatus;

public abstract interface IDefect
  extends HasAttachmentFeature
{
  public abstract void setDetectedBy(String paramString);
  
  public abstract void setAssignedTo(String paramString);
  
  public abstract void setPriority(DefectPriority paramDefectPriority);
  
  public abstract void setSeverity(DefectSeverity paramDefectSeverity);
  
  public abstract void setStatus(DefectStatus paramDefectStatus);
  
  public abstract void setSummary(String paramString);
  
  public abstract void setDetectionDate(String paramString);
  
  public abstract void setDescription(String paramString);
  
  public abstract void isReproducible(boolean paramBoolean);
  
  public abstract void setProject(String paramString);
  
  public abstract void save();
  
  public abstract int getDefectID();
  
  public abstract AttachmentFactory getAttachments();
}