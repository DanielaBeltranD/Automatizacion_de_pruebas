import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AdjuntarArchivo {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                .concat("\\src\\test\\resources\\drivers\\chromedriver.exe"));

        //Precondicion, ingreso a la pagina web

        driver = new ChromeDriver();
        driver.get("http://webdriveruniversity.com/File-Upload/index.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {

        // Postcondiciones, dejar la aplicacion en el estado original, acciones de limpieza post prueba

        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.quit();
    }

    @Test
    public void adjuntarArchivo() {

        WebElement botonAdjuntarArchivo = driver.findElement(By.id("myFile"));
        botonAdjuntarArchivo.sendKeys("C:\\Users\\dbetrand\\Documents\\Tester QA\\Informe resultados pruebas.docx");

        WebElement enviarArchivo = driver.findElement(By.id("submit-button"));
        enviarArchivo.click();

        Alert alert = driver.switchTo().alert();

        MatcherAssert.assertThat("Los mensajes deben ser iguales", alert.getText(),Matchers.equalTo("Your file has now been uploaded!"));

    }

}

