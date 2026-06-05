// Demonstrating Thread Lifecycle states:
// NEW → RUNNABLE → RUNNING → WAITING/SLEEPING → TERMINATED

class LifecycleThread extends Thread {
    public LifecycleThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + " is RUNNING (state: " + getState() + ")");

        for (int i = 1; i <= 3; i++) {
            System.out.println(getName() + " working... step " + i);
            try {
                Thread.sleep(600); // TIMED_WAITING state
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted during sleep.");
            }
        }

        System.out.println(getName() + " is about to TERMINATE.");
    }
}

public class ThreadLifecycle {
    public static void main(String[] args) throws InterruptedException {
        LifecycleThread t = new LifecycleThread("LifecycleThread");

        // NEW state
        System.out.println("After creation   -> State: " + t.getState());

        t.start();

        // RUNNABLE state (just after start)
        System.out.println("After start()    -> State: " + t.getState());

        // Let the thread sleep, then check TIMED_WAITING
        Thread.sleep(300);
        System.out.println("During sleep     -> State: " + t.getState());

        // Wait for thread to finish
        t.join();

        // TERMINATED state
        System.out.println("After join()     -> State: " + t.getState());

        System.out.println("\nThread Lifecycle Summary:");
        System.out.println("NEW → RUNNABLE → RUNNING → TIMED_WAITING → TERMINATED");
    }
}
