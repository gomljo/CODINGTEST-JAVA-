import java.util.*;

public class Solution {

    private static final List<int[]> repeatedPermutation = new ArrayList<>();
    private static void permutation(int depth, int[] permutation, int[] discounts) {
        if (depth == permutation.length) {
            repeatedPermutation.add(permutation.clone());
            return;
        }
        for (int discount : discounts) {
            permutation[depth] = discount;
            permutation(depth + 1, permutation, discounts);
        }

    }
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        List<Result> resultList = new ArrayList<>();
        permutation(0, new int[emoticons.length], new int[]{10,20,30,40});

        for (int[] discount: repeatedPermutation){
            int totalAmount = 0;
            int service = 0;
            for (int[] user : users) {
                int userAmount = 0;
                int discountStandard = user[0];
                int amountStandard = user[1];
                for (int j = 0; j < emoticons.length; j++) {
                    if (discount[j] >= discountStandard) {
                        userAmount += ((long) (emoticons[j] / 100) * (100 - discount[j]));
                    }

                }
                if (userAmount >= amountStandard) {
                    service+=1;
                }
                else {
                    totalAmount += userAmount;
                }
            }
            resultList.add(new Result(service, totalAmount));
        }
       resultList.sort(Comparator.comparing(Result::getNumberOfPeopleWhoSubscribeService)
                .thenComparing(Result::getSalesAmount));
        Result result = resultList.get(resultList.size()-1);

        answer[0] = result.getNumberOfPeopleWhoSubscribeService();
        answer[1] = result.getSalesAmount();
        return answer;
    }

}
class Result {

    private final int numberOfPeopleWhoSubscribeService;
    private final int salesAmount;


    public Result(int numberOfPeopleWhoSubscribeService, int salesAmount) {
        this.numberOfPeopleWhoSubscribeService = numberOfPeopleWhoSubscribeService;
        this.salesAmount = salesAmount;
    }

    public int getNumberOfPeopleWhoSubscribeService() {
        return numberOfPeopleWhoSubscribeService;
    }

    public int getSalesAmount() {
        return salesAmount;
    }

    @Override
    public String toString() {
        return "Result{" +
                "numberOfPeopleWhoSubscribeService=" + numberOfPeopleWhoSubscribeService +
                ", salesAmount=" + salesAmount +
                '}';
    }
}
