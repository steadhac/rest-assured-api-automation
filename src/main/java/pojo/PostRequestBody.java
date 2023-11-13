package pojo;

import java.util.List;

public class PostRequestBody {
    private String job;
    private String name;
    private List<String> languages;
    private List<CityRequest> cityRequestBody;

    public List<CityRequest> getCityRequestBody() {
        return cityRequestBody;
    }

    public void setCityRequestBody(List<CityRequest> cityRequestBody) {
        this.cityRequestBody = cityRequestBody;
    }

    public String getJob() {
        return job;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
