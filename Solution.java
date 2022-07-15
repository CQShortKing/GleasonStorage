package package01;

public class Solution {

    public static void sortLost(Lost[] lostArray) {
        for (int i = 0; i < lostArray.length - 1; i++) {
            for (int j = 0; j < lostArray.length - i - j; j++) {
                if (lostArray[j].year > lostArray[j + 1].year) {
                    Lost temp = lostArray[j];
                    lostArray[j] = lostArray[j + 1];
                    lostArray[j + 1] = temp;
                } else if (lostArray[j].year == lostArray[j + 1].year) {
                    if (lostArray[j].emonth > lostArray[j + 1].emonth) {
                        Lost temp = lostArray[j];
                        lostArray[j] = lostArray[j + 1];
                        lostArray[j + 1] = temp;
                    } else if (lostArray[j].emonth == lostArray[j + 1].emonth) {
                        if (lostArray[j].day > lostArray[j + 1].day) {
                            Lost temp = lostArray[j];
                            lostArray[j] = lostArray[j + 1];
                            lostArray[j + 1] = temp;
                        }
                    }
                }
            }
        }
    }

            public static void selectByKeyword(Lost[] lostArray, String keyword){
            Lost ls;
                for (Lost lost : lostArray) {
                    if (keyword.equals(lost.username)) {
                        System.out.println(lost);
                    }
                }
            }
            public static void main(String[] args){
                CardLost[] dl = new CardLost[5];
                CardLost dl1 = new CardLost(1234, "May", 23, 2021, "Mike");
                CardLost dl2 = new CardLost(2414, "January", 12, 2022, "Peter");
                CardLost dl3 = new CardLost(5321, "March", 30, 2022, "Muller");
                CardLost dl4 = new CardLost(6211, "April", 15, 2021, "Jane");
                CardLost dl5 = new CardLost(3215, "December", 2, 2021, "Kim");
                dl[0] = dl1;
                dl[1] = dl2;
                dl[2] = dl3;
                dl[3] = dl4;
                dl[4] = dl5;
                BookLost[] bl = new BookLost[5];
                BookLost bl1 = new BookLost("Little Prince", "May", 6, 2021, "Millie");
                BookLost bl2 = new BookLost("The Great Gatsby", "August", 23, 2021, "Irving");
                BookLost bl3 = new BookLost("Jane Eyre", "June", 2, 2021, "Peyton");
                BookLost bl4 = new BookLost("Adventures in the Center of the Earth", "May", 5, 2022, "Leo");
                BookLost bl5 = new BookLost("Assassin's Creed", "July", 3, 2021, "Sadie");
                bl[0] = bl1;
                bl[1] = bl2;
                bl[2] = bl3;
                bl[3] = bl4;
                bl[4] = bl5;
                sortLost(bl);
                sortLost(dl);
                for (BookLost bookLost : bl) {
                    System.out.println(bookLost);
                }
                for (CardLost cardLost : dl) {
                    System.out.println(cardLost);
                }
                selectByKeyword(bl,"Millie");

            }

        }

