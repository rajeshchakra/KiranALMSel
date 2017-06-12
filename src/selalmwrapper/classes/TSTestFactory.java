package selalmwrapper.classes;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import selalmwrapper.collection.ListWrapper;

public class TSTestFactory {
	private Dispatch testSet;
	private Dispatch tsTestFactory;

	public TSTestFactory(ActiveXComponent almObject, Dispatch testSet) {
		this.testSet = testSet;
		this.tsTestFactory = init();
	}

	private Dispatch init() {
		Dispatch tsTestFactory = Dispatch.get(this.testSet, "TSTestFactory").toDispatch();

		return tsTestFactory;
	}

	public ListWrapper<TSTest> getNewList() {
		Dispatch listOfTests = Dispatch.call(this.tsTestFactory, "NewList", new Object[] { "" }).toDispatch();

		int count = Dispatch.call(listOfTests, "Count").getInt();
		ListWrapper<TSTest> listWrapper = new ListWrapper<TSTest>();
		for (int i = 1; i <= count; i++) {
			Dispatch dispatchTSTest = Dispatch.call(listOfTests, "Item", new Object[] { Integer.valueOf(i) }).toDispatch();
			TSTest tsTest = new TSTest(dispatchTSTest);
			listWrapper.add(tsTest);
		}
		return listWrapper;
	}
}