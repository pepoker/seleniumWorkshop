package cinepolis.data;

import java.util.List;

public class MovieFormat {
    private String language;
    private List<String> schedules;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<String> schedules) {
        this.schedules = schedules;
    }

}
