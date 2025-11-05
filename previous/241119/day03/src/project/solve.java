package project;

public class solve {
    private movie_shuju movie[];
    public solve(){}
    public solve(movie_shuju movie[]){
        this.movie=movie;
    };
    public void print(){
        for(int i=0;i<movie.length;i++){
            System.out.println(movie[i].getId()+"\t"+movie[i].getName()+"\t"+movie[i].getPrice());
        }
    }
    public void find(int input){
        for(int i=0;i<movie.length;i++){
            if(movie[i].getId()==input){
                System.out.println(movie[i].getId()+"\t"+movie[i].getName()+"\t"+movie[i].getPrice());
            }
        }
    }
}
