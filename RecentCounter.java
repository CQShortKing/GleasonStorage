package timeRequiredToBuy;

class RecentCounter {
    int[] records;
    int head, tail;

    public RecentCounter() {
        records = new int[10010];
        head = tail = 0;
    }

    public int ping(int t) {
        records[tail++] = t;
        while (head < tail && records[head] < t - 3000) {
            head++;
        }
        return tail - head;
    }
}