import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int threadId = scanner.nextInt();
            int amount = scanner.nextInt();

            if (threadId == 1 || threadId == 2) {
                new Thread(new FetchTask(warehouse, threadId, amount)).start();
            } else if (threadId == 3) {
                new Thread(new SaveTask(warehouse, amount)).start();
            }
        }

        scanner.close();
    }
}

class Warehouse {
    private final int capacity = 20;
    private int[] stocks = {5, 0, 0};
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void fetch(int fetcherId, int amount) throws InterruptedException {
        lock.lock();
        try {
            while (true) {
                int maxIndex = getMaxIndex();
                if (stocks[maxIndex] >= amount) {
                    stocks[maxIndex] -= amount;
                    System.out.println("线程" + fetcherId + "取出" + amount + "个货物从货舱" + (maxIndex + 1));
                    printStocks();
                    notFull.signalAll();
                    break;
                } else {
                    notEmpty.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void save(int amount) throws InterruptedException {
        lock.lock();
        try {
            while (true) {
                int minIndex = getMinIndex();
                if (stocks[minIndex] + amount <= capacity) {
                    stocks[minIndex] += amount;
                    System.out.println("存入" + amount + "个货物到货舱" + (minIndex + 1));
                    printStocks();
                    notEmpty.signalAll();
                    break;
                } else {
                    notFull.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private int getMaxIndex() {
        int maxIndex = 0;
        for (int i = 1; i < stocks.length; i++) {
            if (stocks[i] > stocks[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private int getMinIndex() {
        int minIndex = 0;
        for (int i = 1; i < stocks.length; i++) {
            if (stocks[i] < stocks[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private void printStocks() {
        System.out.println("当前剩余货物: " + stocks[0] + ", " + stocks[1] + ", " + stocks[2]);
    }
}

class FetchTask implements Runnable {
    private final Warehouse warehouse;
    private final int fetcherId;
    private final int amount;

    public FetchTask(Warehouse warehouse, int fetcherId, int amount) {
        this.warehouse = warehouse;
        this.fetcherId = fetcherId;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            warehouse.fetch(fetcherId, amount);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class SaveTask implements Runnable {
    private final Warehouse warehouse;
    private final int amount;

    public SaveTask(Warehouse warehouse, int amount) {
        this.warehouse = warehouse;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            warehouse.save(amount);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


