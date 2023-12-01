import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {

    public static boolean isExists(List<CacheObject> cache, String city, int time){
        for (CacheObject object: cache) {
            if(object.getName().equals(city)){
                object.changeTime(time);
                return true;
            }
        }
        return false;
    }

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize==0){
            return cities.length * 5;
        }
        List<CacheObject> cache = new ArrayList<>(cacheSize);
        int hit = 1;
        int miss = 5;
        for (int i=0; i<cities.length; i++) {
            String city = cities[i].toUpperCase();
            if (cache.isEmpty()) {
                cache.add(new CacheObject(city, i));
                answer += miss;
                continue;
            }
            boolean isHit = isExists(cache, city, i);
            if(!isHit){
                if(cache.size() < cacheSize){
                    cache.add(new CacheObject(city, i));
                } else if (cache.size() == cacheSize) {
                    cache.sort(Comparator.comparing(CacheObject::getReferenceTime));
                    cache.remove(0);
                    cache.add(new CacheObject(city, i));
                }
                else {
                    System.out.println("error!");
                }
                answer += miss;
            }
            else {
                answer+=hit;
            }

        }

        System.out.println(answer);

        return answer;
    }
}

class CacheObject {
    private final String name;
    private int referenceTime;

    public CacheObject(String name, int referenceTime) {
        this.name = name;
        this.referenceTime = referenceTime;
    }

    public String getName() {
        return name;
    }

    public int getReferenceTime() {
        return referenceTime;
    }

    public void changeTime(int time){
        this.referenceTime = time;
    }

}
