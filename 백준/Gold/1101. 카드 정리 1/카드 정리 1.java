import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Box> boxList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] box = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            boxList.add(new Box(box));
        }
        boxList.sort(Comparator.comparing(Box::getNumberOfColor).reversed());
        BoxArranger arranger = new BoxArranger(n, boxList);
        arranger.arrange();
        arranger.printResult();
        br.close();
    }

}

class BoxArranger {

    private List<Box> boxList;
    private int answer;
    private int n;

    public BoxArranger(int n, List<Box> boxList) {
        this.boxList = boxList;
        this.answer = Integer.MAX_VALUE;
        this.n = n;
    }

    public void arrange() {
        // 조커 박스 지정
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (boxList.get(j).getNumberOfColor() == 0) {
                    continue;
                }
                if (boxList.get(j).getNumberOfColor() == 1 && !this.isSameColorExistsIn(boxList.get(j).getExistedColor(), j)) {
                    continue;
                }
                count++;
            }
            this.answer = Math.min(count, this.answer);
        }
    }

    public boolean isSameColorExistsIn(int color, int index) {
        for (int i = index+1; i < this.n; i++) {
            if (boxList.get(i).getNumberOfColor() == 1 && boxList.get(i).getExistedColor() == color) {
                return true;
            }
        }
        return false;
    }

    public void printResult(){
        System.out.println(this.answer);
    }
}

class Box {
    private final int[] cards;

    public Box(int[] cards) {
        this.cards = cards;
    }

    public void addCards(int color, int quantity) {
        this.cards[color] += quantity;
    }

    public void subtractCards(int color, int quantity) {
        this.cards[color] -= quantity;
    }

    public int getQuantity(int color) {
        return this.cards[color];
    }

    public int getNumberOfColor() {
        int count = 0;
        for (int card : this.cards) {
            if (card > 0) {
                count++;
            }
        }
        return count;
    }

    public int getExistedColor() {
        for (int i = 0; i < this.cards.length; i++) {
            if (this.cards[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Box{" +
                "cards=" + Arrays.toString(cards) +
                '}';
    }
}