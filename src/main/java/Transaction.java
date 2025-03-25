public interface Transaction {
    int getId();
    void setStatus(TransactionStatus transactionStatus);
    TransactionStatus getStatus();
    double getAmount();
}
