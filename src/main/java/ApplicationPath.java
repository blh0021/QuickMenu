import java.io.File;
import java.util.*;

class SortByFile implements Comparator<File> {
    public int compare(File a, File b) {
        return a.getName().compareTo(b.getName());
    }
}

public class ApplicationPath {

    private String path = System.getenv("PATH");
    private String os = System.getProperty("os.name");
    private Log log = new Log(getClass());

    public List<File> files;
    public List<String> filesString;

    public ApplicationPath() {
        files = new ArrayList<File>();
        filesString = new ArrayList<String>();
    }

    public List<File> getFileList() {
        String split = os.contains("Windows") ? ";" : ":";

        String[] paths = path.split(split);
        for (String p : paths) {

            File folder = new File(p);
            try {
                files.addAll(Arrays.asList(folder.listFiles()));
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }

        files.sort(new SortByFile());

        for (File f : files) {

            if (f.canExecute() && !os.contains("Windows")) {
                filesString.add(f.getName());
            } else {

                if (f.getName().matches(".*\\.(exe|cmd|bat)"))
                    filesString.add(f.getName());
            }
        }

        //remove duplicates
        filesString = new ArrayList<>(new HashSet<>(filesString));

        return files;
    }

}
