import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChainblockImplTest {

    private Chainblock database;
    private TransactionImpl transaction;

    @BeforeEach
    public void setUp(){
        this.database = new ChainblockImpl();
        this.transaction = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Desi", "Stoyan",1000.00);
    }

    @Test
    public void testAddCorrectTransactions(){
        Assertions.assertEquals(0,this.database.getCount());
        this.database.add(transaction);
        Assertions.assertEquals(1, this.database.getCount());
    }
    @Test
    public void testAddExistingTransaction(){
        Assertions.assertEquals(0,this.database.getCount());
        this.database.add(transaction);
        Assertions.assertEquals(1, this.database.getCount());
        this.database.add(transaction);
        Assertions.assertEquals(1, this.database.getCount());
    }

    @Test
    public void testContains(){
        Assertions.assertEquals(0, this.database.getCount());
        Assertions.assertFalse(this.database.contains(23));
        Assertions.assertFalse(this.database.contains(transaction));

        this.database.add(transaction);

        Assertions.assertEquals(1, this.database.getCount());
        Assertions.assertTrue(this.database.contains(1));
        Assertions.assertTrue(this.database.contains(transaction));
    }

    @Test
    public void testChangeTransactionStatus(){
        this.database.add(transaction);
        this.database.changeTransactionStatus(1,TransactionStatus.ABORTED);
        Assertions.assertEquals(transaction.getStatus(), TransactionStatus.ABORTED);

    }

    @Test
    public void testChangeTransactionInvalidId(){
        this.database.add(transaction);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.database.changeTransactionStatus(23, TransactionStatus.FAILED);
        });
    }

    @Test
    public void testRemoveTransactionById(){
        this.database.add(transaction);
        this.database.removeTransactionById(1);
        Assertions.assertEquals(this.database.getCount(), 0);
    }

    @Test
    public void testRemoveTransactionByInvalidId(){
        this.database.add(transaction);
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            this.database.removeTransactionById(23);
        });

    }

    @Test
    public void testGetTransactionById(){
        this.database.add(transaction);
        Assertions.assertEquals(transaction, this.database.getById(1));
    }

    @Test
    public void testGetTransactionByInvalidId(){
        this.database.add(transaction);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.database.getById(23);
        });
    }

    @Test
    public void testSuccessfulGetByTransactionStatus(){
        TransactionImpl firstTransaction = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Petar", "Anna", 1000.00);
        TransactionImpl secondTransaction = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Ivan", "Georgi", 2000.00);
        TransactionImpl thirdTransaction = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Maria", "Kaloyan", 3000.00);
        this.database.add(firstTransaction);
        this.database.add(secondTransaction);
        this.database.add(thirdTransaction);

        Iterable<Transaction> successfulTransactions = this.database.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
        List<Transaction> returnedTransactions = new ArrayList<>();
        successfulTransactions.forEach(returnedTransactions::add);
        returnedTransactions.forEach(transaction1 -> Assertions.assertEquals(transaction1.getStatus(), TransactionStatus.SUCCESSFUL));
        Assertions.assertEquals(3000,returnedTransactions.getFirst().getAmount(), 0.01);
        Assertions.assertEquals(1000,returnedTransactions.getLast().getAmount(), 0.01);
    }
    @Test
    public void testGetByInvalidTransactionStatus(){
        TransactionImpl firstTransaction = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Petar", "Anna", 1000.00);
        TransactionImpl secondTransaction = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Ivan", "Georgi", 2000.00);
        TransactionImpl thirdTransaction = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Maria", "Kaloyan", 3000.00);
        this.database.add(firstTransaction);
        this.database.add(secondTransaction);
        this.database.add(thirdTransaction);
        Assertions.assertThrows(IllegalArgumentException.class,() -> this.database.getByTransactionStatus(TransactionStatus.UNAUTHORIZED));
    }
}
