package selalmwrapper.classes;

import com.jacob.com.Dispatch;

import selalmwrapper.classes.AttachmentStorage;

public class Attachment
{
  private Dispatch currentAttachment;
  
  public Attachment(Dispatch currentAttachment)
  {
    this.currentAttachment = currentAttachment;
  }
  
  public void setDescription(String attachmentDescription)
  {
    Dispatch.put(this.currentAttachment, "Description", attachmentDescription);
  }
  
  public void setFileName(String filePath)
  {
    System.out.println("FILE PATH" + filePath);
    Dispatch.put(this.currentAttachment, "FileName", filePath);
  }
  
  public void setType(String type)
  {
    Dispatch.put(this.currentAttachment, "Type", type);
  }
  
  public void post() {
    Dispatch.call(this.currentAttachment, "Post");
  }
  
  public AttachmentStorage getAttachmentStorage() {
    return new AttachmentStorage(this.currentAttachment);
  }
}