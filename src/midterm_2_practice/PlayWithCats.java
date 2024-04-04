package midterm_2_practice;

import java.util.Random;

class Cat {

}

class Tiger extends Cat {

}

class HouseCat extends Cat {

}

public class PlayWithCats {

    public static Cat[] getCats(int N) {

        Cat[] cats = new Cat[N];

        Random r = new Random();
        for(int j = 0; j < N; ++j) {
            boolean isHouseCat = r.nextBoolean();
            if (isHouseCat) {
                cats[j] = new HouseCat();
            } else {
                cats[j] = new Tiger();
            }
        }
        return cats;
    }

    public static int countHouseCats(Cat[] cats) {
        int count = 0;
        for(Cat cat: cats) {
            if(cat instanceof HouseCat) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Cat[] cats = getCats(100);
        System.out.println(countHouseCats(cats));
    }
}
