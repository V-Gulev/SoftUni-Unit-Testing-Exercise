import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import p01_Database.Database;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    @Test
    public void whenCreateDatabaseWithZeroElements_thenExceptionIsThrown() {
        Integer[] elements = new Integer[0];
        Assertions.assertThrows(OperationNotSupportedException.class, () -> {
            Database database = new Database(elements);

        });

    }
}
