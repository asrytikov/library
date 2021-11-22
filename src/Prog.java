public class Prog {

    public static void main(String[] args) {
        System.out.println("App start..");
        int booksFreeCount = 3;
        int booksReadRoom = 3;
        int peoplesCount = 10;

        BookLibrary bookLibrary = new BookLibrary(booksFreeCount, booksReadRoom, peoplesCount);
        try {
            bookLibrary.startWorkLibrary();
        }catch (InterruptedException exp){
            exp.printStackTrace();
        }

        System.out.println("App stop..");
    }

}
