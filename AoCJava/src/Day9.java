import static java.lang.Integer.parseInt;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Day9 {
    File file;
    public void sortTheData() {
        Path path = Paths.get("src", "inputDay9.txt");
        file = new File(path.toUri());
    }

    public static void main(String[] args) throws FileNotFoundException {
        Day9 day9 = new Day9();
        day9.sortTheData();
        BigInteger val = day9.solve1();
        day9.solve2(val);
    }
    
    public boolean twoSum(BigInteger x, HashSet<BigInteger> set) {
        for (BigInteger val : set) {
            if (set.contains(x.subtract(val))) {
                return true;
            }
        }
        return false;
    }
    
    public BigInteger solve1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        Queue<BigInteger> q = new LinkedList<>();
        HashSet<BigInteger> set = new HashSet<>();
        for (int j = 0; j < 25; j++) {
            BigInteger bigInteger = new BigInteger(sc.nextLine());
            q.offer(bigInteger);
            set.add(bigInteger);
        }
        while (sc.hasNext()) {
            BigInteger bigInteger2 = new BigInteger(sc.nextLine());
            if (!twoSum(bigInteger2, set)) {
                return bigInteger2;
            }
            BigInteger removeVal = q.remove();
            set.remove(removeVal);
            q.offer(bigInteger2);
            set.add(bigInteger2);
        }
        return null; // wont reach this case
    }
    
    public BigInteger getAns(ArrayList<BigInteger> ls, int start, int end) {
        BigInteger smallest = ls.get(start);
        BigInteger largest = ls.get(start);
        for (int i = start + 1; i <= end; i++) {
            if (ls.get(i).compareTo(smallest) == -1) {
                smallest = ls.get(i);
                continue;
            }
            if (ls.get(i).compareTo(largest) == 1) {
                largest = ls.get(i);
            }
        }
        return smallest.add(largest);
    }
    
    public void solve2(BigInteger val) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<BigInteger> list = new ArrayList<>();
        while (sc.hasNext()) {
            list.add(new BigInteger(sc.nextLine()));
        }
        BigInteger sum = list.get(0);
        int start = 0;
        for (int i = 1; i < list.size(); i++) {
            if (sum.equals(val)) {
                System.out.println(getAns(list, start, i - 1));
                break;
            }
            if (sum.add(list.get(i)).equals(val)) {
                System.out.println(getAns(list, start, i));
                break;
            }
            if (list.get(i).compareTo(val) == 1) {
                start = i + 1;
                sum = BigInteger.ZERO;
                continue;
            }
            sum = sum.add(list.get(i));
            if (sum.compareTo(val) == 1) {
                while (sum.compareTo(val) == 1) {
                    sum = sum.subtract(list.get(start++));
                }
            }
        }
    }
}
