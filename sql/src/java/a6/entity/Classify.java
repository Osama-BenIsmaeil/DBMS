package cs430.a6.entity;

/**
 * Represents Classify table
 */
public class Classify {
    private final String title;
    private final String group;

    public Classify(String title, String group) {
        this.title = title;
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "title='" + title + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
