import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day3 {
    Path path = Paths.get("src", "inputDay3.txt");
    File file = new File(path.toUri());

    public static void main(String[] args) throws FileNotFoundException {
        Day3 day3 = new Day3();
        day3.solve2();
    }
    
    public void solve() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<String> totalHeight = new ArrayList<>();
        while (sc.hasNext()) {
            totalHeight.add(sc.nextLine());
        }
        int width = totalHeight.get(0).length();
        char[][] map = new char[totalHeight.size()][width];
        for (int i = 0; i < totalHeight.size(); i++) {
            map[i] = totalHeight.get(i).toCharArray();
        }
        int treeEncounted = 0;
        int i = 0;
        int j = 0;
        while (j < totalHeight.size() - 1) {
            i = (i + 3) % width;
            j +=1;
            if (map[j][i] == '#') treeEncounted++;
        }
    }
    
    public void solve2() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<String> totalHeight = new ArrayList<>();
        while (sc.hasNext()) {
            totalHeight.add(sc.nextLine());
        }
        int width = totalHeight.get(0).length();
        char[][] map = new char[totalHeight.size()][width];
        for (int i = 0; i < totalHeight.size(); i++) {
            map[i] = totalHeight.get(i).toCharArray();
        }
        int sum = 1;
        int[][] movement = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
        for (int[] key : movement) {
            int i = 0;
            int j = 0;
            int treeEncounted = 0;
            while (j < totalHeight.size() - 1) {
                i = (i + key[0]) % width;
                j += key[1];
                if (map[j][i] == '#') treeEncounted++;
            }
            sum *= treeEncounted;
        }
    }
}
