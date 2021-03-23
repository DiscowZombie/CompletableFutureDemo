package deep.asyncornot;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThenAcceptAsyncExecutor {

    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws InterruptedException {
        // This CF was created by the Main Thread
        final CompletableFuture<Integer> future = new CompletableFuture<>();

        future.thenAcceptAsync(i -> {
            // Since the CF instance belong to the Main Thread, this code will be executed by him
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new CompletionException(e);
            }
        }, EXECUTOR);

        Thread.sleep(1000);

        future.complete(42);

        EXECUTOR.shutdown();
    }
}
