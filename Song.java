package linkedListPractice;

/**
 * Class: Song
 * 
 * @author Robert Zank
 * @version 1.0 Course : CSE 274 Fall 2023 Written: September 28, 2023
 *
 *          This class can be used to make a song object that hold the
 *          the following data: song title and artist.
 *
 *          Purpose: – This class can be used to make a song object
 */

/**
 * Represents a Song object.
 */
public class Song {
    
    // Fields
    private String title;
    private String artist;

    /**
     * Constructs an empty song object.
     */
    public Song() {
        title = null;
        artist = null;
    }
    
    /**
     * Constructs a new Song object with the specified title and artist.
     *
     * @param title  The title of the song.
     * @param artist The artist of the song.
     */
    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    /**
     * Gets the title of the song.
     *
     * @return The title of the song.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the song.
     *
     * @param title The new title of the song.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the artist of the song.
     *
     * @return The artist of the song.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Sets the artist of the song.
     *
     * @param artist The new artist of the song.
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Returns a string representation of the Song object.
     *
     * @return A string containing the title and artist of the song.
     */
    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}    
