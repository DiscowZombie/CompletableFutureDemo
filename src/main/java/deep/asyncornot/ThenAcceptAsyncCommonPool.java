package deep.asyncornot;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class ThenAcceptAsyncCommonPool {

    public static void main(String[] args) throws InterruptedException {
        // This CF was created by the Main Thread
        final CompletableFuture<Integer> future = new CompletableFuture<>();

        future.thenAcceptAsync(i -> {
            // Here, we do "acceptAsync" so this part will be executed by one thread of ForkJoinPool.commonPool()
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new CompletionException(e);
            }
        });

        Thread.sleep(1000);

        future.complete(42);

    }
}
