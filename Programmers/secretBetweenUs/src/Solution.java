class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        StringBuilder stringBuilder = new StringBuilder();
        char[] alphabets = s.toCharArray();
        for(char alphabet: alphabets){

            for (int i = 0; i < index; i++) {
                int next = (alphabet+1);

                while (skip.contains(String.valueOf((char) next))){
                    next += 1;
                }
                if(next > 122){
                    next -= 122;
                    next = 96;
                }
                alphabet = (char) next;
                System.out.println((char) next);
            }
        }
        return stringBuilder.toString();
    }
}
