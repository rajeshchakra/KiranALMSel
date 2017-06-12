package selalmwrapper.collection;

import java.util.ArrayList;

@SuppressWarnings("hiding")
public class ListWrapper<Object> extends ArrayList<Object> {
	private static final long serialVersionUID = 1L;
	public int getCount() {
		return size();
	}
}
