package jp.ac.uryukyu.ie.e195713;

public class Main {
    public static void main(String[] args) {
        Enemy enemy = new Enemy();
        Player player = new Player();
        int NumberOfTurn = 1;
        System.out.println(enemy.getNumber());
        while(enemy.isLoser() == false & player.isLoser() == false){
            System.out.println("Turn "+NumberOfTurn);
            player.Attack(enemy);
            enemy.Attack(player);
            NumberOfTurn++;
        }
        if(enemy.isLoser()){
            System.out.println("You win!");
        }else{
            System.out.println("You lose...");
        }
    }
}