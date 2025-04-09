package org.example.serviceSystem;
import java.util.List;
import org.example.entity.Transaction;
import org.example.exception.ValidateIdException;

public interface ServiceBank {
    Transaction addTransaction (Transaction transaction) throws ValidateIdException;

    boolean removeTransaction (Long id);

    void showAllTransaction();

    List<Transaction> searchTransaction(String name);

    void saveToFile();

    void loadToFile();
}
