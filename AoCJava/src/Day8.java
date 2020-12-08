import static java.lang.Integer.parseInt;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day8 {
    ArrayList<Pair<String, Integer>> listOfInstruction;
    
    static class Pair<T, U> {
        T first;
        U second;
        Pair (T first, U second) {
            this.first = first;
            this.second = second;
        }
        public String toString() {
            return "first is " + first + " second is " + second;
        }
    }
    
    public void sortTheData() throws FileNotFoundException {
        Path path = Paths.get("src", "inputDay8.txt");
        File file = new File(path.toUri());
        Scanner sc = new Scanner(file);
        listOfInstruction = new ArrayList<>();
        while (sc.hasNext()) {
            String[] s = sc.nextLine().split(" ");
            listOfInstruction.add(new Pair<>(s[0], parseInt(s[1])));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Day8 day8 = new Day8();
        day8.sortTheData();
        // day8.solve1(day8.listOfInstruction);
        day8.solve2(day8.listOfInstruction);
    }
    
    public Pair<Boolean, Integer> solve1(ArrayList<Pair<String, Integer>> listOfInstruction) {
        boolean[] visited = new boolean[listOfInstruction.size()];
        int i = 0;
        int acc = 0;
        while (i < listOfInstruction.size() && !visited[i]) {
            Pair<String, Integer> instruction = listOfInstruction.get(i);
            switch (instruction.first) {
            case "jmp":
                visited[i] = true;
                i += instruction.second;
                break;
            case "acc":
                acc += instruction.second;
            default:
                // assuming the inputs are valid, we can let acc fall through
                visited[i] = true;
                i++;
                break;
            }
        }
        return new Pair<>(visited[listOfInstruction.size() - 1] || i > listOfInstruction.size(), acc);
    }
    
    public void solve2(ArrayList<Pair<String, Integer>> listOfInstruction) {
        for (int i = 0; i < listOfInstruction.size(); i++) {
            Pair<String, Integer> instruction = listOfInstruction.get(i);
            if (instruction.first.equals("acc")) {
                continue;
            }
            ArrayList<Pair<String, Integer>> tempInstruction = (ArrayList<Pair<String, Integer>>) listOfInstruction.clone();
            if (instruction.first.equals("nop")) {
                tempInstruction.set(i, new Pair<>("jmp", instruction.second));
            } else {
                tempInstruction.set(i, new Pair<>("nop", instruction.second));
            }
            Pair<Boolean, Integer> ans = solve1(tempInstruction);
            if (ans.first) {
                System.out.println(ans.second);
                break;
            }
        }
    }
}
