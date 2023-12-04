import java.util.*;

public class Solution {

   public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if(cacheSize==0){
            return cities.length * 5;
        }

        PriorityQueue<CacheObject> cache = new PriorityQueue<>(
                (object1, object2) -> Integer.compare(object1.getReferenceTime(), object2.getReferenceTime()));
        int hit = 1;
        int miss = 5;
        for (int i=0; i<cities.length; i++) {
            String city = cities[i].toUpperCase();
            if (cache.isEmpty()) {
                cache.add(new CacheObject(city, i));
                answer += miss;
                continue;
            }
            CacheObject cacheObject = new CacheObject(city, i);
            if (!cache.contains(cacheObject)) {
                if (cache.size() >= cacheSize) {
                    cache.poll();
                }
                cache.add(cacheObject);
                answer += miss;
            }  else {
                PriorityQueue<CacheObject> temporary = new PriorityQueue<>(
                        (object1, object2) -> Integer.compare(object1.getReferenceTime(), object2.getReferenceTime()));
                while (!cache.isEmpty() && !cache.peek().getName().equals(city)){
                    temporary.add(cache.poll());
                }
                
                if(!cache.isEmpty()){
                    CacheObject update = cache.poll();
                    update.changeTime(i);
                    temporary.add(update);
                    temporary.addAll(cache);
                }
                cache = temporary;
                answer += hit;
            }

        }

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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof CacheObject))
            return false;
        CacheObject cacheObject = (CacheObject) obj;
        return Objects.equals(this.name, cacheObject.getName());
    }

}
