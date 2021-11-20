import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookLibrary {

    private static final int TIME_READ = 2000;
    private List<Book> lstBookLibrary = new ArrayList<>();

    private int booksReadRoom;
    private int booksFree;

    private int peopleCount;
    private List<PeopleReader> lstPeopleReader = new ArrayList<>();
    private List<Thread> threadsPeopleReader = new ArrayList<>();

    public BookLibrary(int booksFree, int booksReadRoom, int peopleCount){
        this.booksFree = booksFree;
        System.out.println("Add books by free...");
        fillBooksLibrary(false, booksFree);

        this.booksReadRoom = booksReadRoom;
        System.out.println("Add books by read to library...");
        fillBooksLibrary(true, booksReadRoom);

        System.out.println("Add peoples readers to library...");
        for (int i = 0; i < peopleCount; i++) {
            PeopleReader peopleReader = new PeopleReader("People"+i, lstBookLibrary);
            lstPeopleReader.add(peopleReader);
        }
    }

    public List<Book> getLstBookLibrary() {
        return lstBookLibrary;
    }

    public List<PeopleReader> getLstPeopleReader() {
        return lstPeopleReader;
    }

    private void fillBooksLibrary(boolean readRoom, int count){
        int firstIndex = lstBookLibrary.size();

        for (int i = firstIndex; i < (firstIndex) + count; i++) {
            Book book = new Book("Book"+i, readRoom, new Random().nextInt(TIME_READ));
            lstBookLibrary.add(book);
        }
    }

    public void startWorkLibrary() throws InterruptedException{
        
    }

}
