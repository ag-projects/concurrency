package thread_01_creation;

public class App {

    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // code the will run in a new thread
                System.out.println("We are in the new thread " + Thread.currentThread().getName());
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
            }
        });

        thread.setName("New worker thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in " + Thread.currentThread().getName() + " after starting a new thread");

        // this thread will not consume any CPU time
//        Thread.sleep(3000);
    }

}
