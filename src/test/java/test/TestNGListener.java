package test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

	public void onFinish(ITestContext result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("*****Test Failed : "+result.getName());
		// TODO Auto-generated method stub
		
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("*****Test Skipped : "+result.getName());
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult result) {
		
		System.out.println("*****Test Started : "+result.getName());
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

}
