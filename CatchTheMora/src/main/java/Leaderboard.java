import java.io.*;
import java.util.*;

public class Leaderboard {
    File file;
    Map<String, Integer> sortedLeaderboard;

    public Leaderboard(String player, Integer score) {
        file = new File("src\\main\\leaderboard.txt");
        List<String> lines = new ArrayList<>();
        Map<String, Integer> leaderboard = new HashMap<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String l: lines) {
            leaderboard.put(l.split(" ")[0], Integer.parseInt(l.split(" ")[1]));
        }

        if (!leaderboard.containsKey(player) || leaderboard.get(player) < score) {
            lines.removeIf(l -> l.startsWith(player + " "));
            leaderboard.put(player, score);
            lines.add(player + " " + score);
        }


        Map<String, Integer> temp = sortByValue(leaderboard);
        sortedLeaderboard = invertMap(temp);

        try {
            FileWriter writer = new FileWriter(file);
            for (String l : lines) {
                writer.write(l + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortedMap) {

        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortedMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static Map <String, Integer> invertMap(Map <String, Integer> uninvertedMap) {
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(uninvertedMap.entrySet());
        for (int i = 0, j = list.size() - 1; i < j; i++) {
            list.add(i, list.remove(j));
        }
        Map<String, Integer> invertedMap = new LinkedHashMap<String, Integer>();
        int i = 0;
        for (Map.Entry<String, Integer> entry : list) {
            i++;
            if (i <= 5) invertedMap.put(entry.getKey(), entry.getValue());
        }
        return invertedMap;
    }

    public Map<String, Integer> returnLeaderboard() {
        return sortedLeaderboard;
    }

    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey()
                    + " Value : " + entry.getValue());
        }
    }
}
