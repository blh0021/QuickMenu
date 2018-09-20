import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class SortByFile implements Comparator<File> {
    public int compare(File a, File b) {
        return a.getName().compareTo(b.getName());
    }
}

public class ApplicationPath {

    private String path = System.getenv("PATH");

    public List<File> files;
    public List<String> filesString;

    public ApplicationPath() {
        files = new ArrayList<File>();
        filesString = new ArrayList<String>();
    }

    public List<File> getFileList() {

        String[] paths = path.split(":");
        for (String p : paths) {
            File folder = new File(p);
            try {
                files.addAll(Arrays.asList(folder.listFiles()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        files.sort(new SortByFile());

        for (File f : files) {
            if (f.canExecute()) {
                filesString.add(f.getName());
            }
        }

        return files;
    }

}
