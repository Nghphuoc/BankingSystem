package org.example;
import org.example.Entity.Transaction;
import org.example.Exception.ValidateAmountException;
import org.example.Exception.ValidateIdException;
import org.example.ServiceSystem.ServiceSystemImpl.BankService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a bank instance
        BankService bank = new BankService();

        // Optionally load transactions from file
        bank.loadToFile();
        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Banking System ---");
            System.out.println("1. Add Transaction");
            System.out.println("2. Remove Transaction");
            System.out.println("3. Show All Transactions");
            System.out.println("4. Search Transaction");
            System.out.println("5. Save Transactions to File");
            System.out.println("6. Load Transactions from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline character


            switch (choice) {

                case 1: // Add Transaction
                    System.out.print("Enter transaction Id ");
                    Long id =scanner.nextLong();
                    System.out.print("Enter transaction name: ");
                    scanner.nextLine(); // sử dụng để tạo dòng mới nếu ko có trương trình sẽ bỏ qua phần mane
                    String name = scanner.nextLine();

                    double amount = 0;
                    try {
                        System.out.print("Enter amount: ");
                        String input = scanner.nextLine();
                        amount = parseAmount(input);  // Kiểm tra xem input có phải là số double không

                    }catch (Exception e){
                        System.out.println("error: "+e.getMessage());
                    }
                    //scanner.nextLine();  // consume newline
                    System.out.print("Enter type (deposit/withdrawal): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter note: ");
                    String note = scanner.nextLine();
                    Transaction transaction = new Transaction(id,name, amount, type, note);

                    try {
                        bank.addTransaction(transaction);
                    } catch (ValidateIdException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Transaction added.");
                    break;

                case 2: // Remove Transaction
                    System.out.print("Enter transaction Id to remove: ");
                    Long removeId = scanner.nextLong();
                    if (bank.removeTransaction(removeId)) {
                        System.out.println("Transaction removed.");
                    } else {
                        System.out.println("Transaction not found.");
                    }
                    break;

                case 3: // Show All Transactions
                    bank.showAllTransaction();
                    break;

                case 4: // Search Transaction
                    System.out.print("Enter transaction name to search: ");
                    String searchName = scanner.nextLine();
                    Transaction searchedTransaction = bank.searchTransaction(searchName);
                    if (searchedTransaction != null) {
                        System.out.println("Transaction found: " + searchedTransaction);
                    } else {
                        System.out.println("Transaction not found.");
                    }
                    break;

                case 5: // Save to File
                    bank.saveToFile();
                    break;

                case 6: // Load from File
                    bank.loadToFile();
                    break;

                case 7: // Exit
                    running = false;
                    System.out.println("Exiting system.");
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }

        // Close the scanner
        scanner.close();
        }

    public static double parseAmount(String input) throws ValidateAmountException {
        try {
            // Thử chuyển đổi đầu vào thành số double
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            // Nếu không thể chuyển đổi, ném ngoại lệ InvalidAmountException
            System.out.println("The input is not a valid number.");
            throw new ValidateAmountException("The input is not a valid number.");
        }
    }
}