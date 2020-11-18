package FileSystem;
import java.util.*;

public class FileSystem {
    private final Directory root;

    public FileSystem() {
        root = new Directory("/", null);
    }

    /**
     * resolve a path like /foo/bar
     */
    private List<Entry> resolve(String path) {
        assert path.startsWith("/");
        String[] components = path.substring(1).split("/");
        List<Entry> entries = new ArrayList<Entry>(components.length + 1);
        entries.add(root);

        Entry entry = root;
        for (String component : components) {
            if (entry == null || !(entry instanceof Directory)) {
                throw new IllegalArgumentException("invalid path:" + path);
            }
            if (!component.isEmpty()) {
                // entry = ((Directory) entry).getChild(component); // where is getChild??
                entries.add(entry);
            }
        }
        return entries;
    }

    public void mkdire(String path) {
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) != null) {
            throw new IllegalArgumentException("Directory already exists: " + path);
        }
        String[] components = path.split("/");
        final String dirName = components[components.length - 1];
        final Directory parent = (Directory) entries.get(entries.size() - 2);
        Directory newDir = new Directory(dirName, parent);
        parent.addEntry(newDir);
    }

    public void createFile(String path) {
        assert !path.endsWith("/");
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) != null) {
            throw new IllegalArgumentException("File already exists: " + path);
        }
        final String fileName = path.substring(path.lastIndexOf("/") + 1);
        final Directory parent = (Directory) entries.get(entries.size() - 2);
        File file = new File(fileName, parent, 0);
        parent.addEntry(file);
    }

    public void delete(String path) {
        // TODO: delete the file/directory with the given path
        return;
    }

    public Entry[] list(String path) {  // Entry[] --> EntryInfo[]
        // TODO: list all the immediate children of the directory specified by the given path
        return null;
    }

    public int count() {
        // TODO: return the total number of files/directories in the FileSystem
        return 0;
    }
}
