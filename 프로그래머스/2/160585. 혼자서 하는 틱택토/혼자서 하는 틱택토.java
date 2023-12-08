class Solution {
        private static final int SIZE = 3;
    public static boolean isXWin(String[] board, String action){
        String[][] newBoard = new String[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            newBoard[i] = board[i].split("");
        }

        if(newBoard[0][0].equals(action) && newBoard[0][1].equals(action) && newBoard[0][2].equals(action)){
            return true;
        } else if (newBoard[1][0].equals(action) && newBoard[1][1].equals(action) && newBoard[1][2].equals(action)) {
            return true;
        } else if (newBoard[2][0].equals(action) && newBoard[2][1].equals(action) && newBoard[2][2].equals(action)) {
            return true;
        }
        else if (newBoard[0][0].equals(action) && newBoard[1][0].equals(action) && newBoard[2][0].equals(action)) {
            return true;
        }
        else if (newBoard[0][1].equals(action) && newBoard[1][1].equals(action) && newBoard[2][1].equals(action)) {
            return true;
        }
        else if (newBoard[0][2].equals(action) && newBoard[1][2].equals(action) && newBoard[2][2].equals(action)) {
            return true;
        }
        else if (newBoard[0][0].equals(action) && newBoard[1][1].equals(action) && newBoard[2][2].equals(action)) {
            return true;
        }
        else if (newBoard[0][2].equals(action) && newBoard[1][1].equals(action) && newBoard[2][0].equals(action)) {
            return true;
        }
        else {
            return false;
        }
    }


    public int solution(String[] board) {
        int answer = 1;
        int circleCount = 0;
        int xCount = 0;
        for (String value : board) {
            String[] row = value.split("");
            for (String s : row) {

                if(s.equals(".")){
                    continue;
                }

                if (s.equals("O")) {
                    circleCount++;
                } else{
                    xCount++;
                }
            }
        }
        if(circleCount < xCount){
            return 0;
        } else if (circleCount==xCount) {
            if (isXWin(board, "O")){
                return 0;
            }
        }
        else {
            if(Math.abs(circleCount - xCount) > 1){
                return 0;
            }
            if(isXWin(board, "X")){
                return 0;
            }
            if (isXWin(board, "O") && isXWin(board,"X")){
                return 0;
            }
        }
        return answer;
    }
}