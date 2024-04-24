package sit707_week5;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherControllerTest {

	private static WeatherController wController;
    private static int nHours;
    private static double[] hourlyTemperatures;

    @BeforeClass
    public static void setUp() {
        wController = WeatherController.getInstance();
        nHours = wController.getTotalHours();
        hourlyTemperatures = new double[nHours];
        for (int i = 0; i < nHours; i++) {
            hourlyTemperatures[i] = wController.getTemperatureForHour(i + 1);
        }
    }

    @AfterClass
    public static void tearDown() {
        wController.close();
    }

    @Test
    public void testStudentIdentity() {
        // Arrange
        String studentId = "223558537";

        // Act & Assert
        Assert.assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        // Arrange
        String studentName = "Niwanthi";

        // Act & Assert
        Assert.assertNotNull("Student name is null", studentName);
    }

    @Test
    public void testTemperatureMin() {
        // Arrange
        System.out.println("+++ testTemperatureMin +++");
        double minTemperature = Double.MAX_VALUE;

        // Act
        for (double temperatureVal : hourlyTemperatures) {
            if (minTemperature > temperatureVal) {
                minTemperature = temperatureVal;
            }
        }

        // Assert
        Assert.assertEquals(minTemperature, wController.getTemperatureMinFromCache(), 0.01);
    }

    @Test
    public void testTemperatureMax() {
        // Arrange
        System.out.println("+++ testTemperatureMax +++");
        double maxTemperature = Double.MIN_VALUE;

        // Act
        for (double temperatureVal : hourlyTemperatures) {
            if (maxTemperature < temperatureVal) {
                maxTemperature = temperatureVal;
            }
        }

        // Assert
        Assert.assertEquals(maxTemperature, wController.getTemperatureMaxFromCache(), 0.01);
    }

    @Test
    public void testTemperatureAverage() {
        // Arrange
        System.out.println("+++ testTemperatureAverage +++");
        double sumTemp = 0;

        // Act
        for (double temperatureVal : hourlyTemperatures) {
            sumTemp += temperatureVal;
        }
        double averageTemp = sumTemp / nHours;

        // Assert
        Assert.assertEquals(averageTemp, wController.getTemperatureAverageFromCache(), 0.01);
    }

    @Test
    public void testTemperaturePersist() {
        // Arrange
        System.out.println("+++ testTemperaturePersist +++");
        String persistTime = null;

        // Act
        persistTime = wController.persistTemperature(10, 19.5);

        // Assert
        Assert.assertNotNull(persistTime);
    }
}