import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import p06_TirePressureMonitoringSystem.Alarm;
import p06_TirePressureMonitoringSystem.Sensor;

public class AlarmUnitTest {
    @Test
    public void whenTyrePressureIsLessThan17_thenAlarmIsOn(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Alarm alarm = new Alarm(sensor);

        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(16.00);
        alarm.check();
        Assertions.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void whenTyrePressureIsMoreThan21_thenAlarmIsOn(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Alarm alarm = new Alarm(sensor);

        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(30.00);
        alarm.check();
        Assertions.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void whenTyrePressureIsBetween17And21_thenAlarmIsOff(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Alarm alarm = new Alarm(sensor);

        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(20.00);
        alarm.check();
        Assertions.assertFalse(alarm.getAlarmOn());
    }

    @Test
    public void whenInvokePopNextPressurePsiValue_thenReceiveRandomNumber(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(20.00);
        double valueToCheck = sensor.popNextPressurePsiValue();
        Assertions.assertEquals(20.00, valueToCheck);
    }

    @Test
    void testPopNextPressurePsiValue() {
        Sensor sensor = new Sensor();
        double pressure = sensor.popNextPressurePsiValue();

        Assertions.assertTrue(pressure >= 16 && pressure <= 22, "Pressure value should be within expected range");
    }

}
