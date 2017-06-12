 package selalmwrapper.classes;
 
 import selalmwrapper.classes.AttachmentFactory;
import selalmwrapper.IDefect;
import selalmwrapper.enums.DefectPriority;
import selalmwrapper.enums.DefectSeverity;
import selalmwrapper.enums.DefectStatus;

import com.jacob.com.Dispatch;
 import com.jacob.com.Variant;
 
 
 
 public class Bug
   implements IDefect
 {
   private Dispatch bugFactory;
   private Dispatch bug;
   
   public Bug(Dispatch bugFactory)
   {
    this.bugFactory = bugFactory;
    this.bug = init();
   }
   
   public AttachmentFactory getAttachments() {
    return new AttachmentFactory(this.bug);
   }
   
   private Dispatch init() {
    Dispatch bug = Dispatch.call(this.bugFactory, "AddItem", new Object[] { "" }).toDispatch();
    return bug;
   }
   
   public void setAssignedTo(String assignedTo) {
    Dispatch.put(this.bug, "AssignedTo", assignedTo);
   }
   
   public void setSeverity(DefectSeverity severity) {
    Dispatch.invoke(this.bug, "Field",4 , new Object[] { "BG_SEVERITY", new Variant(severity.getSeverity()) }, new int[1]);
   }
   
 
   public void setDescription(String description)
   {
    Dispatch.invoke(this.bug, "Field",4 , new Object[] { "BG_DESCRIPTION", new Variant(description) }, new int[1]);
   }
   
   public void setProject(String project)
   {
    Dispatch.invoke(this.bug, "Field",4 , new Object[] { "BG_PROJECT", new Variant(project) }, new int[1]);
   }
   
   public void isReproducible(boolean isReproducible) {
     String reproducible;
    if (isReproducible) {
      reproducible = "Y";
     } else {
      reproducible = "N";
     }
    Dispatch.invoke(this.bug, "Field",4 , new Object[] { "BG_REPRODUCIBLE", new Variant(reproducible) }, new int[1]);
   }
   
 
 
 
   public void setDetectionDate(String date)
   {
    Dispatch.invoke(this.bug, "Field",4 , new Object[] { "BG_DETECTION_DATE", new Variant(date) }, new int[1]);
   }
   
   public int getDefectID()
   {
    int id = Dispatch.call(this.bug, "ID").getInt();
    return id;
   }
   
   public void setStatus(DefectStatus status) {
    Dispatch.put(this.bug, "Status", status.getStatus());
   }
   
   public void setPriority(DefectPriority priority) {
    Dispatch.put(this.bug, "Priority", priority.getPriority());
   }
   
   public void setSummary(String summary) {
    Dispatch.put(this.bug, "Summary", summary);
   }
   
   public void setDetectedBy(String detectedBy) {
    Dispatch.put(this.bug, "DetectedBy", detectedBy);
   }
   
   public void save() {
    Dispatch.call(this.bug, "Post");
   }
 }