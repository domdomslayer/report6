package jp.ac.uryukyu.ie.e195713;

public class Main {
    public static void main(String[] args) {
        Enemy enemy = new Enemy();
        Player player = new Player();
        System.out.println(enemy.getNumber());
        while(enemy.isLoser() == false & player.isLoser() == false){
            player.Attack(enemy);
            enemy.Attack(player);
        }
        if(enemy.isLoser()){
            System.out.println("You win!");
        }else{
            System.out.println("You lose...");
        }

    }
}