package tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.RetryAnalyzer;
import utilities.TestListener;

@Listeners(TestListener.class)
public class GoogleSearch extends BaseTest {
	
	private static final Logger logger = LogManager.getLogger(GoogleSearch.class);
	
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void googleSearch() {
    	
    	logger.info("Starting the testcase  and browser launched successfully");
        driver.get("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("Selenium TestNG", Keys.ENTER);
       
        boolean resultDisplayed = driver.findElement(By.id("search")).isDisplayed();
        Assert.assertTrue(resultDisplayed, "Search results not displayed");
        logger.info("Test case  successfully");
    }
}
