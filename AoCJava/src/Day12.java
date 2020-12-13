import static java.lang.Integer.parseInt;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Day12 {
    File file;
    ArrayList<String> instructions;
    // North -> 0, East -> 90, South -> 180, West -> 270
    int shipDirection = 90; // East
    int ew = 0; // east-west
    int ns = 0; //north-south
    public void sortTheData() throws FileNotFoundException {
        Path path = Paths.get("src", "inputDay12.txt");
        file = new File(path.toUri());
        Scanner sc = new Scanner(file);
        instructions = new ArrayList<>();
        while (sc.hasNext()) {
            instructions.add(sc.nextLine());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Day12 day12 = new Day12();
        day12.sortTheData();
        day12.solve1(); // 938
        day12.solve2(); // 54404
    }

    public void incrementDirection(int val) {
        if (shipDirection == 0) ns += val;
        else if (shipDirection == 90) ew += val;
        else if (shipDirection == 180) ns -= val;
        else if (shipDirection == 270) ew -= val;
    }

    public void solve1() {
        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            int val = parseInt(instruction.substring(1));
            switch (instruction.charAt(0)) {
            case 'F': 
                incrementDirection(val); 
                break;
            case 'L': 
                shipDirection = (shipDirection - val + 360) % 360; 
                break;
            case 'R': 
                shipDirection = (shipDirection + val + 360) % 360; 
                break;
            case 'N': 
                ns += val; 
                break;
            case 'S': 
                ns -= val; 
                break;
            case 'E': 
                ew += val; 
                break;
            case 'W': 
                ew -= val; 
                break;
            }
        }
        System.out.println(Math.abs(ew) + Math.abs(ns));
    }
    
    int wpE = 10; // default values
    int wpN = 1; // default values
    int ns2 = 0; // ship position
    int ew2 = 0; // ship position
    
    public void handleRotation(int val) {
        int tempE = wpE;
        int tempN = wpN;
        if (val == 90) {
            wpN = -tempE;
            wpE = tempN;
        } else if (val == 180) {
            wpE = -tempE;
            wpN = -tempN;
        } else if (val == 270) {
            wpN = tempE;
            wpE = -tempN;
        }
    }
    
    public void solve2() {
        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            int val = parseInt(instruction.substring(1));
            switch (instruction.charAt(0)) {
            case 'F':
                ew2 += (wpE * val);
                ns2 += (wpN * val);
                break;
            case 'L':
                handleRotation((360 - val) % 360);
                break;
            case 'R':
                handleRotation(val);
                break;
            case 'N':
                wpN += val;
                break;
            case 'S':
                wpN -= val;
                break;
            case 'E':
                wpE += val;
                break;
            case 'W':
                wpE -= val;
                break;
            }
        }
        System.out.println(Math.abs(ew2) + Math.abs(ns2));
    }
}