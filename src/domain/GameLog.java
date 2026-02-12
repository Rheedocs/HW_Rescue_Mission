package domain;

import java.util.ArrayList;
import java.util.List;

public class GameLog {
    private final List<String> lines = new ArrayList<>();

    public void add(String line) { lines.add(line); }
    public List<String> all() { return lines; }
}



