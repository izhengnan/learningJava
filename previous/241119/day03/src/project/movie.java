package project;

import java.util.Scanner;

public class movie {
    public static void main(String[] args) {
        movie_shuju movie[]=new movie_shuju[6];
        movie[0]=new movie_shuju(1,"《老友记》",40);
        movie[1]=new movie_shuju(2,"速度与激情1",40);
        movie[2]=new movie_shuju(3,"速度与激情2",40);
        movie[3]=new movie_shuju(4,"速度与激情3",40);
        movie[4]=new movie_shuju(5,"速度与激情4",40);
        movie[5]=new movie_shuju(6,"速度与激情5",40);
        solve s = new solve(movie);
        s.print();
        System.out.println("请输入要查找的电影编号");
        Scanner sc = new Scanner(System.in);
        int in = sc.nextInt();
        s.find(in);
    }
}
