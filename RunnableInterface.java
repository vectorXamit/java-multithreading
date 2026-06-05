// Creating a thread by implementing Runnable interface

class MyRunnable implements Runnable {
    private String taskName;

    public MyRunnable(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Task started: " + taskName);
        for (int i = 1; i <= 5; i++) {
            System.out.println(taskName + " - Step: " + i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println(taskName + " was interrupted!");
            }
        }
        System.out.println("Task completed: " + taskName);
    }
}

public class RunnableInterface {
    public static void main(String[] args) {
        System.out.println("Main thread started.");

        // Using Runnable with Thread class
        Runnable r1 = new MyRunnable("Download");
        Runnable r2 = new MyRunnable("Upload");

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        // Using Lambda expression (shorter way - Java 8+)
        Thread t3 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Lambda Thread - Step: " + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t3.start();

        System.out.println("Main thread ends.");
    }
}
