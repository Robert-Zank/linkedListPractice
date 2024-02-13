package linkedListPractice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Class: FrontEnd
 * 
 * @author Robert Zank
 * @version 1.0 Course : CSE 274 Fall 2023 Written: September 28, 2023
 *
 *          This class will read in data about songs from a text file. From
 *          there, It will use that data to make a song object for each song.
 *          Then it will store those into a LinkedList like an album. From
 *          there the user will be prompted with a menu where they can list
 *          the songs,shuffle the songs, remove a song, add a new song, or
 *          rewrite the playlist back to the text file.
 *
 *          Purpose: – This class enables the user to store songs in a
 *          playlist and edit that playlist.
 */

public class FrontEnd {

    /**
     * Loads data from a text file and creates Song objects.
     */
    private static String load(LinkedList<Song> songs, Scanner key) {
        // prompt the user with a file name with the data they want to load
        System.out.println("Enter the .txt file you would like to load: ");

        // Loop to keep prompting the user until they enter a valid file name
        Scanner fle;
        boolean file = false;
        String fileName = "";
        while (!file) {
            try {
                fileName = key.next();
                fle = new Scanner(new File(fileName));
                file = true;

                // Loop process the data and make song objects
                while (fle.hasNextLine()) {
                    String tempTitle = fle.nextLine();
                    String tempArtist = fle.nextLine();
                    Song temp = new Song(tempTitle, tempArtist);
                    songs.insertLast(temp);
                }
                fle.close();
            } catch (FileNotFoundException e) {
                System.out.println("Please enter a valid file name: ");
            }
        }
        return fileName;
    }

    /**
     * Displays a menu to the user with various options.
     */
    private static void menu(LinkedList<Song> songs, String fileName,
            Scanner key) {
        System.out.println("\nWhat would you like to do: ");
        System.out.println("1) Play the songs sequentially");
        System.out.println("2) Shuffle the songs into a new order");
        System.out.println("3) Remove a song from the playlist");
        System.out.println("4) Add a new song to the playlist");
        System.out.println("5) Rewrite the playlist back to songs.txt");
        System.out.println("6) Exit the program and keep origional file");
        System.out.print("Enter the number you would like to slelct: ");
        // if statement to carry out the various selections in the menu
        boolean check = false;
        while (!check) {
            int selection = key.nextInt();
            key.nextLine();
            if (selection == 1) {
                printSongs(songs);
                menu(songs, fileName, key);
                check = true;
            } else if (selection == 2) {
                songs = shuffleSongs(songs);
                menu(songs, fileName, key);
                check = true;
            } else if (selection == 3) {
                removeSong(songs, key);
                menu(songs, fileName, key);
                check = true;
            } else if (selection == 4) {
                addSong(songs, key);
                menu(songs, fileName, key);
                check = true;
            } else if (selection == 5) {
                outputSongs(songs, fileName);
                check = true;
            } else if (selection == 6) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("Please enter a valid number: ");
            }
        }
    }

    /**
     * method to write songs back to the file.
     * 
     * @param songs LinkedList of songs
     */
    private static void outputSongs(LinkedList<Song> songs, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (int i = 0; i < songs.getSize(); i++) {
                Song song = songs.getAt(i);
                writer.println(song.getTitle());
                writer.println(song.getArtist());
            }
            System.out.println("Playlist written to " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to the file.");
        }
    }

    /**
     * method to add a song
     * 
     * @param songs LinkedList of songs
     */
    private static void addSong(LinkedList<Song> songs, Scanner key) {
        System.out.println("\nWhat is the title of the song?");
        String tempTitle = key.nextLine();
        System.out.println("Who is the artist?");
        String tempArtist = key.nextLine();
        Song temp = new Song(tempTitle, tempArtist);
        System.out.println("The song you want to add is as follows: ");
        System.out.println("Title: " + tempTitle + " Artist(s): "
                + tempArtist);
        System.out.println("Would you like to add this song to the top"
                + " of the playlist?");
        boolean check = false;
        while (!check) {
            System.out.println("Type \"Yes\" or \"No\": ");
            String confirmation = key.nextLine().toLowerCase();
            if (confirmation.equals("yes") || confirmation.equals("y")
                    || confirmation.equals("yep")) {
                check = true;
                songs.insertFirst(temp);
            } else if (confirmation.equals("no") || confirmation.equals("n")
                    || confirmation.equals("nope")) {
                check = true;
            } else {
                System.out.println("Please type a valid answer: ");
            }
        }
    }

    /**
     * method to remove a song
     * 
     * @param songs LinkedList of songs
     */
    private static void removeSong(LinkedList<Song> songs, Scanner key) {
        System.out.println("\nWhat song would you like to remove?");
        String selection = key.nextLine();
        // Loop to find and remove the selected song
        boolean check = false;
        for (int i = 0; i < songs.getSize(); i++) {
            if (((Song) songs.getAt(i)).getTitle().equals(selection)) {
                check = true;
            }
            if (check && i < songs.getSize() - 1) {
                songs.getAt(i).setArtist(songs.getAt(i + 1).getArtist());
                songs.getAt(i).setTitle(songs.getAt(i + 1).getTitle());
            }
        }
        if (check) {
            System.out.println("Song was sucessfully removed!\n");
            songs.removeLast();
        } else {
            System.out.println(
                    "The song was not removed because it could not be found");
        }

    }

    /**
     * method to shuffle the songs in the playlist
     * 
     * @param songs LinkedList of songs
     * 
     *              Adapted from Fisher-Yates code:
     *              https://www.geeksforgeeks.org/shuffle-a-given-array-using
     *              -fisher-yates-shuffle-algorithm/
     */
    private static LinkedList<Song> shuffleSongs(LinkedList<Song> songs) {
        // convert the LInkedLIst into an array
        Song[] songsArr = new Song[songs.getSize()];
        for (int i = 0; i < songs.getSize(); i++) {
            songsArr[i] = songs.getAt(i);
        }

        // create a random object
        Random random = new Random();

        // f for loop needed in the shuffle process
        for (int i = songsArr.length - 1; i > 0; i--) {
            // Pick a random index from 0 to i
            int j = random.nextInt(i + 1);
            // Swap arr[i] with the element at random index
            Song temp = songsArr[i];
            songsArr[i] = songsArr[j];
            songsArr[j] = temp;
        }

        // convert the array back into a LinkedList
        LinkedList<Song> songList = new LinkedList<>();
        for (Song song : songsArr) {
            songList.insertFirst(song);
            ;
        }

        // return the new shuffled LinkedList
        return songList;
    }

    /**
     * Method to print out the songs in the list
     * 
     * @param songs LinkedList of songs
     */
    private static void printSongs(LinkedList<Song> songs) {
        System.out.println("\nPlaylist:");
        for (int i = 0; i < songs.getSize(); i++) {
            Song song = songs.getAt(i);
            System.out.println("Title: " + song.getTitle() + ", Artist: "
                    + song.getArtist());
        }
    }

    /**
     * Main method to drive the process.
     *
     * @param args Command-line arguments
     */
    public static void main(String args[]) {
        Scanner key = new Scanner(System.in);
        LinkedList<Song> songs = new LinkedList<Song>();
        // Load data from the text file and create Song objects
        String fileName = load(songs, key);
        menu(songs, fileName, key); // Display the menu to the user
        key.close();
    }
}
