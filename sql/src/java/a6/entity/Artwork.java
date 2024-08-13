package cs430.a6.entity;

/**
 * This class represents an Artwork
 */
public class Artwork {

    private final String title;

    private final int year;

    private final String type;

    private final float price;

    private final String artistName;

    public Artwork(String title, int year, String type, float price, String artist_name) {
        this.title = title;
        this.year = year;
        this.type = type;
        this.price = price;
        this.artistName = artist_name;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public String getArtistName() {
        return artistName;
    }

    @Override
    public String toString() {
        return "Artwork{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
