import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormPage {
    private WebDriver driver;

    public FormPage(WebDriver driver){
        this.driver = driver;
    }

    public void fillFormCustomer() {
        WebElement saveButton = driver.findElement(By.id("form-button-save"));
        Assertions.assertTrue(saveButton.isDisplayed());

        driver.findElement(By.id("field-customerName")).sendKeys("Teste Sicredi");
        driver.findElement(By.id("field-contactLastName")).sendKeys("Teste");
        driver.findElement(By.id("field-contactFirstName")).sendKeys("Daniel Arruda");
        driver.findElement(By.id("field-phone")).sendKeys("51 9999-9999");
        driver.findElement(By.id("field-addressLine1")).sendKeys("Av Assis Brasil, 3970");
        driver.findElement(By.id("field-addressLine2")).sendKeys("Torre D");
        driver.findElement(By.id("field-city")).sendKeys("Porto Alegre");
        driver.findElement(By.id("field-state")).sendKeys("RS");
        driver.findElement(By.id("field-postalCode")).sendKeys("91000-000");
        driver.findElement(By.id("field-country")).sendKeys("Brasil");
        driver.findElement(By.id("field-salesRepEmployeeNumber")).sendKeys("Fixter");
        driver.findElement(By.id("field-creditLimit")).sendKeys("200");

        saveButton.click();
    }

    public boolean hasStoredData() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='report-success']//p")));
        WebElement reportSuccess = driver.findElement(By.xpath("//div[@id='report-success']//p"));
        return "Your data has been successfully stored into the database. Edit Record or Go back to list".equals(reportSuccess.getText());
    }

    public void goBackToList() {
        driver.findElement(By.xpath("//a[contains(text(), 'Go back to list')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//INPUT[@type='text'])[1]")));
    }
}
