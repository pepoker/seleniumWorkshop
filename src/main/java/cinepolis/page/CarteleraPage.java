package cinepolis.page;

import base.page.BasePage;
import cinepolis.data.BillboardMovie;
import cinepolis.data.CineData;
import cinepolis.data.MovieFormat;
import cinepolis.enums.Language;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarteleraPage extends BasePage {
    @FindBy(id = "preloadCartelera")
    private WebElement loadingComponent;

    @FindBy(css = ".divComplejo")
    private List<WebElement> cinemas;

    @FindBy(id = "SUB")
    private WebElement subtitleLanguage;

    @FindBy(id = "ESP")
    private WebElement spanishLanguage;

    @FindBy(id = "ORI")
    private WebElement originalLanguage;

    public CarteleraPage(WebDriver driver) {
        super(driver);
    }

    public void waitLoadPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 15, 500);
        wait.until(ExpectedConditions.attributeContains(loadingComponent, "class", "ng-hide"));
    }

    public List<WebElement> getVisibleCinemas() {
        return cinemas.stream().filter(c -> c.isDisplayed()).collect(Collectors.toList());
    }

    public void checkBoxLanguage(Language language, boolean select) {
        WebElement languageElement;
        switch (language) {
            case ESPANOL:
                languageElement = spanishLanguage;
                break;
            case SUBTITULADA:
                languageElement = subtitleLanguage;
                break;
            case ORIGINAL:
                languageElement = originalLanguage;
            default:
                throw new NotImplementedException("Language not supported yet.");
        }

        checkBox(languageElement, select);
    }

    private void checkBox(WebElement webElement, boolean select) {
        String checkAttribute = webElement.getAttribute("checked");

        boolean isChecked = false;
        if (checkAttribute != null && checkAttribute.equalsIgnoreCase("true")) {
            isChecked = true;
        }

        if (isChecked && !select) {
            webElement.click();
        }

        if (!isChecked && select) {
            webElement.click();
        }
    }

    public List<CineData> getCartelera() {
        List<CineData> cineDatas = new ArrayList<>();
        List<WebElement> cinemas = getVisibleCinemas();
        for (WebElement cineElement : cinemas) {
            CineData cineData = new CineData();
            cineData.setCinemaName(getCineName(cineElement));
            cineData.setCity(getCiudadCine(cineElement));
            cineData.setBillboardMovies(getBillboardsForCinema(cineElement));


            cineDatas.add(cineData);
        }

        return cineDatas;
    }

    private String getCineName(WebElement cinema) {
        WebElement header = cinema.findElement(By.className("btnInfoComplejo"));
        String nombreComplejo = header.getAttribute("nombrecomplejo");
        return nombreComplejo;
    }

    private String getCiudadCine(WebElement cinema) {
        WebElement header = cinema.findElement(By.className("btnInfoComplejo"));
        String city = header.getAttribute("nombreciudad");
        return city;
    }

    private List<BillboardMovie> getBillboardsForCinema(WebElement cinemaElement) {
        List<BillboardMovie> billboardMovies = new ArrayList<>();

        List<WebElement> movieItems = cinemaElement.findElements(By.tagName("article")).stream().filter(a -> a.isDisplayed()).collect(Collectors.toList());

        for (WebElement movieItem : movieItems) {
            billboardMovies.add(getBillboardMovie(movieItem));
        }

        return billboardMovies;
    }

    private BillboardMovie getBillboardMovie(WebElement movieItem) {
        BillboardMovie movie = new BillboardMovie();
        movie.setClasification(getClasification(movieItem));
        movie.setMovieName(getMovieName(movieItem));
        movie.setMovieLength(getMovieLength(movieItem));
        movie.setMovieFormats(getMovieFormat(movieItem));


        return movie;
    }

    private String getClasification(WebElement movieItem) {
        return movieItem.findElement(By.cssSelector(".clasificacion")).getText();
    }

    private String getMovieName(WebElement movieItem) {
        WebElement peliculaInfo = movieItem.findElement(By.cssSelector("span"));
        return peliculaInfo.getAttribute("data-titulo");
    }

    private String getMovieLength(WebElement movieItem) {
        return movieItem.findElement(By.cssSelector(".duracion")).getText();
    }

    private List<MovieFormat> getMovieFormat(WebElement movieItem) {
        List<MovieFormat> movieFormats = new ArrayList<>();
        List<WebElement> formatElements = movieItem.findElements(By.cssSelector(".horarioExp > .row"));

        for (WebElement formatElement : formatElements) {
            movieFormats.add(getFormatoPelicula(formatElement));
        }

        return movieFormats;
    }

    private MovieFormat getFormatoPelicula(WebElement formatElement) {
        MovieFormat movieFormat = new MovieFormat();
        movieFormat.setLanguage(getMovieLanguage(formatElement));
        movieFormat.setSchedules(getMovieSchedule(formatElement));

        return movieFormat;
    }

    private String getMovieLanguage(WebElement formatElement) {
        return formatElement.findElement(By.tagName("p")).getText();
    }

    private List<String> getMovieSchedule(WebElement formatoElement) {
        List<WebElement> scheduleElements = formatoElement.findElements(By.cssSelector(".btnhorario"));
        return scheduleElements.stream().map(h -> h.getText()).collect(Collectors.toList());
    }
}
