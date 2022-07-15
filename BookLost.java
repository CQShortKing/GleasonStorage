package package01;

public class BookLost extends Lost {
    private final String bookName;

    public BookLost(String bookName,String month,int day, int year, String username) {
        super(month,day,year,username);
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "BookLost{" +
                "bookName='" + bookName + '\'' +
                '}';
    }
    
}
