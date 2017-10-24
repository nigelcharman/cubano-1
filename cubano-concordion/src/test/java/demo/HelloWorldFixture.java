package demo;

import java.util.List;

import org.concordion.cubano.driver.web.Browser;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(ConcordionRunner.class)
public class HelloWorldFixture {

    // @Extension
    // StoryboardExtension storyboard = new StoryboardExtension();

    // @Extension
    // LoggingFormatterExtension logging = new LoggingFormatterExtension().registerListener(new StoryboardLogListener(storyboard));

    // ReportLogger logger = ReportLoggerFactory.getReportLogger(HelloWorldFixture.class);
    Logger logger = LoggerFactory.getLogger(HelloWorldFixture.class);

    public String getGreetingFailure() throws InterruptedException {
        Browser browser = new Browser();
        // browser.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), 5);

        try {
            for (int i = 0; i < 20; i++) {
                browser.getDriver().navigate().to("http://google.co.nz");
                logger.info("GOTO");

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=q]")));

                browser.getDriver().findElement(By.cssSelector("input[name=q]")).sendKeys("concordion");
                logger.info("TYPE");

                browser.getDriver().findElement(By.cssSelector("input[name=q]")).sendKeys(Keys.ENTER);

                // browser.getDriver().findElement(By.cssSelector("input[name=btnK]")).click();
                logger.info("CLICK");

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("foot")));
                logger.info("WAIT");

                boolean exception = false;

                do {
                    try {
                        List<WebElement> results = browser.getDriver().findElements(By.cssSelector("h3[class=r] > a"));

                        for (WebElement result : results) {
                            if (result.getAttribute("href").equals("http://concordion.org/")) {
                                result.click();
                                logger.info("NAVIGATE");

                                break;
                                // Thread.sleep(1000);
                            }
                        }

                        exception = false;

                    } catch (StaleElementReferenceException e) {
                        exception = true;
                    }
                } while (exception);

                wait.until(ExpectedConditions.titleContains("Concordion"));
            }
        } finally {
            browser.close();
        }

        // logger.with()
        // .message("Hello World!")
        // .attachment("This is some data", "data.txt", MediaType.PLAIN_TEXT)
        // .marker(new StoryboardMarker("Hello", "Data", StockCardImage.TEXT, CardResult.SUCCESS))
        // .debug();

        // return "Failed " + attempts;
        return "Hello World!";
    }
}