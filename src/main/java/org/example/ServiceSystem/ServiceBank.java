package org.example.ServiceSystem;
import org.example.Entity.Transaction;
import org.example.Exception.ValidateIdException;

public interface ServiceBank {
    Transaction addTransaction (Transaction transaction) throws ValidateIdException;

    boolean removeTransaction (Long id);

    void showAllTransaction();

    Transaction searchTransaction(String name);

    void saveToFile();

    void loadToFile();
}
