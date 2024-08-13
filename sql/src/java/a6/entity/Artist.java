package cs430.a6.entity;

/**
 * This class represents an artist
 */
public class Artist {
    // a_name
    private final String name;
    // birthplace
    private final String birthplace;
    // age
    private final int age;
    // style
    private final String style;

    public Artist(String name, String birthplace, int age, String style) {
        this.name = name;
        this.birthplace = birthplace;
        this.age = age;
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public int getAge() {
        return age;
    }

    public String getStyle() {
        return style;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", birthplace='" + birthplace + '\'' +
                ", age=" + age +
                ", style='" + style + '\'' +
                '}';
    }
}
