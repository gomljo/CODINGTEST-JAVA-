import java.util.Arrays;

class Solution {

    public int[] solution(String[] park, String[] routes) {
        int maxWidth = park[0].length();
        int maxHeight = park.length;

        RobotDog robotDog = new RobotDog(maxHeight, maxWidth, park);
        robotDog.findStart();

        for(String route: routes){
            String[] directionAndMove = route.split(" ");
            String direction = directionAndMove[0];
            int move = Integer.parseInt(directionAndMove[1]);
            robotDog.move(direction, move);

        }
        return robotDog.getResult();
    }
}

class RobotDog{

    int x=0;
    int y=0;
    int maxWidth;
    int maxHeight;
    String[] park;
    public RobotDog(int maxHeight, int maxWidth, String[] park){
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
        this.park = park;
    }

    public void findStart(){
        for(int i=0; i<park.length; i++){
            String road = park[i];
            if(road.contains("S")){
                y = i;
                x = road.indexOf("S");
            }
        }
    }

    public void move(String direction, int move){
        int xDelta = 0;
        int yDelta = 0;
        System.out.println(direction);
        switch (direction) {
            case "E":
                xDelta += move;
                break;
            case "W":
                xDelta -= move;
                break;
            case "N":
                yDelta -= move;
                break;
            default:
                yDelta += move;
                break;
        }

        if(x+xDelta < maxWidth && x+xDelta >= 0 && y+yDelta < maxHeight && y+yDelta >= 0){
            if(!isObstacleOnTheRoad(xDelta, yDelta)){
                x+=xDelta;
                y+=yDelta;
            }
        }
    }

    public boolean isObstacleOnTheRoad(int xDelta, int yDelta){

        if(xDelta!=0){
            String road =park[y];
            int start = Math.min(x, x+xDelta);
            int end = Math.max(x, x+xDelta);

            String subRoad = road.substring(start, end+1);
            return subRoad.contains("X");
        }

        if(yDelta!=0){
            int start = Math.min(y, y+yDelta);
            int end = Math.max(y, y+yDelta);
            for (int i=start; i<=end; i++){

                if(park[i].charAt(x)=='X'){
                    return true;
                }
            }
        }
        return false;
    }

    public int[] getResult(){
        return new int[] {y, x};
    }
}

class Main {
    public static void main(String[] args) {
        String[] park = {"SOO","OOO","OOO"};
        String[] routes = {"E 2","S 2","W 1"};
//        String[] park = {"SOO","OXX","OOO"};
//        String[] routes = {"E 2","S 2","W 1"};
//        String[] park = {"OSO","OOO","OXO","OOO"};
//        String[] routes = {"E 2","S 3","W 1"};
        Solution solution = new Solution();
        int[] answer = solution.solution(park, routes);
        System.out.println(Arrays.toString(answer));
    }
}