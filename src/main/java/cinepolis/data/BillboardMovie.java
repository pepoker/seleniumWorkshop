package cinepolis.data;

import java.util.List;

public class BillboardMovie {
    private String movieName;
    private String clasification;
    private String movieLength;
    private List<MovieFormat> movieFormats;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getClasification() {
        return clasification;
    }

    public void setClasification(String clasification) {
        this.clasification = clasification;
    }

    public String getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(String movieLength) {
        this.movieLength = movieLength;
    }

    public List<MovieFormat> getMovieFormats() {
        return movieFormats;
    }

    public void setMovieFormats(List<MovieFormat> movieFormats) {
        this.movieFormats = movieFormats;
    }
}
