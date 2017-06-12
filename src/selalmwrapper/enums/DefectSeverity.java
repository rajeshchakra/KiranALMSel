package selalmwrapper.enums;

public enum DefectSeverity {
	LOW("1-Low"), MEDIUM("2-Medium"), HIGH("3-High"), VERY_HIGH("4-Very High"), URGENT("5-Urgent");

	private String severity;

	private DefectSeverity(String severity) {
		setSeverity(severity);
	}

	public String getSeverity() {
		return this.severity;
	}

	private void setSeverity(String severity) {
		this.severity = severity;
	}
	
}