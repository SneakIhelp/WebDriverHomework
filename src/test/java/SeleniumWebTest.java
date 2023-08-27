import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumWebTest {

    WebDriver driver;

    private String login = "lareeru@mail.ru";
    private String pas = "Aa!12345678";

    @AfterEach
    public void close(){
        if (driver != null)
            driver.quit();
    }

    @Test
    public void firstTest(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.get("https://duckduckgo.com/");

        driver.findElement(By.name("q")).sendKeys("ОТУС");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("/html/body/div[2]/div[5]/div[4]/div/div/section[1]/ol/li[1]/article/div[2]/h2/a/span"), "Онлайн‑курсы для профессионалов, дистанционное обучение современным ..."));

        String firstResult = driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[4]/div/div/section[1]/ol/li[1]/article/div[2]/h2/a/span")).getText();
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...", firstResult);
    }

    @Test
    public void secondTest(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("kiosk");
        driver = new ChromeDriver(options);
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");

        WebElement imageElement = driver.findElement(By.cssSelector("img.img-fluid"));

        WebElement overlayElement = driver.findElement(By.className("content-overlay"));

        Actions actions = new Actions(driver);

        actions.moveToElement(overlayElement).click(imageElement).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pp_hoverContainer")));

        Assertions.assertTrue(driver.findElement(By.cssSelector(".pp_hoverContainer")).isDisplayed());
    }

    @Test
    public void thirdTest(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        driver = new ChromeDriver(options);
        driver.get("https://otus.ru");
        loginInOtus();
        System.out.println(driver.manage().getCookies());
    }

    private void loginInOtus(){
        driver.findElement(By.cssSelector(".sc-mrx253-0")).click();
        clearAndEnter(By.cssSelector("div.hGvqzc:nth-child(1) > div:nth-child(1) > input:nth-child(2)"), login);
        clearAndEnter(By.cssSelector(".sc-177u1yy-0 > input:nth-child(2)"), pas);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/div[2]/div/div[2]/div[1]/div/button")).click();
    }

    private void clearAndEnter(By by, String text){
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }
}
