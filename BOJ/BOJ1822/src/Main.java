import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int BinarySearch(int target, int[] arr){
        int start = 0;
        int end = arr.length-1;

        while (start<=end){
            int mid = (start+end) / 2;

            if(arr[mid] < target){
                start = mid+1;
            } else if (arr[mid] > target) {
                end = mid-1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int sizeOfA = Integer.parseInt(st.nextToken());
        int sizeOfB = Integer.parseInt(st.nextToken());

        int[] setA = new int[sizeOfA];
        int[] setB = new int[sizeOfB];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < sizeOfA; i++) {
            setA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < sizeOfB; i++) {
            setB[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(setA);
        Arrays.sort(setB);
        List<String> out = new ArrayList<>();
        for (int j : setA) {
            if (BinarySearch(j, setB) == -1) {
                out.add(String.valueOf(j));
            }
        }
        if (out.isEmpty()){
            System.out.println("0");
        }
        else {
            System.out.println(out.size());
            System.out.println(String.join(" ", out));
        }


    }
}