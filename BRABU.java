package cps;

import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * BRABU - a robot by (your name here)
 */

	/**
	 * run: BRABU's default behavior
	 */


public class BRABU extends Robot {

    boolean peek; // Não gire se houver um robô na frente
    double moveAmount; // Quanto mover

    public void run() {
        // Inicialize moveAmount para o máximo possível para este campo de batalha.
        moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
        // Inicialize peek como falso
        peek = false;

        // Gire à esquerda para ficar de frente para uma parede.
        turnLeft(getHeading() % 90);
        ahead(moveAmount);
        // Gire o canhão 90 graus para a direita.
        peek = true;
        turnGunRight(90);
        turnRight(90);

        while (true) {
            peek = true;
            ahead(moveAmount);
            peek = false;
            turnRight(90);
        }
    }

    public void onHitRobot(HitRobotEvent e) {
        if (e.getBearing() > -90 && e.getBearing() < 90) {
            back(100);
        } else {
            ahead(100);
        }

        // Atire quando atingir um robô à sua frente
        fire(2);
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        fire(2);

        // Observe que a varredura é chamada automaticamente quando o robô está em movimento.
        if (peek) {
            scan();
        }
    }
}


