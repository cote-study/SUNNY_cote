package coding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

//폭탄의 마지막 문자가 들어왔을 때 stack을 꺼내서 확인하고 폭발이면 pop
public class BJ9935 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] words = br.readLine().toCharArray();
        char[] bomb= br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();

        char lastone = bomb[bomb.length-1];
        for(int i=0; i< words.length; i++) {
            stack.push(words[i]);
//            System.out.println("i: " + i);
            if (i >= bomb.length - 1 && words[i] == lastone) {
                //비교
                boolean flag = false;
//                System.out.println("stack: " + stack.size());
                for (int j = 0; j < bomb.length; j++) {
                    if (stack.get(stack.size()-j-1) != bomb[bomb.length - j - 1]) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    for (int j = 0; j < bomb.length; j++) {
                        stack.pop();
                    }

                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if(stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            for(char c : stack) {
                sb.append(c);
            }
            System.out.println(sb.toString());
        }
    }
}
/*
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
Q53rCuvs4cM0FOTj1VqS
 */