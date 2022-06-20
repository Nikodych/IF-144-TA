package com.softserveinc.ita.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int counter = 0;
    private final int MAX_RETRY_LIMIT = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (counter < MAX_RETRY_LIMIT) {
            counter++;
            return true;
        }
        return false;
    }
}