package selalmwrapper.enums;

public enum DefectStatus {
	NEW("New"), OPEN("Open"), REJECTED("Rejected"), FIXED("Fixed"), REOPEN("Reopen"), CLOSED("Closed");

	private String status;

	private DefectStatus(String status) {
		setStatus(status);
	}

	public String getStatus() {
		return this.status;
	}

	private void setStatus(String status) {
		this.status = status;
	}
}