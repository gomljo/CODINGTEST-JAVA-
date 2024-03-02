import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long[] numbers = new long[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }
        SegmentTree segmentTree = new SegmentTree(numbers);
        segmentTree.initTreeSize();
        segmentTree.init(0, n - 1, 1);
        StringBuilder sb = new StringBuilder();
        while (true) {
            if(m==0 && k==0){
                break;
            }
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                int index = Integer.parseInt(st.nextToken()) - 1;
                long number = Long.parseLong(st.nextToken());
                long diff = number - numbers[index];
                segmentTree.update(0, n - 1, 1, index, diff);
                numbers[index] = number;
                m--;
            } else if (command == 2) {
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;
                long result = segmentTree.sum(0, n - 1, 1, start, end);
                sb.append(result).append("\n");
                k--;
            }
        }
        System.out.println(sb);
        br.close();
    }
}

class SegmentTree {
    private long[] tree;
    private final long[] array;


    public SegmentTree(long[] array) {
        this.array = array;
    }

    public void initTreeSize() {
        int h = (int) Math.ceil(Math.log(this.array.length) / Math.log(2));
        int treeSize = (int) Math.pow(2, h+1);
        tree = new long[treeSize];
    }

    public long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = array[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    public long sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    public void update(int start, int end, int node, int index, long diff) {
        if (index < start || index > end) {
            return;
        }
        tree[node] += diff;
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, diff);
        update(mid + 1, end, node * 2 + 1, index, diff);
    }

    public void printTree() {
        System.out.println(Arrays.toString(this.tree));
    }
}