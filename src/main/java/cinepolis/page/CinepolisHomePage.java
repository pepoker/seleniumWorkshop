package cinepolis.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CinepolisHomePage {
    @FindBy(css = ".btnVerCartelera")
    private WebElement botonVerCartelera;

    @FindBy(id = "cmbCiudades")
    private WebElement cmbCiudades;

    @FindBy(id = "cmbComplejos")
    private WebElement cmbCines;

    public CinepolisHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickBotonVerCartelera() {
        botonVerCartelera.click();
    }

    public void verCartelera(String ciudad, String cine) {
        seleccionarCiudad(ciudad);
        seleccionarCine(cine);
    }

    public void verCartelera(String ciudad) {
        seleccionarCiudad(ciudad);
        clickBotonVerCartelera();
    }

    private void seleccionarCiudad(String ciudad) {
        Select select = new Select(cmbCiudades);
        select.selectByVisibleText(ciudad);
    }

    private void seleccionarCine(String cine) {
        if (!"".equals(cine)) {
            Select select = new Select(cmbCines);
            select.selectByVisibleText(cine);
        }
    }
}
