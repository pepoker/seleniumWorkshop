package expedia.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ExpediaHome {
    private WebDriver driver;

    @FindBy(id = "package-origin-hp-package")
    private WebElement origen;

    @FindBy(id = "package-destination-hp-package")
    private WebElement destino;

    @FindBy(id = "package-departing-hp-package")
    private WebElement salida;

    @FindBy(id = "package-returning-hp-package")
    private WebElement regreso;

    @FindBy( id = "search-button-hp-package")
    private WebElement botonBuscar;

    public ExpediaHome(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setOrigen(String origen){
        this.origen.sendKeys(origen);
        this.origen.sendKeys(Keys.ENTER);
    }

    public void setDestino(String des){
        this.destino.sendKeys(des);
    }

    public void setSalida(String fecha){
        salida.sendKeys(fecha);
    }

    public void setRegreso(String fecha){
        regreso.sendKeys(fecha);
    }

    public void clickBuscar(){
        botonBuscar.click();
    }
}
