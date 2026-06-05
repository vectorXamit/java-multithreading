// Demonstrating Synchronization to prevent race conditions

// Shared resource (without synchronization - causes race condition)
class Counter {
    private int count = 0;

    // Unsynchronized method
    public void incrementUnsafe() {
        count++;
    }

    // Synchronized method - only one thread can access at a time
    public synchronized void incrementSafe() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public void reset() {
        count = 0;
    }
}

// Synchronized block example - Bank Account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Synchronized block inside method
    public void withdraw(String user, double amount) {
        synchronized (this) {
            if (balance >= amount) {
                System.out.println(user + " withdrawing ₹" + amount);
                try {
                    Thread.sleep(100); // simulate processing delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= amount;
                System.out.println(user + " done. Remaining balance: ₹" + balance);
            } else {
                System.out.println(user + " failed - Insufficient balance: ₹" + balance);
            }
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class SynchronizationExample {
    public static void main(String[] args) throws InterruptedException {
        // ---- Demo 1: Race condition vs synchronized method ----
        System.out.println("=== Demo 1: Synchronized Method ===");
        Counter counter = new Counter();

        Runnable safeTask = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementSafe();
            }
        };

        Thread t1 = new Thread(safeTask, "T1");
        Thread t2 = new Thread(safeTask, "T2");

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Expected count : 2000");
        System.out.println("Actual count   : " + counter.getCount());

        // ---- Demo 2: Synchronized block - BankAccount ----
        System.out.println("\n=== Demo 2: Synchronized Block (Bank Account) ===");
        BankAccount account = new BankAccount(5000);

        Thread user1 = new Thread(() -> account.withdraw("Alice", 3000), "Alice");
        Thread user2 = new Thread(() -> account.withdraw("Bob",   3000), "Bob");
        Thread user3 = new Thread(() -> account.withdraw("Carol", 2000), "Carol");

        user1.start(); user2.start(); user3.start();
        user1.join();  user2.join();  user3.join();

        System.out.println("Final balance: ₹" + account.getBalance());

        // ---- Demo 3: Static synchronization ----
        System.out.println("\n=== Demo 3: Static Synchronized Method ===");
        Runnable printTask = () -> {
            for (int i = 1; i <= 3; i++) {
                printSynced(Thread.currentThread().getName() + " - line " + i);
            }
        };

        Thread ta = new Thread(printTask, "ThreadA");
        Thread tb = new Thread(printTask, "ThreadB");
        ta.start(); tb.start();
        ta.join();  tb.join();

        System.out.println("\nAll demos complete.");
    }

    // Static synchronized method - locks on the Class object
    public static synchronized void printSynced(String msg) {
        System.out.println(msg);
        try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
