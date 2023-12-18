package sample;

import robocode.*;

public class SpeedsterBot extends AdvancedRobot {
    private boolean movingForward = true;

    public void run() {
        // Configurações iniciais
        setAdjustRadarForGunTurn(true);
        setAdjustGunForRobotTurn(true);

        while (true) {
            // Gira o radar rapidamente para detectar oponentes
            setTurnRadarRight(360);

            // Movimento circular constante
            if (movingForward) {
                setAhead(Double.POSITIVE_INFINITY);
            } else {
                setBack(Double.POSITIVE_INFINITY);
            }

            // Acelera rapidamente
            setMaxVelocity(8);

            // Desacelera bruscamente
            if (getVelocity() > 4) {
                setMaxVelocity(4);
            }

            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        // Mantém uma distância variável, ajustando conforme necessário
        double distance = e.getDistance();

        if (distance > 200) {
            setFire(3); // Atira rapidamente
        } else {
            setFire(1);
        }

        // Mira diretamente no inimigo
        setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
    }

    public void onHitWall(HitWallEvent e) {
        // Inverte a direção ao atingir uma parede
        movingForward = !movingForward;
    }

    public void onHitRobot(HitRobotEvent e) {
        // Inverte a direção ao colidir com outro robô
        movingForward = !movingForward;
    }
}