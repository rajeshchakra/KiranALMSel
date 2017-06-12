package selalmwrapper;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.ComThread;

import selalmwrapper.bean.ServerDetails;
import selalmwrapper.classes.Attachment;
import selalmwrapper.classes.AttachmentStorage;
import selalmwrapper.classes.Run;
import selalmwrapper.classes.RunFactory;
import selalmwrapper.classes.Step;
import selalmwrapper.classes.StepFactory;
import selalmwrapper.classes.TDConnection;
import selalmwrapper.classes.TSTest;
import selalmwrapper.classes.TSTestFactory;
import selalmwrapper.classes.TestSet;
import selalmwrapper.classes.TestSetFolder;
import selalmwrapper.classes.TestSetTreeManager;
import selalmwrapper.collection.ListWrapper;
import selalmwrapper.enums.DefectPriority;
import selalmwrapper.enums.DefectSeverity;
import selalmwrapper.enums.DefectStatus;
import selalmwrapper.enums.StatusAs;
import selalmwrapper.exceptions.ALMServiceException;

public class ALMServiceWrapper {
	private ServerDetails serverDetails = null;
	private TDConnection almObj = null;

	private String testSetFolder;

	private String testSetName;

	public ALMServiceWrapper(String url) {
		this.serverDetails = new ServerDetails();
		this.serverDetails.setUrl(url);
	}

	public boolean connect(String username, String password, String domain, String project) throws ALMServiceException {
		this.serverDetails.setUsername(username);
		this.serverDetails.setPassword(password);
		this.serverDetails.setDomain(domain);
		this.serverDetails.setProject(project);
		connectToOTA();
		return true;
	}

	private void releaseConnection() {
		try {
			if (getAlmObj().isConnected()) {
				getAlmObj().disconnect();
			}
			if (getAlmObj().isLoggedIn()) {
				getAlmObj().logout();
			}
			getAlmObj().releaseConnection();
		} catch (Exception e) {
		}
	}

	private void connectToOTA() throws ALMServiceException {
		try {
		
			 ActiveXComponent activexObject = new ActiveXComponent("TDApiOle.TDConnection");
			 setAlmObj(new TDConnection(activexObject, getServerDetails()));
			releaseConnection();
			getAlmObj().initConnectionEx(getServerDetails().getUrl());
			getAlmObj().login(getServerDetails().getUsername(), getServerDetails().getPassword());
			getAlmObj().connect(getServerDetails().getDomain(), getServerDetails().getProject());
		} catch (UnsatisfiedLinkError e) {
			throw new ALMServiceException("Please add the jacob-(version-bit-type).dll file to the System path");
		} catch (ComFailException e) {
			throw new ALMServiceException("Please Register the OTAClient.dll with your system");
		}
	}

	public ITestCase updateResult(String testSetFolderPath, String testSetName, int testSetID, String tcName,
			StatusAs as) throws ALMServiceException {
		TestSetTreeManager testSetTreeManager = getAlmObj().getTestSetTreeManager();
		TestSetFolder testSetFolder = testSetTreeManager.getNodeByPath(testSetFolderPath);
		TestSet testSet = testSetFolder.findTestSet(testSetName, testSetID);
		TSTestFactory tsTestFactory = testSet.getTSTestFactory();
		ListWrapper<TSTest>   listWrapper = tsTestFactory.getNewList();
		for (TSTest tsTest : listWrapper) {
					if (tcName.equals(tsTest.getTestName())) {
				tsTest.putStatus(as);
				tsTest.post();
				return tsTest;
			}
		}
		throw new ALMServiceException("The Given Test Name \"" + tcName + "\" Not Found");
	}

	public ITestSet getTestSet(String testSetFolderPath, String testSetName, int testSetID) throws ALMServiceException {
		TestSetTreeManager testSetTreeManager = getAlmObj().getTestSetTreeManager();

		TestSetFolder testSetFolder = testSetTreeManager.getNodeByPath(testSetFolderPath);

		TestSet testSet = testSetFolder.findTestSet(testSetName, testSetID);
		return testSet;
	}

	public ITestCaseRun createNewRun(ITestCase tsTest, String runName, StatusAs as) {
		RunFactory runFactory = tsTest.getRunFactory();
		Run run = runFactory.addItem();
		run.setStatus(as);
		run.setName(runName);
		run.post();
		return run;
	}

	public void addStep(ITestCaseRun run, String stepName, StatusAs as, String description, String expected,
			String actual) {
		StepFactory stepFactory = run.getStepFactory();
		Step step = stepFactory.addItem();
		step.setStepName(stepName);
		step.setStatus(as);
		step.setDescription(description);
		step.setExpected(expected);
		step.setActual(actual);
		step.post();
	}

	public void newAttachment(String attachment, String description, HasAttachmentFeature hasAttachment)
			throws ALMServiceException {
		File file = new File(attachment);
		if (file.exists()) {
			Attachment attachmentFile = hasAttachment.getAttachments().addItem(file.getName());

			attachmentFile.setDescription(description);
			attachmentFile.post();
			AttachmentStorage as = attachmentFile.getAttachmentStorage();
			as.clientPath(file.getParent());
			as.save(file.getName());
		} else {
			throw new ALMServiceException("The Specified Attachment file does not exist");
		}
	}

	public IDefect newDefect() {
		IDefect defect = getAlmObj().getBugFactory().addItem();
		return defect;
	}

	public int newDefect(String detectedBy, String assignedTo, DefectPriority priority, DefectSeverity severity,
			DefectStatus status, String summary, String detectedDate, String description, boolean isReproducible,
			String project, String attachment) throws ALMServiceException {
		IDefect bug = getAlmObj().getBugFactory().addItem();
		if ((detectedBy != null) && (detectedBy.length() > 0  )) {
			bug.setDetectedBy(detectedBy);
		}
		if ((assignedTo != null) && (assignedTo.length() > 0  )) {
			bug.setAssignedTo(assignedTo);
		}
		if (priority != null) {
			bug.setPriority(priority);
		}
		if (severity != null) {
			bug.setSeverity(severity);
		}
		if (status != null) {
			bug.setStatus(status);
		}
		if ((summary != null) && (summary.length() > 0  )) {
			bug.setSummary(summary);
		}
		if ((detectedDate != null) && (detectedDate.length() > 0  )) {
			bug.setDetectionDate(detectedDate);
		}

		if ((description != null) && (description.length() > 0  )) {
			bug.setDescription(description);
		}
		bug.isReproducible(isReproducible);
		if ((project != null) && (project.length() > 0  )) {
			bug.setProject(project);
		}

		File file = new File(attachment);
		if (file.exists()) {
			Attachment attachmentFile = bug.getAttachments().addItem(file.getName());

			attachmentFile.setDescription("Sample Attchment Desc");
			attachmentFile.post();
			AttachmentStorage as = attachmentFile.getAttachmentStorage();
			as.clientPath(file.getParent());
			as.save(file.getName());
		} else {
			throw new ALMServiceException("The Specified Attachment file does not exist");
		}

		bug.save();
		return bug.getDefectID();
	}

	public void close() {
		try {
			releaseConnection();
			return;
		} catch (Exception e) {
		} finally {
			try {
				ComThread.Release();
			} catch (Exception e) {
			}
		}
	}

	public ServerDetails getServerDetails() {
		return this.serverDetails;
	}

	public TDConnection getAlmObj() {
		return this.almObj;
	}

	public void setAlmObj(TDConnection almObj) {
		this.almObj = almObj;
	}

	public String getTestSetFolder() {
		return this.testSetFolder;
	}

	public void setTestSetFolder(String testSetFolder) {
		this.testSetFolder = testSetFolder;
	}

	public String getTestSetName() {
		return this.testSetName;
	}

	public void setTestSetName(String testSetName) {
		this.testSetName = testSetName;
	}
}