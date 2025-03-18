package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.RetryAnalyzer;
import utilities.TestListener;

@Listeners(TestListener.class)
public class GoogleSearch extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void googleSearch() {
        driver.get("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("Selenium TestNG", Keys.ENTER);

        boolean resultDisplayed = driver.findElement(By.id("search")).isDisplayed();
        Assert.assertTrue(resultDisplayed, "Search results not displayed");
    }
}
