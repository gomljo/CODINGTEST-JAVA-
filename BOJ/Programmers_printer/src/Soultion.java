import java.util.*;

class Document{

    private final int id;
    private final int priority;

    Document(int id, int priority){
        this.id = id;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("[id: %d, priority: %d]", this.id, this.priority);
    }
}

class Solution {

    public int solution(int[] priorities, int location) {
        int answer = 0;

        LinkedList<Document> waitingQueue = new LinkedList<>();
        List<Document> printOrder = new ArrayList<>();
        for (int i = 0; i < priorities.length; i++) {
            waitingQueue.offer(new Document(i+1, priorities[i]));
        }
        while (waitingQueue.size()!=0){

            Document document = waitingQueue.pollFirst();
            boolean isPrint = true;
            for (int i = 0; i < waitingQueue.size(); i++) {
                if(document.getId()-1 != i){
                    if(document.getPriority() < waitingQueue.get(i).getPriority()){
                        waitingQueue.offerLast(document);
                        isPrint = false;
                        break;
                    }
                }
            }
            if(isPrint){
                printOrder.add(document);
            }
        }
        System.out.println(printOrder);
        for (int i = 0; i < printOrder.size(); i++) {
            Document document = printOrder.get(i);
            if(document.getId()-1==location){
                answer = i;
            }
        }
        return answer;
    }
}