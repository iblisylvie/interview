import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MapBenchmark {
    private static final int THREAD_POOL_SIZE = 5;
    private static Random rdn = new Random();

    public static void main(String[] args) throws InterruptedException {
        benchmark(new Hashtable<String, Integer>());
        benchmark(Collections.synchronizedMap(new HashMap<String, Integer>()));
        benchmark(new ConcurrentHashMap<String, Integer>());
    }

    public static void benchmark(final Map<String, Integer> map) throws InterruptedException {
        System.out.println("Test started for: " + map.getClass());
        long startTime = System.nanoTime();
        ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for (int j = 0; j < THREAD_POOL_SIZE; ++j) {
            es.execute(new Runnable() {
                public void run() {
                    for (int i = 0; i < 500000; ++i) {
                        int randomNumber = rdn.nextInt();
                        // try get
                        map.get(String.valueOf(randomNumber));
                        // try put
                        map.put(String.valueOf(randomNumber), randomNumber);
                    }
                }
            });
        }
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        double elapsed = System.nanoTime() - startTime;
        System.out.println("500k entries added/retrieved in " + elapsed / 1000000L + "ms");
    }
}
