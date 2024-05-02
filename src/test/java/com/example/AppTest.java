package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;
    @BeforeMethod 
    public void setup()throws Exception
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        report=new ExtentReports();
        ExtentSparkReporter spark=new ExtentSparkReporter("C:\\Users\\subas\\Documents\\st_modellab_an\\src");
        report.attachReporter(spark);
        driver.get("https://www.moneycontrol.com/");
        Thread.sleep(10000);
    }
    @Test
    public void shouldAnswerWithTrue() throws Exception
    {
        test=report.createTest("Cretaed testcase");
        WebElement e1=driver.findElement(By.id("search_str"));
        e1.click();
        e1.sendKeys("Reliance Industries");
        e1.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id='mc_mainWrapper']/div[3]/div[2]/div/table/tbody/tr[4]/td[1]/p/a/strong")).click();
        Thread.sleep(2000);
        WebElement e2=driver.findElement(By.xpath("//*[@id='stockName']/h1"));
        String s1=e2.getText();
        if(s1.contains("Reliance"))
        {
            System.out.println("Keyword present");
        }
        else{
            System.out.println("Keyword not present");
        }
        Actions action=new Actions(driver);
        action.moveToElement( driver.findElement(By.linkText("Mutual Funds"))).perform();;
        Thread.sleep(2000);
        driver.findElement(By.linkText("SIP Return")).click();
        Thread.sleep(5000);
        Select dp1=new Select(driver.findElement(By.id("ff_id")));
        dp1.selectByIndex(2);
        Select dp2=new Select(driver.findElement(By.id("im_id")));
        dp2.selectByIndex(3);
        driver.findElement(By.xpath("//*[@id='invamt']")).sendKeys("100000");
        Select dp3=new Select(driver.findElement(By.id("frq")));
        dp3.selectByIndex(0);
        driver.findElement(By.id("stdt")).sendKeys("2021-08-02");
        driver.findElement(By.id("endt")).sendKeys("2023-08-17");
        driver.findElement(By.xpath("//*[@id='mc_mainWrapper']/div[2]/div/div[3]/div[2]/div[2]/form/div[8]/input")).click();
        Thread.sleep(5000);
        WebElement ele=driver.findElement(By.xpath("//*[@id='mc_mainWrapper']/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[1]"));
        String period=ele.getText();
        WebElement el=driver.findElement(By.xpath("//*[@id=\"mc_mainWrapper\"]/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[3]"));
        String amount=el.getText();
        System.out.println(period);
        System.out.println(amount);


    }
    @AfterMethod
    public void closeMethod(){
        report.flush();
        driver.quit();
    }
}
