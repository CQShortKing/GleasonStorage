package timeRequiredToBuy;

import java.util.LinkedList;
import java.util.List;

public class AnimalShelf {
    List<int[]> catList;
    List<int[]> dogList;
    private int index=0;

    public AnimalShelf() {
        catList=new LinkedList<>();
        dogList=new LinkedList<>();
    }

    public void enqueue(int[] animal) {
        if(animal[1]==0){
            catList.add(new int[]{animal[0],index});
        }else{
            dogList.add(new int[]{animal[0],index});
        }
        index++;
    }

    public int[] dequeueAny() {
        if(catList.size()==0
                &&dogList.size()==0){
            return new int[]{-1,-1};
        }
        if(catList.size()==0){
            int[] cur=dogList.get(0);
            dogList.remove(0);
            return new int[]{cur[0],1};
        }else if(dogList.size()==0){
            int[] cur=catList.get(0);
            catList.remove(0);
            return new int[]{cur[0],0};
        }else{
            int[] curA=catList.get(0);
            int[] cutB=dogList.get(0);
            if(curA[1]<=cutB[1]){
                int[] cur=catList.get(0);
                catList.remove(0);
                return new int[]{cur[0],0};
            }else{
                int[] cur=dogList.get(0);
                dogList.remove(0);
                return new int[]{cur[0],1};
            }
        }
    }

    public int[] dequeueDog() {
        if(dogList.size()==0){
            return new int[]{-1,-1};
        }
        int[] cur=dogList.get(0);
        dogList.remove(0);
        return new int[]{cur[0],1};
    }

    public int[] dequeueCat() {
        if(catList.size()==0){
            return new int[]{-1,-1};
        }
        int[] cur=catList.get(0);
        catList.remove(0);
        return new int[]{cur[0],0};
    }
}