import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day11 {
    File file;
    ArrayList<String> ls;
    String[][] globalArr;
    public void sortTheData() throws FileNotFoundException {
        Path path = Paths.get("src", "inputDay11.txt");
        file = new File(path.toUri());
        Scanner sc = new Scanner(file);
        ls = new ArrayList<>();
        while (sc.hasNext()) {
            ls.add(sc.nextLine());
        }
        globalArr = new String[ls.size()][ls.get(0).split("").length];
        for (int i = 0; i < ls.size(); i++) {
            globalArr[i] = ls.get(i).split("");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Day11 day11 = new Day11();
        day11.sortTheData();
        day11.solve1();
        day11.solve2();
    }
    
    public String[][] array2Dcopy(String[][] arr) {
        return Arrays.stream(arr).map(String[]::clone).toArray(String[][]::new);
    }
    
    public boolean handleEmpty(String[][] arrToUse, String[][] temp, int i, int j) {
        for (int k = -1; k < 2; k++){
            for (int l = -1; l < 2; l++) {
                if (k == 0 && l == 0) continue;
                if (i + k < 0 || i + k >= temp.length || j + l < 0 || j + l >= temp[0].length) continue;
                if (arrToUse[i + k][j + l].equals("#")) return false;
            }
        }
        // only come here if all 8 not occupied
        temp[i][j] = "#";
        return true;
    }

    public boolean handleOccupied(String[][] arrToUse, String[][] temp, int i, int j) {
        int count = 0;
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                if (k == 0 && l == 0) continue;
                if (i + k < 0 || i + k >= temp.length || j + l < 0 || j + l >= temp[0].length) continue;
                if (arrToUse[i + k][j + l].equals("#")) count++;
            }
            if (count >= 4) {
                temp[i][j] = "L";
                return true;
            }
        }
        return false;
    }
    
    public int countOccupied(String[][] arr) {
        return (int)Stream.of(arr).flatMap(Stream::of).filter(x -> x.equals("#")).count();
    }
    
    public void solve1() {
        String[][] arrToUse = array2Dcopy(globalArr);
        String[][] temp = array2Dcopy(globalArr);
        while (true) {
            boolean change = false;
            for (int i = 0; i < globalArr.length; i++) {
                for (int j = 0; j < globalArr[0].length; j++) {
                    switch (arrToUse[i][j]) {
                    case ".":
                        break;
                    case "L":
                        change |= handleEmpty(arrToUse, temp, i, j);
                        break;
                    case "#":
                        change |= handleOccupied(arrToUse, temp, i, j);
                        break;
                    }
                }
            }
            arrToUse = array2Dcopy(temp);
            if (!change) break;
        }
        System.out.println(countOccupied(arrToUse));
    }
    // clockwise
    int[][] all8directions = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    
    public boolean isDirectionOccupied(String[][] arr, int x, int y, int xInc, int yInc) {
        if (x < 0 || y < 0 || x >= arr[0].length || y >= arr.length) {
            return false;
        }
        if (arr[y][x].equals(".")) {
            return isDirectionOccupied(arr, x + xInc, y + yInc, xInc, yInc);
        }
        return !arr[y][x].equals("L");
    }
    
    public int getNumSeats(String[][] arrToUse, int i, int j) {
        Stream<int[]> mapping = Arrays.stream(all8directions);
        return (int) mapping.map(arr -> isDirectionOccupied(arrToUse, arr[0] + j, arr[1] + i, arr[0], arr[1])).filter(x -> x).count();
    }
    
    public void solve2() {
        String[][] arrToUse = array2Dcopy(globalArr);
        String[][] temp = array2Dcopy(globalArr);
        while (true) {
            boolean change = false;
            for (int i = 0; i < arrToUse.length; i++) {
                for (int j = 0; j < arrToUse[0].length; j++) {
                    if (arrToUse[i][j].equals(".")) {
                        continue;
                    }
                    int numSeatsOccupied = getNumSeats(arrToUse, i, j);
                    if (arrToUse[i][j].equals("#") && numSeatsOccupied >= 5) {
                        temp[i][j] = "L";
                        change = true;
                        continue;
                    }
                    if (arrToUse[i][j].equals("L") && numSeatsOccupied == 0) {
                        temp[i][j] = "#";
                        change = true;
                        continue;
                    }
                }
            }
            arrToUse = array2Dcopy(temp);
            if (!change) break;
        }
        System.out.println(countOccupied(arrToUse));
    }
}
