package cinepolis.data;

import java.util.ArrayList;
import java.util.List;

public class CineData {
    private String city;
    private String cinemaName;
    private List<BillboardMovie> billboardMovies;

    public CineData(){
        billboardMovies = new ArrayList<>();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cine) {
        this.cinemaName = cine;
    }

    public List<BillboardMovie> getBillboardMovies() {
        return billboardMovies;
    }

    public void setBillboardMovies(List<BillboardMovie> billboardMovies) {
        this.billboardMovies = billboardMovies;
    }
}
