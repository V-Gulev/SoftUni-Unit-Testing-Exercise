import mushroomKingdom.Field;
import mushroomKingdom.Mushroom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MushroomKingdomTests {
    private Mushroom goodMushroom;
    private Mushroom badMushroom;
    private Field field;

    @BeforeEach
    public void setUp() {
        goodMushroom = new Mushroom("White", "Bitter", true, 2);
        badMushroom = new Mushroom("Muhomorka", "Sour", false, 20);
        field = new Field("Pole", 10);
    }

    @Test
    public void testWhenAddMushroomButThereIsNoSpace(){
        Field fullField = new Field("FullField", 1);
        fullField.addMushroom(goodMushroom);
        Assertions.assertThrows(IllegalArgumentException.class, () -> fullField.addMushroom(badMushroom));
    }

    @Test
    public void testWhenAddAlreadyExistingMushroom(){
        field.addMushroom(badMushroom);
        Assertions.assertThrows(IllegalArgumentException.class, () -> field.addMushroom(badMushroom));
    }

    @Test
    public void testWhenRemovingMushroomSuccessfully(){
        field.addMushroom(badMushroom);
        field.addMushroom(goodMushroom);
        int count = field.getCount();
        field.removeMushroom("White");
        Assertions.assertNotEquals(count, field.getCount());
        Assertions.assertEquals(count - 1, field.getCount());
    }

    @Test
    public void testWhenGettingMostPoisonousMushroomSuccessfully(){
        field.addMushroom(goodMushroom);
        field.addMushroom(badMushroom);
        Assertions.assertEquals("Muhomorka", field.getMostPoisonousMushroom());
    }

    @Test
    public void testGettingInedibleMushrooms(){
        Mushroom toxicMushroom = new Mushroom("GoodMushroom", "Sweet", false, 1);
        field.addMushroom(badMushroom);
        field.addMushroom(goodMushroom);
        field.addMushroom(toxicMushroom);
        List<Mushroom> inedibleMushrooms = new ArrayList<>();
        inedibleMushrooms.add(badMushroom);
        inedibleMushrooms.add(toxicMushroom);
        Assertions.assertEquals(inedibleMushrooms, field.getInedibleMushrooms());
    }
    @Test
    public void testGettingMushroomsByFlavour(){
        Mushroom toxicMushroom = new Mushroom("GoodMushroom", "Sour", false, 1);
        field.addMushroom(badMushroom);
        field.addMushroom(goodMushroom);
        field.addMushroom(toxicMushroom);
        List<Mushroom> SourMushrooms = new ArrayList<>();
        SourMushrooms.add(badMushroom);
        SourMushrooms.add(toxicMushroom);
        Assertions.assertEquals(SourMushrooms, field.getMushroomsByFlavor("Sour"));
    }
    @Test
    public void testGetMushroom(){
        field.addMushroom(badMushroom);
        field.addMushroom(goodMushroom);
        Assertions.assertEquals(goodMushroom, field.getMushroom("White"));
    }

    @Test
    public void testSettingNameIncorrectly(){
        Assertions.assertThrows(NullPointerException.class, () -> field.setName(null));
    }

    @Test
    public void testGettingMushroomName(){
        Assertions.assertEquals("Pole", field.getName());
    }

    @Test
    public void testSettingCapacityIncorrectly(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> field.setCapacity(-2));
    }
}
