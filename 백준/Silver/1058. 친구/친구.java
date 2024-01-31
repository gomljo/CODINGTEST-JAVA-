import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfFriend = Integer.parseInt(br.readLine());
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        // 친구 관계 구축
        for (int i = 0; i < numberOfFriend; i++) {
            String[] relationship = br.readLine().split("");
            List<Integer> friends = new ArrayList<>();
            for (int j = 0; j < numberOfFriend; j++) {
                if(relationship[j].equals("Y")){
                    friends.add(j);
                }
            }
            graph.put(i, friends);
        }

        // 2-친구 수 구하기
        // 각 친구마다 본인 친구의 친구까지 그 수를 구함
        // 방문 배열을 사용해볼 수 있을 것 같음
        List<Integer> friendList = new ArrayList<>(graph.keySet());
        int maxFriend = 0;
        for (Integer friendNumber: friendList){
            int numberOfMyFriend = 0;
            boolean[] isFriend = new boolean[numberOfFriend];
            isFriend[friendNumber] = true;
            List<Integer> myFriendList = graph.get(friendNumber);
            // 나랑 친구
            for (Integer myFriend : myFriendList) {
                if (!isFriend[myFriend]) {
                    isFriend[myFriend] = true;
                    numberOfMyFriend++;
                }
            }


            // 나랑 친구 관계인 친구들의 친구들
            for (Integer myFriendNumber: myFriendList) {
                List<Integer> friendOfMyFriendList = graph.get(myFriendNumber);
                for (Integer friendOfMyFriend : friendOfMyFriendList) {
                    if (!isFriend[friendOfMyFriend]) {
                        isFriend[friendOfMyFriend] = true;
                        numberOfMyFriend++;
                    }
                }
            }
            maxFriend = Math.max(maxFriend, numberOfMyFriend);
        }
        System.out.println(maxFriend);

    }
}