package selalmwrapper.exceptions;


public class ALMServiceException extends Exception {

	private static final long serialVersionUID = -2623930816332871225L;
	private String message;

	public ALMServiceException(String message) {
		super(message);
		this.message = message;
	}

	public String toString() {
		return "ALM Service Wrapper Exception Details:  " + this.message;
	}
}