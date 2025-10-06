import java.io.*;
import java.util.*;

class Expense implements Serializable {
    private String category;
    private double amount;
    private String date;

    public Expense(String category, double amount, String date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Date: " + date + " | Category: " + category + " | Amount: " + amount;
    }
}

class ExpenseManager {
    private List<Expense> expenses;
    private static final String FILE_NAME = "expenses.ser";

    public ExpenseManager() {
        expenses = loadExpenses();
    }

    // Add Expense
    public void addExpense(String category, double amount, String date) {
        expenses.add(new Expense(category, amount, date));
        System.out.println("✅ Expense Added Successfully!");
    }

    // Show All Expenses
    public void showAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("⚠ No expenses recorded yet.");
        } else {
            System.out.println("\n---- All Expenses ----");
            for (Expense e : expenses) {
                System.out.println(e);
            }
        }
    }

    // Show Expenses by Category
    public void showByCategory(String category) {
        boolean found = false;
        System.out.println("\n---- Expenses in " + category + " ----");
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                System.out.println(e);
                found = true;
            }
        }
        if (!found) {
            System.out.println("⚠ No expenses found in this category.");
        }
    }

    // Save Expenses using Serialization
    public void saveExpenses() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(expenses);
            System.out.println("💾 Expenses saved successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error saving expenses: " + e.getMessage());
        }
    }

    // Load Expenses
    @SuppressWarnings("unchecked")
    private List<Expense> loadExpenses() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Expense>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();
        int choice;

        do {
            System.out.println("\n===== Expense Tracker =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Expenses by Category");
            System.out.println("4. Save & Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Category (Food/Travel/Shopping): ");
                    String category = sc.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Date (dd-mm-yyyy): ");
                    String date = sc.nextLine();
                    manager.addExpense(category, amount, date);
                    break;

                case 2:
                    manager.showAllExpenses();
                    break;

                case 3:
                    System.out.print("Enter Category to View: ");
                    String cat = sc.nextLine();
                    manager.showByCategory(cat);
                    break;

                case 4:
                    manager.saveExpenses();
                    System.out.println("👋 Exiting... Data Saved!");
                    break;

                default:
                    System.out.println("❌ Invalid Choice. Try Again!");
            }
        } while (choice != 4);

        sc.close();
    }
}