import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int k = Integer.parseInt(params[1]);
        int[] a = new int[n + 1];
        a[0] = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        HeapSort heapSort = new HeapSort(k, a);
        System.out.println(heapSort.trace());

    }

    static class HeapSort {
        int K;
        int[] a;
        int cnt;
        public HeapSort(int k, int[] a){
            this.K = k;
            this.a = a;
            this.cnt=0;
        }
        void heapify(int[] a, int k, int n) {

            int left = 2 * k;
            int right = left+1;
            int smaller=0;
            if (right <= n) {
                if (a[left] < a[right]) {
                    smaller = left;
                } else {
                    smaller = right;
                }
            } else if (left <= n) {
                smaller = left;
            } else {
                return;
            }

            if (a[smaller] < a[k]) {
                swap(a, smaller, k);
                heapify(a, smaller, n);
            }
        }
        private void swap(int[] arr, int i, int j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
            if(++cnt == K) throw new RuntimeException();
        }
        void buildMinHeap(int[] a, int n) {
            for (int i = n / 2; i >= 1; i--) {
                heapify(a, i, n);

            }
        }

        void heap_sort(int[] a) {

            buildMinHeap(a, a.length - 1);

            for (int i = a.length - 1; i >=2; i--) {
                swap(a, 1, i);
                heapify(a, 1, i - 1);
            }
        }

        public String toString(){
            String[] result = new String[a.length-1];
            for (int i = 1; i <a.length ; i++) {
                result[i-1] = String.valueOf(a[i]);
            }

            return String.join(" ", result);
        }

        String trace(){
            try {
                heap_sort(a);
            }catch (Exception e){
                return toString();
            }
            return "-1";
        }

    }
}
