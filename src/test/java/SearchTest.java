import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {
    private Setup setup = new Setup();
    public WebDriver driver;
    @BeforeMethod
    public void setupTest(){
        driver = setup.SetupDriver();
        driver.navigate().to(setup.testURL);
    }


    @Test(priority = 1)
    public void EmptySearch() throws InterruptedException {
// driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"searchform\"]/button"));
        searchButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), setup.testURL);
    }
    @Test(priority = 2)
    public void RandomTextSearch() throws InterruptedException {
// driver.manage().window().maximize();
        WebElement searchTextBox = driver.findElement(By.id("artikl"));
        Thread.sleep(5000);
        searchTextBox.sendKeys("asdfghjk12345");
        Thread.sleep(5000);
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"searchform\"]/button"));
        searchButton.click();
        WebElement noResultTextBox = driver.findElement(By.xpath("//*[@id=\"product-list\"]/h2"));
        Assert.assertEquals(noResultTextBox.getText(), "NEMA NITI JEDAN ARTIKL ZA PRIKAZ");
        System.out.print(noResultTextBox.getText());
    }
    @Test(priority = 3)
    public void ItemSearch() throws InterruptedException {
// driver.manage().window().maximize();
        WebElement searchTextBox = driver.findElement(By.id("artikl"));
        Thread.sleep(5000);
        searchTextBox.sendKeys("ryzen 5 5600x");
        Thread.sleep(5000);
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"searchform\"]/button"));
        searchButton.click();
        WebElement firstResultTextBox = driver.findElement(By.xpath("//*[@id=\"item_136923\"]/div/div[3]/h2/a/span"));
        Assert.assertEquals(firstResultTextBox.getText(), "CPU AMD Ryzen 5 5600X 100-100000065BOX");
        System.out.print(firstResultTextBox.getText());
    }
    //---------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}
