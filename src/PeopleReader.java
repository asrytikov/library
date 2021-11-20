import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PeopleReader implements Runnable{

    private String peopleName;

    private final static Random random = new Random();

    private static final int ACTION_NO_VISIT = 0; //не прийти в библиотеку
    private static final int ACTION_IN_LIBRARY = 1; //пришел в библиотеку

    private static final int ACTION_IN_LIBRARY_NO_ACTION = 0; // пришел в библ, но ничего не делает
    private static final int ACTION_IN_LIBRARY_GET_BOOK = 1; // пришел в библ и взял книгу
    private static final int ACTION_IN_LIBRARY_OUT_LIBRARY = 2; //ушел из библ

    private List<Book> lstBooksReader = new ArrayList<>(); //список книг читателя
    private List<Thread> threadsBookRead = new ArrayList<>();

    private List<Book> lstBookLibrary;

    public PeopleReader(String peopleName, List<Book> lstBookLibrary){
        this.peopleName = peopleName;
        this.lstBookLibrary = lstBookLibrary;

        System.out.println("Create new people reader: name "+ peopleName);
    }

    public String getPeopleName() {
        return peopleName;
    }

    private int getActionReader(int actionValuesCount){
        return random.nextInt(actionValuesCount);
    }

    private Book getBookRead(){
        Book book = null;

        if (lstBookLibrary.size() > 0){
            int indexBook = random.nextInt(lstBookLibrary.size() + 1);
            //random.nextInt(10) - случ 0 ... 9
            int i = 0;
            for (Book bookChose: lstBookLibrary){
                if (i == indexBook){
                    book = bookChose;
                    break;
                }
                i++;
            }
        }
            if (book !=null && !lstBooksReader.contains(book)){
                lstBooksReader.add(book);
            }else{
                book = null;
            }

            if (book !=null){
                System.out.println(this.peopleName + " chose the book = "+ book.getBookName());
            }

        return book;
    }

    @Override
    public void run() {
        try{
            int action = getActionReader(2);

            if (action == ACTION_NO_VISIT){
                System.out.println(this.peopleName + " won't come to library");
                return;
            }

            if (action == ACTION_IN_LIBRARY){
                Thread.sleep(random.nextInt(100));
                System.out.println(this.peopleName + " came to library");
            }

            action = -1;

            while(action != ACTION_IN_LIBRARY_OUT_LIBRARY){
                action = getActionReader(3);
                if (action == ACTION_IN_LIBRARY_NO_ACTION){
                    continue;
                }
                if (action == ACTION_IN_LIBRARY_GET_BOOK){
                    Book book = getBookRead();
                    if (book != null){
                        if (book.getLstPeopleReader().isEmpty()){
                            book.addQueuePeopleReader(this);
                            Thread thread = new Thread(book);
                            threadsBookRead.add(thread);
                            thread.start();
                        }else{
                            book.addQueuePeopleReader(this);
                        }
                    }
                    Thread.sleep(random.nextInt(100));
                    continue;
                }

                if (action==ACTION_IN_LIBRARY_OUT_LIBRARY){
                    int countThreads = threadsBookRead.size();
                    while(countThreads>0){
                        for(Thread thr: threadsBookRead){
                            if (thr.getState() == Thread.State.TERMINATED){
                                countThreads--;
                            }
                            Thread.sleep(200);
                        }
                    }
                    System.out.println(this.peopleName + " left library");
                }

            }

        }catch (InterruptedException exp){
            exp.printStackTrace();
        }
    }


}
