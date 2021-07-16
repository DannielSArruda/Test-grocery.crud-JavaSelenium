import org.junit.jupiter.api.*;

public class DesafioAutomacaoWeb {
    private ListPage listPage;
    private FormPage formPage;

    @BeforeEach
    public void beforeEach(){
        this.listPage = new ListPage();
    }

    @AfterEach
    public void afterEach(){
        this.listPage.closeBrowser();
    }

    @Test
    public void desafio1(){

        listPage.accessListPage();
        listPage.changeTheme("Bootstrap V4 Theme");

        this.formPage = listPage.addCustomer();
        formPage.fillFormCustomer();

        Assertions.assertTrue(formPage.hasStoredData());
    }

    @Test
    public void desafio2(){
        desafio1();
        formPage.hasStoredData();
        formPage.goBackToList();
        listPage.deleteCustomer(listPage.searchCustomer("Teste Sicredi"));
    }
}
