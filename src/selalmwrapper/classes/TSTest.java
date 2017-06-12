package selalmwrapper.classes;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import selalmwrapper.classes.AttachmentFactory;
import selalmwrapper.classes.RunFactory;
import selalmwrapper.ITestCase;
import selalmwrapper.enums.StatusAs;

public class TSTest implements ITestCase {
	private Dispatch test;
	private Dispatch tsTest;

	public TSTest(ActiveXComponent almObject, Dispatch test) {
		this.test = test;
		this.tsTest = init();
	}

	public TSTest(Dispatch tsTest) {
		this.tsTest = tsTest;
	}

	private Dispatch init() {
		Dispatch tsTest = Dispatch.call(this.test, "Item", new Object[] { Integer.valueOf(1) }).toDispatch();
		return tsTest;
	}

	public String getName() {
		String name = Dispatch.call(this.tsTest, "Name").getString();
		return name;
	}

	public String getTestName() {
		String testName = Dispatch.call(this.tsTest, "TestName").getString();
		return testName;
	}

	public void putStatus(StatusAs as) {
		Dispatch.put(this.tsTest, "Status", as.getStatus().trim());
	}

	public RunFactory getRunFactory() {
		return new RunFactory(this.tsTest);
	}

	public void post() {
		Dispatch.call(this.tsTest, "Post");
	}

	public AttachmentFactory getAttachments() {
		return new AttachmentFactory(this.tsTest);
	}
}