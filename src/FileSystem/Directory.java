package FileSystem;
import java.util.*;

public class Directory extends Entry {
    private List<Entry> contents;

    public Directory(String n, Directory p) {
        super(n, p);
        contents = new ArrayList<>();
    }

    protected List<Entry> getContents() {
        return contents;
    }

    @Override
    public int size() {
        int size = 0;
        for (Entry e : contents) {
            size += e.size();
        }
        return size;
    }

    protected boolean deleteEntry(Entry entry) {
        return contents.remove(entry);
    }

    public void addEntry(Entry entry) {
        contents.add(entry);
    }
}
