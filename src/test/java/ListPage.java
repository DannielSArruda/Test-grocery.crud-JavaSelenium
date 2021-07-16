import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListPage{

    public static final String URL = "https://www.grocerycrud.com/v1.x/demo/my_boss_is_in_a_hurry/bootstrap";
    private WebDriver driver;

    public ListPage(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    public void accessListPage(){
        driver.navigate().to(URL);
    }

    public void changeTheme(String theme) {
        Select objSelect = new Select(driver.findElement(By.id("switch-version-select")));
        objSelect.selectByVisibleText(theme);
    }

    public FormPage addCustomer() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='el el-plus']")));
        driver.findElement(By.xpath("//i[@class='el el-plus']")).click();
        return new FormPage(driver);
    }

    public String searchCustomer(String customerName) {
        driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys(customerName);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), '"+ customerName +"')]")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//SPAN[@class='paging-ends']")));
        WebElement itens = driver.findElement(By.xpath("//SPAN[@class='paging-ends']"));
        String itensNumber = itens.getText();
        return itensNumber;
    }

    public void deleteCustomer(String itensNumber){
        driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@title='Delete'])[1]")));
        driver.findElement(By.xpath("(//a[@title='Delete'])[1]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//P[@class='alert-delete-multiple']")));
        WebElement element = driver.findElement(By.xpath("//P[@class='alert-delete-multiple']"));
        Assertions.assertEquals("Are you sure that you want to delete those "+ itensNumber + " items?", element.getText());

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//BUTTON[@class='btn btn-danger delete-multiple-confirmation-button']")));
        driver.findElement(By.xpath("//BUTTON[@class='btn btn-danger delete-multiple-confirmation-button']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//P[text()='Your data has been successfully deleted from the database.']")));
        WebElement element2 = driver.findElement(By.xpath("//P[text()='Your data has been successfully deleted from the database.']"));
        Assertions.assertEquals("Your data has been successfully deleted from the database.", element2.getText());
    }

    public void closeBrowser() { this.driver.quit(); }
}
