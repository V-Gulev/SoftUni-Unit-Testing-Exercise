package aquarium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AquariumTests {
    private Fish endangeredFish;
    private Fish normalFish;
    private Fish normalFish2;
    private Aquarium aquarium;

    @BeforeEach
    public void setUp(){
        endangeredFish = new Fish("OldShark", "Ocean", 20, true);
        normalFish = new Fish("Goldfish", "Lake", 5, false);
        normalFish2 = new Fish("BlueFish", "Lake", 10, true);
        aquarium = new Aquarium("Aquarium", "Lake");
    }

    @Test
    public void testWhenAddingFishWithDifferentWaterType(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> aquarium.addFish(endangeredFish));
    }

    @Test
    public void testWhenAddingAlreadyExistingFish() {
        aquarium.addFish(normalFish);
        Assertions.assertThrows(IllegalArgumentException.class, () -> aquarium.addFish(normalFish));
    }

    @Test
    public void testWhenRemovingFish(){
        aquarium.addFish(normalFish);
        aquarium.addFish(normalFish2);
        aquarium.removeFish("Goldfish");
        Assertions.assertEquals(1, aquarium.getCount());
    }

    @Test
    public void testGettingFastestFish(){
        aquarium.addFish(normalFish2);
        aquarium.addFish(normalFish);
        Assertions.assertEquals("BlueFish", aquarium.getFastestFish());
    }

    @Test
    public void testGettingEndangeredFishes() {
        aquarium.addFish(normalFish);
        aquarium.addFish(normalFish2);
        Assertions.assertEquals("BlueFish", aquarium.getEndangeredFishes());
    }

    @Test
    public void testGettingFishesByHabitat() {
        aquarium.addFish(normalFish);
        aquarium.addFish(normalFish2);
        List<Fish> fishList = new ArrayList<>(List.of(normalFish, normalFish2));
        Assertions.assertEquals(fishList, aquarium.getFishByHabitat("Lake"));
    }
    @Test
    public void testGettingFish() {
        aquarium.addFish(normalFish);
        aquarium.addFish(normalFish2);
        Assertions.assertEquals(normalFish, aquarium.getFish("Goldfish"));
    }
    @Test
    public void testGettingAquariumName() {
        Assertions.assertEquals("Aquarium", aquarium.getName());
    }

    @Test
    public void testSettingAquariumName() {
        Assertions.assertThrows(NullPointerException.class, () -> aquarium.setName(""));
        Assertions.assertThrows(NullPointerException.class, () -> aquarium.setName(null));
    }
}
