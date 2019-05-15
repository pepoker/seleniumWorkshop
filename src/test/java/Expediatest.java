import base.enums.Browsers;
import base.test.BaseTest;
import expedia.page.ExpediaHome;
import org.testng.annotations.Test;

public class Expediatest extends BaseTest {
   public  Expediatest(){
       super(Browsers.CHROME);
   }

    @Test
    public void testExpedia(){
       driver.get("https://www.expedia.mx/");

        ExpediaHome expediaHome = new ExpediaHome(driver);
        expediaHome.setOrigen("Chihuahua, Chihuahua, México (CUU-A. Internacional General Roberto Fierro Villalobos)");
        expediaHome.setDestino("Cancún, Quintana Roo, México (CUN-Aeropuerto Internacional de Cancún)");
        expediaHome.setSalida("24/05/2019");
        expediaHome.setRegreso("26/05/2019");
        expediaHome.clickBuscar();
    }
}
