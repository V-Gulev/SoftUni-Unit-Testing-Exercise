import garage.Car;
import garage.Garage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GarageTest {
    private Garage garage;

    @BeforeEach
    void setUp() {
        garage = new Garage("Garage", 10);
        garage.addCar(new Car("Tesla", 500, "white"));
        garage.addCar(new Car("BMW", 650, "black"));
    }

    @Test
    void testConstructor_Should_Throw_When_Name_Null() {
        assertThrows(NullPointerException.class,
                () -> new Garage(null, 10));
    }

    @Test
    void testConstructor_Should_Throw_When_Name_Empty() {
        assertThrows(NullPointerException.class,
                () -> new Garage("", 10));
    }

    @Test
    void testConstructor_Should_Throw_When_Name_WhiteSpace() {
        assertThrows(NullPointerException.class,
                () -> new Garage("          ", 10));
    }

    @Test
    void testConstructor_Should_Throw_When_Capacity_Negative() {
        assertThrows(IllegalArgumentException.class,
                () -> new Garage("GarageName", -10));
    }

    @Test
    void testConstructor_Should_Create_Correct_Object() {
        Garage garage = new Garage("Garage", 10);

        assertEquals("Garage", garage.getName());
        assertEquals(10, garage.getCapacity());
        assertEquals(0, garage.getCount());
    }

    @Test
    void testAddCar_Should_Throw_When_No_Capacity() {
        Garage garage = new Garage("Garage", 0);

        assertThrows(IllegalArgumentException.class,
                () -> garage.addCar(new Car("Tesla", 500, "white")));
    }

    @Test
    void testAddCar_Should_Throw_When_Car_Exists() {

        assertThrows(IllegalArgumentException.class,
                () -> garage.addCar(new Car("Tesla", 500, "white")));
    }

    @Test
    void testAddCar_Should_Add_Car_Successfully() {

        garage.addCar(new Car("TeslaModel3", 500, "RED"));

        assertEquals(3, garage.getCount());
    }

    @Test
    void testRemoveCar_Should_Remove_Successfully() {

        assertTrue(garage.removeCar("Tesla"));
    }

    @Test
    void testRemoveCar_Should_Remove_UnSuccessfully() {

        assertFalse(garage.removeCar("OPEL"));
    }

    @Test
    void testGetMostPowerfulCar_Should_Return_Most_Powerful_Car() {

        String actualResult = garage.getMostPowerfulCar();

        assertEquals("BMW", actualResult);
    }

    @Test
    void testGetCarsWithDesiredColor_Should_Return_When_There_Are_Cars_With_Given_Color_Correct_List() {
        garage.addCar(new Car("AUDI", 150, "RED"));
        garage.addCar(new Car("TeslaModelY", 350, "RED"));
        garage.addCar(new Car("FERRARI", 200, "RED"));

        List<Car> actualResult = garage.getCarsWithDesiredColor("RED");

        actualResult.forEach(car -> {
            assertEquals("RED", car.getColor());
        });
    }

    @Test
    void testGetCarsWithDesiredColor_When_No_Car_With_Given_Color_Should_Return_Empty_List() {
        List<Car> actualResult = garage.getCarsWithDesiredColor("RED");

        assertEquals(0, actualResult.size());
        assertTrue(actualResult.isEmpty());
    }
}
