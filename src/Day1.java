import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day1 {
    public void threeSum() throws FileNotFoundException {
        Path path = Paths.get("src","input2.txt");
        File file = new File(path.toUri());
        Scanner sc = new Scanner(file);
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (sc.hasNext()) {
            arrayList.add(sc.nextInt());
        }
        for (int i = 0; i < arrayList.size() - 2; i++) {
            HashSet<Integer> map = new HashSet<>();
            int x = arrayList.get(i);
            int sum = 2020 - x;
            for (int j = i + 1; j < arrayList.size(); j++) {
                int y = arrayList.get(j);
                if (map.contains(sum - y)) {
                    System.out.println(x * y * (2020 - x - y));
                    break;
                }
                map.add(arrayList.get(j));
            }
        }
    }

    public void twoSum() throws FileNotFoundException {
        Path path = Paths.get("src","input.txt");
        File file = new File(path.toUri());
        Scanner sc = new Scanner(file);
        HashSet<Integer> map = new HashSet<>();
        int target = 2020;
        while (sc.hasNext()) {
            int curr = sc.nextInt();
            if (map.contains(target - curr)) {
                System.out.println(curr * (target - curr));
                break;
            }
            map.add(curr);
        }
    }
}
