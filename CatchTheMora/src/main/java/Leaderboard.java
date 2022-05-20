import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Leaderboard {
    File file;

    public Leaderboard(String player, Integer score) {
        file = new File("src\\main\\leaderboard.txt");
        List<String> lines = new ArrayList<>();
        Map<String, Integer> leaderboard = new HashMap<>();
        try {
            FileReader fr = new FileReader(file);
            FileWriter writer = new FileWriter(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        for (String l: lines) {
            String i = l.split(" ")[2];
            leaderboard.put(l.split(" ")[1], Integer.parseInt(l.split(" ")[2]));
        }

        if (leaderboard.containsKey(player) && leaderboard.get(player) < score)
            leaderboard.put(player, score);

        Map<String, Integer> temp = sortByValue(leaderboard);
        Map<String, Integer> sortedLeaderboard = invertMap(temp);

        try {
            new FileWriter(file, false).close();
            FileWriter writer = new FileWriter(file);
            Iterator it = sortedLeaderboard.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                writer.write(pair.getKey() + " " + pair.getValue() + "\n");
            }
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
}
