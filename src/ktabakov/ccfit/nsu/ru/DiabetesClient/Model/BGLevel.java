package ktabakov.ccfit.nsu.ru.DiabetesClient.Model;

/**
 * Created by Константин on 30.03.2015.
 */
public class BGLevel {

    private String timeMeasurement;
    private int valueMeasurement;

    public BGLevel(String timeMeasurement, int valueMeasurement) {
        this.timeMeasurement = timeMeasurement;
        this.valueMeasurement = valueMeasurement;

    }

    public String getTimeMeasurement() {
        return timeMeasurement;
    }

    public int getValueMeasurement() {
        return valueMeasurement;
    }
}
