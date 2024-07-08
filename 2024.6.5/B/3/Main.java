import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Future<Integer>[] results = new Future[10];

        // 创建并提交10个任务
        for (int i = 0; i < 10; i++) {
            final int start = i * 10 + 1;
            final int end = start + 9;
            results[i] = executor.submit(new SumTask(start, end));
        }

        int totalSum = 0;
        for (int i = 0; i < 10; i++) {
            try {
                totalSum += results[i].get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        System.out.println("总和是: " + totalSum);
    }
}

class SumTask implements Callable<Integer> {
    private final int start;
    private final int end;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
}