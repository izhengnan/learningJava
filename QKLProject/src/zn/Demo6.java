package zn;

public class Demo6 {
    public static void main(String[] args) {
        int[][] a = {{3,4,1,2},{2,6,0,7},{0,3,2,3}};
        int[][] b = {{1,8,9,0},{5,4,3,6},{6,5,2,4}};
        int[][] c = new int[3][4];
        for (int i = 0; i < c.length ; i++) {
            for (int j = 0; j < c[i].length ; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        for (int i = 0; i < c.length ; i++) {
            System.out.print("[");
            for (int j = 0; j < c[i].length ; j++) {
                System.out.print(c[i][j]);
                if(j!=c[i].length-1){
                    System.out.print(",");
                }
            }
            System.out.print("]");
        }
    }
}
