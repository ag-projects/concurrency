package thread_06_sharing_data;

public class App {

    public static void main(String[] args) throws InterruptedException {

        InventoryCounter inventoryCounter = new InventoryCounter();

        IncreamentingThread increamentingThread = new IncreamentingThread(inventoryCounter);
        DecreamentingThread decreamentingThread = new DecreamentingThread(inventoryCounter);

        increamentingThread.start();
        decreamentingThread.start();

        System.out.println();

        increamentingThread.join();
        decreamentingThread.join();

        System.out.println("Number of items we currently have " + inventoryCounter.getItems() + " items.");
    }

    private static class InventoryCounter {
        private int items = 0;

        public synchronized void increament() {
            items++;
        }

        public synchronized void decreament() {
            items--;
        }

        public synchronized int getItems() {
            return items;
        }
    }

    private static class IncreamentingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public IncreamentingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increament();
            }
        }
    }

    private static class DecreamentingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public DecreamentingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i< 10000; i++) {
                inventoryCounter.decreament();
            }
        }
    }
}
