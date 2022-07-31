package timeRequiredToBuy;

class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int seconds = 0, i = 0;
        while (true) {
            if (tickets[i] == 0) {
                if (i == k) {
                    break;
                }
            } else {
                tickets[i]--;
                seconds++;
                if (i == k && tickets[i] == 0) break;
            }
            i = (i + 1) % tickets.length;
        }
        return seconds;
    }
}