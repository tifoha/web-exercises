package pack;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        List<Integer> results = new CopyOnWriteArrayList<>();
        ExecutorService exec = Executors.newFixedThreadPool(10);
        Random r = new Random();
        Function<Integer, Future<Integer>> mapper = delay -> exec.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(delay);
            return delay;
        });

        long start = System.currentTimeMillis();

        List<Future<Integer>> futures = r.ints(40, 0, 3000)
                .boxed()
                .map(mapper)
                .collect(Collectors.toList());

        exec.shutdown();

        CompletableFuture[] tasks = futures.stream()
        .map(f->CompletableFuture.supplyAsync(() -> {
    try {
        return f.get();
    } catch (Exception ignored) { }
    return null;
})
                .thenAccept(results::add))
        .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks)
                .join();

        System.out.println(results);
        System.out.println(Collections.max(results));
        System.out.println(results.stream().mapToInt(Integer::intValue).sum());
        System.out.println(Duration.of(System.currentTimeMillis() - start, ChronoUnit.MILLIS));
        Optional.ofNullable("sdsf");
    }
}