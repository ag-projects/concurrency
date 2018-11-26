package thread_01_creation;

public class App2 {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("Internal Exception");
            }
        });

        thread.setName("Misbehaving thread");

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("An critical error occurred in the thread " + thread.getName() + " the error is -> " + e.getMessage());
            }
        });

        thread.start();
    }
}
