package org.example.serviceSystem.serviceSystemImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Transaction;
import org.example.exception.ValidateIdException;
import org.example.serviceSystem.ServiceBank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankService implements ServiceBank {

    List<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "data/transactions.json";

    @Override
    public Transaction addTransaction(Transaction transaction) throws ValidateIdException {
        // Check if the transaction ID already exists in the list
        try{
            for (Transaction transactionInfo : transactions) {
                if (transactionInfo.getId().equals(transaction.getId())) {
                    // If ID already exists, throw exception
                    System.out.println("Duplication ID: " +transaction.getId());
                    throw new ValidateIdException("Error Duplication ID: "+transaction.getId());
                }
            }
            transactions.add(transaction); // If we didn't find a duplicate, add the transaction
            System.out.println("Transaction added successfully.");
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }

        return transaction;
    }

    @Override
    public boolean removeTransaction(Long id) {
        List<Transaction> listRemove = new ArrayList<>(); // lưu vào một list mới sử dụng remove all để xóa các phần tử trùng lặp
        boolean check = false;
        for(Transaction transactionInfo : transactions) {
            if(transactionInfo.getId().equals(id)){
                listRemove.add(transactionInfo);
                check = true; // đã tìm thấy đối tượng cần xóa
                break;
            }
        }
        if(listRemove.isEmpty()){
            System.out.println("can not find id same to: "+id);
        }
        transactions.removeAll(listRemove);
        return check;
    }

    @Override
    public void showAllTransaction() {
        if(transactions.isEmpty()){
            System.out.println("No transactions available.");
        }else {
            transactions.forEach(System.out::println);
        }
    }

    @Override
    public List<Transaction> searchTransaction(String name) {

        List<Transaction> transactionsFind = new ArrayList<>();

        if (name == null || name.isEmpty()) {
            System.out.println("Transaction name cannot be null or empty.");
            return null;
        }

        for (Transaction transaction : transactions) {
            // Kiểm tra nếu tên giao dịch khớp với tên tìm kiếm
            if (transaction.getTransactionName().equals(name)) {
                transactionsFind.add(transaction);
            }
        }

        // Nếu không tìm thấy giao dịch nào
        System.out.println("Cannot find transaction with name: " + name);
        return transactionsFind;
    }

    @Override
    public void saveToFile() {
        try {
            // Create the directory if it doesn't exist
            File directory = new File("src/main/resources");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // Save transactions to file
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(FILE_NAME), transactions);
            System.out.println("Transactions saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    @Override
    public void loadToFile() {
        try {
            File file = new File(FILE_NAME);
            // Kiểm tra nếu file tồn tại trước khi đọc
            if (!file.exists()) {
                System.out.println("File not found: " + FILE_NAME);
                return; // Không thực hiện gì nếu file không tồn tại
            }
            // Đọc dữ liệu từ file JSON và chuyển thành danh sách Transaction
            ObjectMapper objectMapper = new ObjectMapper();

            // Đảm bảo rằng JSON chứa một danh sách các đối tượng Transaction
            transactions = objectMapper.readValue(file, new TypeReference<List<Transaction>>() {});

            System.out.println("Transactions loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}
