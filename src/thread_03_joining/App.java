package thread_03_joining;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) throws InterruptedException {

        List<Long> inputs = Arrays.asList(0L, 3435L, 2324L, 4656L, 23L, 2435L, 5566L);

        // calc the factorials for these numbers

        List<FactorialThread> threads = new ArrayList<>();
        for(long input : inputs) {
            threads.add(new FactorialThread(input));
        }

        // start all the threads
        for(Thread thread : threads) {
            thread.start();
        }

        for(Thread thread : threads) {
            thread.join();
        }

        for(int i = 0; i < inputs.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if(factorialThread.isFinished) {
                System.err.println("Factorial of " + inputs.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("Calculation for " + inputs.get(i) + " is still in progress");
            }
        }


    }

    public static class FactorialThread extends Thread {

        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long inputNumber) {
            BigInteger tempResult = BigInteger.ONE;
            for(long i = inputNumber; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult(){
            return result;
        }
    }
}
