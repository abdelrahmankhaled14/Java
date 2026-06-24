abstract class Account {
    protected double balance;

    public void deposit(double amount) {
        this.balance = this.balance + amount;
        System.out.println("[deposit] +" + amount + "  -> balance = " + this.balance);
    }

    public void withdraw(double amount) {           
        this.balance = this.balance - amount;
        System.out.println("[withdraw] -" + amount + "  -> balance = " + this.balance);
    }

    public double getBalance() {
        System.out.println("[getBalance] balance = " + this.balance);
        return this.balance;
    }

    public void transferTo(Account other, double amount) {
        System.out.println("[transferTo] sending " + amount + " to another account");
        this.withdraw(amount);   // polymorphic: calls the SUBCLASS withdraw
        other.deposit(amount);
    }
}

class NormalAccount extends Account {
    @Override
    public void withdraw(double amount) {
        System.out.println("[NormalAccount.withdraw] trying " + amount);
        if (amount > this.balance) {                 
            System.out.println("   rejected: insufficient funds (balance = " + this.balance + ")");
            return;
        }
        this.balance = this.balance - amount;
        System.out.println("   ok -> balance = " + this.balance);
    }
}

class SavingAccount extends Account {
    @Override
    public void withdraw(double amount) {
        System.out.println("[SavingAccount.withdraw] trying " + amount);
        if (amount > this.balance - 500) {           
            System.out.println("   rejected: must keep 500 minimum (balance = " + this.balance + ")");
            return;
        }
        this.balance = this.balance - amount;
        System.out.println("   ok -> balance = " + this.balance);
    }
}

public class Bank {
    public static void main(String[] args) {
        System.out.println("=== Test 1: NormalAccount deposit & withdraw ===");
        Account normal = new NormalAccount();
        normal.deposit(1000);
        normal.withdraw(300);     
        normal.withdraw(900);     
        normal.getBalance();

        System.out.println("\n=== Test 2: SavingAccount 500 minimum rule ===");
        Account saving = new SavingAccount();
        saving.deposit(1000);
        saving.withdraw(400);     
        saving.withdraw(200);      
        saving.getBalance();

        System.out.println("\n=== Test 3: transfer (polymorphic withdraw) ===");
        normal.transferTo(saving, 200);   // normal has 700, sends 200
        normal.getBalance();
        saving.getBalance();

        System.out.println("\n=== Test 4: the bug hunt ===");
        saving.transferTo(normal, 600);
        saving.getBalance();
        normal.getBalance();
    }
}