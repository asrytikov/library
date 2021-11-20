import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Book implements Runnable{

    private String bookName;
    private int timeBook = 2000;
    private boolean readBookLib = false; //если true, то чтение только в библиотеке
    private Queue<PeopleReader> lstPeopleReader = new ConcurrentLinkedQueue<PeopleReader>();
    //FIFO - First In First Out

    public Book(String bookName, boolean readBookLib, int timeBook){
        this.bookName = bookName;
        this.readBookLib = readBookLib;
        this.timeBook = timeBook;

        System.out.println("Create new book: name = " + bookName + ", readBookLib =  " +readBookLib + ", time = " + timeBook);
    }

    public String getBookName() {
        return bookName;
    }

    public boolean isReadBookLib() {
        return readBookLib;
    }

    public int getTimeBook() {
        return timeBook;
    }

    public Queue<PeopleReader> getLstPeopleReader() {
        return lstPeopleReader;
    }

    public void addQueuePeopleReader(PeopleReader peopleReader){
        lstPeopleReader.offer(peopleReader);
    }

    public void readBook(int timeBook, String peopleName) throws InterruptedException{
        System.out.println(peopleName+": "+this.bookName + " start reading...");
        Thread.sleep(timeBook);
        System.out.println(peopleName+": "+this.bookName + " stop reading...");
    }

    @Override
    public void run() {
        try{
            while(!lstPeopleReader.isEmpty()){
                PeopleReader peopleReader = lstPeopleReader.poll();
                readBook(new Random().nextInt(this.timeBook), peopleReader.getPeopleName());
            }
        }catch (InterruptedException exp) {
            exp.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
