package zn.practice1121;

import java.util.Scanner;

public class ScoreTest {
    public static void main(String[] args) {
        System.out.println("请输入成绩：");
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();
        try {
            ScoreUtil.validate(score);
        } catch (IllegalScoreException e) {
            System.out.println(e.getMessage());
        }
    }
}
