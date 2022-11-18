package unitTests;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Sort {
    private static ChromeDriver driver;
    WebElement p = null;

    void beforeSortAmount() {

    }

    @BeforeClass
    public static void openBrowser() {
        System.setProperty( "webdriver.chrome.driver", "C:\\chromedriver.exe" );
        driver = new ChromeDriver();
        String baseUrl = "https://sakshingp.github.io/assignment/login.html";
        driver.get( baseUrl );
    }

    @Test
    public void MainForm() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        CountDownLatch waiter = new CountDownLatch(1);
//        waiter.await(3000, TimeUnit.MILLISECONDS); // wait for all elements to load

        driver.manage().window().maximize(); // command to maximize the window
        driver.findElement( By.id( "username" ) ).sendKeys( "Lakshay");
        driver.manage().timeouts().setScriptTimeout( 100, TimeUnit.SECONDS);
        driver.findElement( By.id( "password" ) ).sendKeys( "Wingify");
        driver.manage().timeouts().setScriptTimeout( 100, TimeUnit.SECONDS);
        System.out.println( "Pressing Login button" );
        driver.findElement( By.id( "log-in" ) ).click();
        CountDownLatch waiter = new CountDownLatch(1);
        waiter.await( 3000, TimeUnit.MILLISECONDS );
        driver.manage().timeouts().setScriptTimeout( 2000, TimeUnit.SECONDS);
        js.executeScript( "window.scrollBy(0,120)" ); // scrolling down to confirm message sent
        System.out.println( "Going to list all values" );
        List<WebElement> beforeSortedAmount = driver.findElementsByXPath("//*[@id=\"transactionsTable\"]/tbody/tr/td");
        String[] beforeSortAmountList = new String[beforeSortedAmount.size()];
        for (int i = 0; i < beforeSortedAmount.size(); i++) {
        beforeSortAmountList[i] = (beforeSortedAmount.get( i ).getText().trim().replace( "USD", ""));
        }
        System.out.println("Before Sort Amount");
        Print(beforeSortAmountList);
        Arrays.sort( beforeSortAmountList );
        System.out.println("After Sort");
        Print( beforeSortAmountList );
        System.out.println("Now clicking Amount button to sort");

        driver.findElement( By.id( "amount" ) ).click();

        driver.manage().timeouts().setScriptTimeout( 30, TimeUnit.SECONDS);
        Print( beforeSortAmountList );


    }

    private void Print(String[] beforeSortAmountList) {
        for(int i = 0;i<beforeSortAmountList.length;i++){
         System.out.println(beforeSortAmountList[i]);
        }
    }
}

//        Collections.sort( beforeSortedAmountList );
//        Assert.assertEquals( "beforeSortedAmountList" , "afterSortedAmountList");

//    @AfterClass
//    public static void closeBrowser(){
//        driver.quit(); // closing browser
//    }

