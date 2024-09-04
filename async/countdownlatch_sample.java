import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        int numberOfTasks = 3;
        CountDownLatch latch = new CountDownLatch(numberOfTasks);

        // Create and start three tasks
        for (int i = 0; i < numberOfTasks; i++) {
            new Thread(new Worker(latch)).start();
        }

        // Main thread waits for all tasks to complete
        latch.await();
        System.out.println("All tasks completed. Main thread proceeding.");
    }
}

class Worker implements Runnable {
    private final CountDownLatch latch;

    public Worker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            // Simulate work with sleep
            System.out.println(Thread.currentThread().getName() + " is working");
            Thread.sleep((int) (Math.random() * 1000));
            System.out.println(Thread.currentThread().getName() + " finished work");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Decrement the latch count
            latch.countDown();
        }
    }
}
