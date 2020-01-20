package jp.ac.uryukyu.ie.e195713;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Numer0nerTest {

    @Test
    void judgeEAT() {
        Enemy enemy = new Enemy();
        enemy.setNumber("203");
        int eat_num = enemy.JudgeEAT(3,2,1); //attack num is 123
        assertEquals(1, eat_num);
    }

    @Test
    void judgeBITE() {
        Enemy enemy = new Enemy();
        enemy.setNumber("203");
        int bite_num = enemy.JudgeBITE(3,2,1); //attack num is 123
        assertEquals(1, bite_num);
    }
}