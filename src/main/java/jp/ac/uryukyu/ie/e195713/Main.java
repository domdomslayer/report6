package jp.ac.uryukyu.ie.e195713;

public class Main {
    public static void main(String[] args) {
        Enemy enemy = new Enemy();
        Player player = new Player();
        int NumberOfTurn = 1;
        while(enemy.isLoser() == false & player.isLoser() == false){
            System.out.println("Turn "+NumberOfTurn);
            player.Attack(enemy);
            enemy.Attack(player);
            NumberOfTurn++;
        }
        if(enemy.isLoser()){
            if(player.isLoser()){
                System.out.println("This game is drawn.");
            } else{
                System.out.println("You win!");
            }
        }else{
            System.out.println("You lose...");
        }
    }
}