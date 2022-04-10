import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Finder {
    private final Boolean required;
    private final File directoryName;
    private final File fileName;

    public Finder(Boolean required, File directoryName, File fileName) {
        this.required = required;
        if (directoryName != null) this.directoryName = directoryName;
        else this.directoryName = new File(System.getProperty("user.dir"));
        this.fileName = fileName;
    }

    public List<String> find() {
        List<String> result = new ArrayList<>();
        if (!directoryName.exists()) return Collections.singletonList("Directory " + directoryName + " does not exist");
        File[] files = directoryName.listFiles();
        if (files != null) {
            for (File file: files) {
                if (file.getName().equals(fileName.toString()))
                    result.add(System.getProperty("user.dir") + "\\" + file.getPath());
                if (required && file.isDirectory()) {
                    result.addAll(new Finder(true, file, fileName).find());
                }
            }
        }
        return result;
    }
}