package thread_01_creation.adventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    public static final int MAX_PASSWORD = 9999;

    public static void main(String[] args) {

        Random r = new Random();
        Vault vault = new Vault(r.nextInt(MAX_PASSWORD));

        List<Thread> threads = new ArrayList<>();
        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHacherThread(vault));
        threads.add(new PoliceThread());

        for(Thread thread : threads) {
            thread.start();
        }

    }

    /**
     * Vault class
     */
    private static class Vault {
        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return password == guess;
        }
    }

    /**
     * Hacker Thread class
     */
    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    /**
     * Ascending Hacker Thread class
     */
    private static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess=0; guess<MAX_PASSWORD; guess++) {
                if(vault.isCorrectPassword(guess)) {
                    System.err.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Descending Hacker Thread class
     */
    private static class DescendingHacherThread extends HackerThread {

        public DescendingHacherThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess = MAX_PASSWORD; guess >= 0; guess--) {
                if(vault.isCorrectPassword(guess)) {
                    System.err.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Police Thread
     */
    private static class PoliceThread extends Thread {

        @Override
        public void run() {
            for(int i=10; i>0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }
}
