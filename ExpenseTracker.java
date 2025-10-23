import java.util.*;

class Expense {
    String category;
    double amount;
    String description;

    Expense(String category, double amount, String description) {
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public String toString() {
        return "Category: " + category + ", Amount: " + amount + ", Description: " + description;
    }
}

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Expense> expenses = new ArrayList<>();
        double total = 0;

        System.out.print("Enter number of expenses: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for expense " + (i + 1));
            System.out.print("Category: ");
            String category = sc.nextLine();
            System.out.print("Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine(); 
            System.out.print("Description: ");
            String description = sc.nextLine();

            Expense e = new Expense(category, amount, description);
            expenses.add(e);
            total += amount;
        }

        System.out.println("\n--- Expense Report ---");
        for (Expense e : expenses) {
            System.out.println(e);
        }

        System.out.println("\nTotal Expenses: " + total);
        sc.close();
    }
}
