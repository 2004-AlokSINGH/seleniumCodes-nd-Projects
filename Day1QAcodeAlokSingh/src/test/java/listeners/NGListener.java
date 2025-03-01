package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class NGListener implements ITestListener {
	


    // This method will be called when a test starts
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName()+ result.getStatus());
    }

    // This method will be called when a test passes
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    // This method will be called when a test fails
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        System.out.println("Error: " + result.getThrowable());
    }

    // This method will be called when a test is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    // This method will be called when the test suite starts
    @Override
    public void onStart(org.testng.ITestContext context) {
        System.out.println("Test suite started: " + context.getName());
    }

    // This method will be called when the test suite finishes
    @Override
    public void onFinish(org.testng.ITestContext context) {
        System.out.println("Test suite finished: " + context.getName());
    }

   
}
