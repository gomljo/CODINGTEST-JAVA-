import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        List<int[]> dataList = new ArrayList<>(Arrays.asList(data));

        dataList.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[col-1] > o2[col-1]){
                    return 1;
                } else if (o1[col-1] < o2[col-1]) {
                    return -1;
                }
                else {
                    return Integer.compare(o2[0], o1[0]);
                }
            }
        });
        
        
        List<Integer> remainList = new ArrayList<>();
        for (int i = row_begin-1; i <= row_end-1; i++) {
            int remain = 0;
            int[] rowData = dataList.get(i);
            for (int each: rowData) {
                remain += each % (i+1);
            }
            remainList.add(remain);
        }
        answer = remainList.get(0);
        for (int i = 1; i < remainList.size(); i++) {
            answer ^= remainList.get(i);
        }
        
        return answer;
    }
}
