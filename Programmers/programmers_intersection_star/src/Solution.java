import java.util.*;

class Solution {
    public boolean isInteger(double num) {
        return num % 1 == 0.0;
    }

    public List<Integer> calculateIntersection(int[] a, int[] b){
        int denominator_x = a[0] * b[1] - a[1] * b[0];
        int denominator_y = a[1]*b[0] - a[0]*b[1];

        if(denominator_x==0 || denominator_y==0){
            return new ArrayList<>();
        }

        int numerator_x = a[1] * b[2] - a[2] * b[1];
        int numerator_y = a[0]*b[2] - a[2]*b[0];

        double x = (double)numerator_x / denominator_x;
        double y = (double)numerator_y / denominator_y;

        if(isInteger(x) && isInteger(y)) {
            return List.of((int) x, (int)y);
        }
        return new ArrayList<>();
    }

    public void updateEachMaxCoordinate(int[] eachMaxCoordinate, List<Integer> newPoint){
        int newX = newPoint.get(0);
        int newY = newPoint.get(1);
        if(newY > eachMaxCoordinate[0]){
            eachMaxCoordinate[0] = newY;
        }
        if(newX < eachMaxCoordinate[1]){
            eachMaxCoordinate[1] = newX;
        }
        if (newY < eachMaxCoordinate[2]){
            eachMaxCoordinate[2] = newY;
        }
        if (newX > eachMaxCoordinate[3]){
            eachMaxCoordinate[3] = newX;
        }
    }

    public String drawing(int[] eachMaxCoordinate, List<List<Integer>> starPoints){

        int endY = eachMaxCoordinate[0] - eachMaxCoordinate[2];
        int endX = eachMaxCoordinate[3] - eachMaxCoordinate[1];
        int startY = eachMaxCoordinate[0];
        int startX = eachMaxCoordinate[3];
        String[][] picture = new String[endY][endX];

        for (int i = 0; i < endY; i++) {
            List<Integer> point = starPoints.get(i);
            for (int j = 0; j < endX; j++) {
                if(j==startX-point.get(0)){
                    picture[startY- point.get(1)][j] = "*";
                }
                picture[startY- point.get(1)][j] = ".";
            }
        }


    }


    public String[] solution(int[][] line) {
        String[] answer = {};
        // 순서대로 up, left, down, right
        int[] eachMaxCoordinate = { Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE};
        // 교점 구하기
        List<List<Integer>> coordinate = new ArrayList<>();
        for(int i=0; i<line.length; i++){
            int[] a = line[i];
            for(int j=0; j<line.length; j++){
                if(j==i){
                    continue;
                }
                int[] b = line[j];
                // 만약 x,y가 정수이고 중복된 값이 아니라면 교점 리스트에 추가
                List<Integer> newPoint = calculateIntersection(a, b);
                if(!newPoint.isEmpty() && !coordinate.contains(newPoint)){
                    updateEachMaxCoordinate(eachMaxCoordinate, newPoint);
                    coordinate.add(newPoint);
                }

            }
        }
        System.out.println(Arrays.toString(eachMaxCoordinate));
        System.out.println(coordinate);

        // 좌표계를 설정해야한다
        // 최상단 y
        // 최하단 y
        // 최좌단 x
        // 최우단 x
        // 둘 중 선택
//        coordinate = coordinate.stream()
//                .sorted(Comparator.comparingInt((List<Integer> list) -> list.get(1)).reversed()
//                        .thenComparingInt((List<Integer> list) -> list.get(0)))
//                .collect(Collectors.toList());
//        System.out.println(coordinate);
        return answer;
    }
}