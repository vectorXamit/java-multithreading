// Demonstrating Thread.join() method
// join() makes the calling thread wait until the target thread finishes

class WorkerThread extends Thread {
    private String task;
    private int duration;

    public WorkerThread(String task, int duration) {
        this.task = task;
        this.duration = duration;
    }

    @Override
    public void run() {
        System.out.println(task + " started...");
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(task + " interrupted!");
        }
        System.out.println(task + " completed!");
    }
}

public class JoinMethod {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Join Method Demo ===\n");

        // Example 1: Without join - main doesn't wait
        System.out.println("--- Without join() ---");
        WorkerThread w1 = new WorkerThread("Task-X", 1500);
        w1.start();
        System.out.println("Main continues without waiting for Task-X\n");

        Thread.sleep(2000); // just to let Task-X finish before next demo

        // Example 2: With join - sequential execution
        System.out.println("--- With join() ---");
        WorkerThread download = new WorkerThread("Download", 1500);
        WorkerThread process  = new WorkerThread("Process Data", 1000);
        WorkerThread upload   = new WorkerThread("Upload", 800);

        download.start();
        download.join();  // Wait for download to finish

        process.start();
        process.join();   // Wait for processing to finish

        upload.start();
        upload.join();    // Wait for upload to finish

        System.out.println("\nAll sequential tasks done. Main thread resumes.");

        // Example 3: join with timeout
        System.out.println("\n--- join(timeout) ---");
        WorkerThread slow = new WorkerThread("Slow Task", 3000);
        slow.start();
        slow.join(1500); // Wait max 1.5 seconds
        System.out.println("Main stopped waiting after timeout. Slow Task still: " + slow.getState());
    }
}
