import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class FindLauncher {
    @Option(name = "-r", required = false)
    private Boolean required = false;

    @Option(name = "-d", required = false)
    private File directoryName;

    @Argument(required = true)
    private File fileName;

    public static void main(String[] args) {
        List<String> result = new FindLauncher().launch(args);
        System.out.println(result);
    }

    public List<String> launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        }
        catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar find.jar [-r] [-d directory] filename.txt");
            parser.printUsage(System.err);
            return Collections.singletonList("Incorrect input data");
        }
        Finder finder = new Finder(required, directoryName, fileName);
        List<String> result = finder.find();
        if (result.isEmpty()) return Collections.singletonList("File " + fileName.toString() + " not found");
        return finder.find();
    }
}
