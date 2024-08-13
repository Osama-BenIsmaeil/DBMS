package cs430.a6.entity;

/**
 * Represents the Group table
 */
public class Group {
    private final String group;

    public Group(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Group{" +
                "group='" + group + '\'' +
                '}';
    }
}
