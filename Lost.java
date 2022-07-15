package package01;

public class Lost {
    private String month;
    protected int day;
    protected int year;
    protected int emonth;
    protected String username;


    public Lost(String month, int day, int year, String username) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.username = username;
    }

    @Override
    public String toString() {
        return "Lost{" +
                "month='" + month + '\'' +
                ", day=" + day +
                ", year=" + year +
                ", username='" + username + '\'' +
                '}';
    }

    public void getMonth(String month) {
        switch (month) {
            case "January":
                emonth = 1;
                break;
            case "February":
                emonth = 2;
                break;
            case "March":
                emonth = 3;
                break;
            case "April":
                emonth = 4;
                break;
            case "May":
                emonth = 5;
                break;
            case "June":
                emonth = 6;
                break;
            case "July":
                emonth = 7;
                break;
            case "August":
                emonth = 8;
                break;
            case "September":
                emonth = 9;
                break;
            case "October":
                emonth = 10;
                break;
            case "November":
                emonth = 11;
                break;
            case "December":
                emonth = 12;
                break;
            default:
                System.out.println("ARE YOU KIDDING ME");
        }
    }
}
