package selalmwrapper.classes;

import com.jacob.com.Dispatch;


public class AttachmentStorage
{
  private Dispatch currentAttachment;
  private Dispatch attachmentStorage;
  
  public AttachmentStorage(Dispatch currentAttachment)
  {
    this.currentAttachment = currentAttachment;
    this.attachmentStorage = init();
  }
  
  private Dispatch init() {
    Dispatch attachmentStorage = Dispatch.call(this.currentAttachment, "AttachmentStorage").toDispatch();
    
    return attachmentStorage;
  }
  
  public void clientPath(String directoryPath) {
    Dispatch.call(this.attachmentStorage, "ClientPath");
  }
  
  public void save(String fileName) {
    Dispatch.call(this.attachmentStorage, "Save", new Object[] { fileName, Boolean.valueOf(true) });
  }
  
  public String getErrorMessage() {
    return Dispatch.call(this.attachmentStorage, "GetLastError").getString();
  }
}