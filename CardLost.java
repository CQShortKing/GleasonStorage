package package01;

public class CardLost extends Lost {
    private final int id;

    public CardLost(int id,String month,int day, int year, String username) {
        super(month, day, year, username);
        this.id = id;
    }

    @Override
    public String toString() {
        return "CardLost{" +
                "id=" + id +
                '}';
    }
}