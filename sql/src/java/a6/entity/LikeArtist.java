package cs430.a6.entity;

/**
 * Represents like_artist table
 */
public class LikeArtist {

    public final String customerId;
    public final String artistName;

    public LikeArtist(String customerId, String artistName) {
        this.customerId = customerId;
        this.artistName = artistName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getArtistName() {
        return artistName;
    }

    @Override
    public String toString() {
        return "LikeArtist{" +
                "customerId='" + customerId + '\'' +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
