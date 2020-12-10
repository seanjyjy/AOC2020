import static java.lang.Integer.parseInt;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day10 {
    File file;
    ArrayList<Integer> ls;
    public void sortTheData() throws FileNotFoundException {
        Path path = Paths.get("src", "inputDay10.txt");
        file = new File(path.toUri());
        Scanner sc = new Scanner(file);
        ls = new ArrayList<>();
        ls.add(0);
        while (sc.hasNext()) {
            ls.add(parseInt(sc.nextLine()));
        }
        Collections.sort(ls);
        ls.add(ls.get(ls.size() - 1) + 3);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Day10 day10 = new Day10();
        day10.sortTheData();
        day10.solve1();
        day10.solve2();
    }
    
    public void solve1() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < ls.size(); i++) {
            if (map.get(ls.get(i) - ls.get(i - 1)) != null) {
                map.put(ls.get(i) - ls.get(i - 1), map.get(ls.get(i) - ls.get(i - 1)) + 1);
            } else {
                map.put(ls.get(i) - ls.get(i - 1),  1);
            }
        }
        System.out.println(map.get(1) * map.get(3));
    }
    
    public void solve2() {
        long[] arr = new long[ls.size()];
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i - j < 0) break;
                if (ls.get(i) - ls.get(i -j) > 3) break;
                arr[i] += arr[i - j];
            }
        }
        System.out.println(arr[arr.length - 1]);
    }
}
