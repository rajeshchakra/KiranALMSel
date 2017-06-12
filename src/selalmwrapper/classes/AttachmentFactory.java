package selalmwrapper.classes;

import com.jacob.com.Dispatch;

import selalmwrapper.classes.Attachment;


public class AttachmentFactory
{
  private Dispatch attachmentFactory;
  private Dispatch bug;
  
  public AttachmentFactory(Dispatch bug)
  {
    this.bug = bug;
    this.attachmentFactory = init();
  }
  
  private Dispatch init() {
    Dispatch attachmentFactory = Dispatch.call(this.bug, "Attachments").toDispatch();
    
    return attachmentFactory;
  }
  
  public Attachment addItem(String fileName) {
    Dispatch attachment = Dispatch.call(this.attachmentFactory, "AddItem", new Object[] { fileName }).toDispatch();
    
    return new Attachment(attachment);
  }
}