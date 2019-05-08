package com.qa.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListener extends TestBase implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String destPath = null;
		//destPath = TestUtil.takeScreenshotAtEndOfTest();
		destPath = TestUtil.addScreenshot();
		extentTest = extent.startTest(result.getMethod().getMethodName());
		extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName());
		extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable());
		extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(destPath));
		extent.endTest(extentTest);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
