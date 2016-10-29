package zhuruyi.net.wechardemo.model;

/**
 * Created by ruyi on 2016/10/14.
 * 配合Weather给Gson做例子时使用
 */

public class WeatherInfo {
    private String city;
    private String temp;
    private String time;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
