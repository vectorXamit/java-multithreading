// Demonstrating Thread.sleep() method

class CountdownThread extends Thread {
    private String name;
    private int from;

    public CountdownThread(String name, int from) {
        this.name = name;
        this.from = from;
    }

    @Override
    public void run() {
        System.out.println(name + " starting countdown from " + from);
        for (int i = from; i >= 1; i--) {
            System.out.println(name + " → " + i);
            try {
                Thread.sleep(1000); // sleep for 1 second
            } catch (InterruptedException e) {
                System.out.println(name + " was interrupted!");
                return;
            }
        }
        System.out.println(name + " → DONE!");
    }
}

public class SleepMethod {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Sleep Method Demo ===\n");

        // Example 1: Basic sleep in main thread
        System.out.println("Main thread sleeping for 2 seconds...");
        Thread.sleep(2000);
        System.out.println("Main thread woke up!\n");

        // Example 2: Two countdown threads with sleep
        CountdownThread t1 = new CountdownThread("Rocket-A", 5);
        CountdownThread t2 = new CountdownThread("Rocket-B", 3);

        t1.start();
        Thread.sleep(200); // slight offset
        t2.start();

        t1.join();
        t2.join();

        // Example 3: sleep used to simulate periodic task
        System.out.println("\nSimulating periodic task (every 1 sec, 4 times):");
        for (int i = 1; i <= 4; i++) {
            System.out.println("Task executed at second: " + i);
            Thread.sleep(1000);
        }

        System.out.println("\nAll tasks complete.");
    }
}
