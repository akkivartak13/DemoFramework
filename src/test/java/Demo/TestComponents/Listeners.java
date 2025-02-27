package Jiochat.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Jiochat.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener
{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal <ExtentTest> extentTest = new ThreadLocal <ExtentTest>();
	
		@Override
		public void onTestStart(ITestResult result) 
		{
			test= extent.createTest(result.getMethod().getMethodName());
		
			extentTest.set(test);
			
			
		 
		 }
		 
		@Override
		 public void onTestSuccess(ITestResult result) {
		 
			extentTest.get().log(Status.PASS, "test Pass");
		 
		 }
		 @Override
		 public void onTestFailure(ITestResult result) 
		 {
			 String filePath = null;
			 extentTest.get().fail(result.getThrowable());
			 
			 try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 try {
				filePath = getScreenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
			 
			 		 
		 }
		 
	
	

		public void onTestSkipped(ITestResult result) {
		 

		 
		 }
		 
		 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		 
		 
		 
		 }
		 
		 public void onStart(ITestContext context) {
		 
		 
		 
		 }
		 
		 public void onFinish(ITestContext context) {
			 
			 extent.flush();
		
		 }
		

}
