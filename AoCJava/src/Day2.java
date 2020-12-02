import static java.lang.Integer.parseInt;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Day2 {

    public static void main(String[] args) throws FileNotFoundException {
        solve2();
    }
    
    public static void solve() throws FileNotFoundException {
        Path path = Paths.get("src", "inputDay2.txt");
        File file = new File(path.toUri());
        Scanner sc = new Scanner(file);
        int ans = 0;
        while (sc.hasNext()) {
            String[] inputs = sc.nextLine().split(" ");
            String[] range = inputs[0].split("-");
            char letter = inputs[1].charAt(0);
            char[] words = inputs[2].toCharArray();
            int count = 0;
            for (char x : words) {
                if (x == letter) {
                    count++;
                }
            }
            if (parseInt(range[0]) <= count && count <= parseInt(range[1])) {
                ans++;
            }
        }
        System.out.println(ans);
    }
    
    public static void solve2() throws FileNotFoundException {
        Path path = Paths.get("src", "inputDay2.txt");
        File file = new File(path.toUri());
        Scanner sc = new Scanner(file);
        int ans = 0;
        while (sc.hasNext()) {
            String[] inputs = sc.nextLine().split(" ");
            String[] range = inputs[0].split("-");
            char letter = inputs[1].charAt(0);
            char[] words = inputs[2].toCharArray();
            int v1 = parseInt(range[0]) - 1;
            int v2 = parseInt(range[1]) - 1;
            int size = words.length;
            if (v1 >= 0 && v2 >= 0 && v1 < size && v2 < size && words[v1] != words[v2] && (words[v1] == letter || words[v2] == letter)) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
