import static java.lang.Integer.parseInt;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
    Path path = Paths.get("src", "inputDay7.txt");
    File file = new File(path.toUri());

    public static void main(String[] args) throws FileNotFoundException {
        Day7 day7 = new Day7();
        day7.solve1();
        day7.solve2();
    }
    
    public HashMap<String, HashMap<String, Integer>> getMapping() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();
        while (sc.hasNext()) {
            Pattern patternForKey = Pattern.compile("^(.+) bags contain (.+)\\.$");
            Matcher matcherForKey = patternForKey.matcher(sc.nextLine());
            if (matcherForKey.find()) {
                String key = matcherForKey.group(1);
                String listOfItems = matcherForKey.group(2);
                String[] items = listOfItems.split(", ");
                HashMap<String, Integer> listOfRoots = new HashMap<>();
                for (String item : items) {
                    Pattern patternForRoot = Pattern.compile("^(\\d+) (.+) bags?$");
                    Matcher matcherForRoot = patternForRoot.matcher(item);
                    if (matcherForRoot.find()) {
                        listOfRoots.put(matcherForRoot.group(2), parseInt(matcherForRoot.group(1)));
                    }
                }
                if (!listOfRoots.isEmpty()) {
                    map.put(key, listOfRoots);
                }
            }
        }
        return map;
    }

    HashMap<String, Boolean> visited = new HashMap<>();
    
    public void dfs(HashMap<String, HashMap<String, Integer>> map, Set<String> keySet, String code) {
        for (String s : keySet) {
            if (visited.get(s) != null) continue;
            HashMap<String, Integer> innerRoot = map.get(s);
            boolean added = false;
            for (String s2 : innerRoot.keySet()) {
                if (s2.equals(code) && visited.get(s) == null) {
                    visited.put(s, true);
                    added = true;
                }
            }
            if (added) {
                dfs(map, keySet, s);
            }
        }
    }
    
    public void solve1() throws FileNotFoundException {
        HashMap<String, HashMap<String, Integer>> map = getMapping();
        dfs(map, map.keySet(), "shiny gold");
        int count1 = (int) visited.keySet().stream().filter(x -> visited.get(x) != null).count();
        System.out.println("count is " + count1);
    }
    
    int count2 = 0;
    public void dfs2(HashMap<String, HashMap<String, Integer>> map, String code, int mult) {
        if (map.containsKey(code)) {
            HashMap<String, Integer> innerMap = map.get(code);
            if (innerMap != null) {
                for (String s : innerMap.keySet()) {
                    count2 += mult * (innerMap.get(s));
                    dfs2(map, s, mult * innerMap.get(s));
                }
            }
        }
    }
    
    public void solve2() throws FileNotFoundException {
        HashMap<String, HashMap<String, Integer>> map = getMapping();
        dfs2(map, "shiny gold", 1);
        System.out.println(count2);
    }
}
