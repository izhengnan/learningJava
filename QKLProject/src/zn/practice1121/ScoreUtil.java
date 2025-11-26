package zn.practice1121;

public class ScoreUtil {
    public static void validate(int score) throws IllegalScoreException {
        if (score < 0 || score > 100) {
            throw new IllegalScoreException("非法成绩：" + score + "，成绩必须在 0-100 之间");
        }else {
            System.out.println("成绩合法");
        }
    }
}
