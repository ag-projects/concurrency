package thread_07_sharing_data;

import java.util.Random;

public class App {

    public static void main(String[] args) {

        Metrics metrics = new Metrics();

        BusinessLogic t1 = new BusinessLogic(metrics);
        BusinessLogic t2 = new BusinessLogic(metrics);
        MetricsPrinter t3 =     new MetricsPrinter(metrics);

        t1.start();
        t2.start();
        t3.start();


    }


    public static class BusinessLogic extends Thread {

        private Metrics metrics;
        Random random = new Random();

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {

                long start = System.currentTimeMillis();
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long end = System.currentTimeMillis();
                metrics.addSample(end - start);
            }
        }
    }

    public static class MetricsPrinter extends Thread {
        private Metrics metrics;

        public MetricsPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            double metricsAverage = metrics.getAverage();
            System.out.println("Current average is " + metricsAverage);

        }
    }

    public static class Metrics {

        private long count = 0;
        private volatile double average = 0.0;

        public synchronized void addSample(long sample) {
            double currentSample = average * count;
            count++;
            average = (currentSample + sample) / count;
        }

        public double getAverage() {
            return average;
        }
    }
}
