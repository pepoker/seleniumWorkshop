import base.enums.Browsers;
import base.test.BaseTest;
import cinepolis.data.CineData;
import cinepolis.enums.Language;
import cinepolis.page.CarteleraPage;
import cinepolis.page.CinepolisHomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CinepolisTest extends BaseTest {

    public CinepolisTest() {
        super(Browsers.CHROME);
    }

    @Test
    public void navigateToCinepolis() {
        driver.get("https://www.cinepolis.com/#");
        driver.findElement(By.cssSelector(".btnVerCartelera"));

        CinepolisHomePage cinepolisHomePage = new CinepolisHomePage(driver);
        cinepolisHomePage.verCartelera("Chihuahua", "Cinépolis Paseo Central");

        CarteleraPage carteleraPage = new CarteleraPage(driver);
        carteleraPage.waitLoadPage();
        List<CineData> billboard = carteleraPage.getCartelera();
        CineData cinePaseoCentral = billboard.get(0);

        Assert.assertTrue(cinePaseoCentral.getBillboardMovies().size() > 0);

        carteleraPage.checkBoxLanguage(Language.ESPANOL, false);

        CineData cinePaseoCentralSinEspanol = carteleraPage.getCartelera().get(0);
        Assert.assertTrue(cinePaseoCentral.getBillboardMovies().size() > cinePaseoCentralSinEspanol.getBillboardMovies().size());

    }
}
