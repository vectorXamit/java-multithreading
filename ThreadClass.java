// Creating a thread by extending Thread class

class MyThread extends Thread {
    private String threadName;

    public MyThread(String name) {
        this.threadName = name;
        System.out.println("Creating thread: " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Thread running: " + threadName);
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + " - Count: " + i);
            try {
                Thread.sleep(500); // pause for 500ms
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted!");
            }
        }
        System.out.println("Thread finished: " + threadName);
    }
}

public class ThreadClass {
    public static void main(String[] args) {
        System.out.println("Main thread started.");

        MyThread t1 = new MyThread("Thread-A");
        MyThread t2 = new MyThread("Thread-B");
        MyThread t3 = new MyThread("Thread-C");

        t1.start(); // starts Thread-A
        t2.start(); // starts Thread-B
        t3.start(); // starts Thread-C

        System.out.println("Main thread ends. Threads running in background...");
    }
}
