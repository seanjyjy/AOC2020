import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day13 {
    File file;
    int departure;
    Integer[] buses;
    Long[][] buses2;
    String[] abc;
    public void sortTheData() throws FileNotFoundException {
        Path path = Paths.get("src", "inputDay13.txt");
        file = new File(path.toUri());
        Scanner sc = new Scanner(file);
        departure = sc.nextInt();
        String[] values = sc.next().split(",");
        abc = values;
        buses = Arrays.stream(values).filter(x -> !x.equals("x")).map(Integer::parseInt).toArray(Integer[]::new);
        buses2 = IntStream.range(0, values.length).mapToObj(x -> {
            if (values[x].equals("x")) {
                return null;
            }
            return new Long[]{parseLong(values[x]), (long)x};
        }).filter(Objects::nonNull).toArray(Long[][]::new);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Day13 day13 = new Day13();
        day13.sortTheData();
        day13.solve1();
        day13.solve2();
    }
    
    public void solve1() {
        int i = departure;
        while(true) {
            for (int x : buses) {
                if (i % x == 0) {
                    System.out.println((i - departure) * x);
                    return;
                }
            }
            i++;
        }
    }
    
    // algorithm adapted from https://www.geeksforgeeks.org/multiplicative-inverse-under-modulo-m/
    public long computeInverse(long a, long m) {
        long m0 = m;
        long y = 0, x = 1;
        if (m == 1) return 0;
        while (a > 1) {
            long q = a / m;
            long t = m;
            m = a % m;
            a = t;
            t = y;
            y = x - q * y;
            x = t;
        }
        if (x < 0) x += m0;
        return x;
    }

    // algorithm adapted from https://www.freecodecamp.org/news/how-to-implement-the-chinese-remainder-theorem-in-java-db88a3f1ffe0/
    public void solve2() {
        long product = Arrays.stream(buses2).mapToLong(a -> a[0]).reduce(1, (a, b) -> a * b);
        long[] partialProduct = new long[buses2.length];
        long[] inverse = new long[buses2.length];
        long sum = 0;
        for (int i = 0; i < buses2.length; i++) {
            partialProduct[i] = product / buses2[i][0];
            inverse[i] = computeInverse(partialProduct[i], buses2[i][0]);
            sum += partialProduct[i] * inverse[i] * buses2[i][1];
        }
        System.out.println(product - sum % product);
    }
}
