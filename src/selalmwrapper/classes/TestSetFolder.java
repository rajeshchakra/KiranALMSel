package selalmwrapper.classes;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import selalmwrapper.classes.TestSet;
import selalmwrapper.exceptions.ALMServiceException;

public class TestSetFolder {
	private ActiveXComponent almObject;
	private Dispatch testSetFolder;

	public TestSetFolder(ActiveXComponent almObject, Dispatch testSetFolder) {
		this.almObject = almObject;
		this.testSetFolder = testSetFolder;
	}

	public TestSet findTestSet(String testSetName, int searchTestSetID) throws ALMServiceException {
		Dispatch listOfTestSet = Dispatch
				.call(this.testSetFolder, "FindTestSets", new Object[] { testSetName, Boolean.valueOf(true), null })
				.toDispatch();

		Dispatch testSet = null;
		try {
			int count = Dispatch.call(listOfTestSet, "Count").getInt();

			for (int i = 1; i <= count; i++) {
				testSet = Dispatch.call(listOfTestSet, "Item", new Object[] { Integer.valueOf(i) }).toDispatch();
				int testSetID = Dispatch.call(testSet, "ID").getInt();
				if (searchTestSetID == testSetID) {
					return new TestSet(this.almObject, testSet);
				}
			}
			throw new ALMServiceException("The Given Test Set Name \"" + testSetName + "\" Not Found");
		} catch (NullPointerException e) {
			throw new ALMServiceException("The Given Test Set Name \"" + testSetName + "\" Not Found ");
		}
	}

	public int getCount() {
		int count = Dispatch.call(this.testSetFolder, "Count").getInt();
		return count;
	}

	public String getName() {
		String name = Dispatch.call(this.testSetFolder, "Name").getString();
		return name;
	}

	public String getPath() {
		String path = Dispatch.call(this.testSetFolder, "Path").getString();
		return path;
	}
}
