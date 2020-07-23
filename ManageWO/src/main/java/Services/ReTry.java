package Services;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ReTry implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int retryLimit = 1;
    public boolean retry(ITestResult result)
    {
        if(retryCount < retryLimit)
        {
            retryCount++;
            return true;
        }

        return false;
    }

}
