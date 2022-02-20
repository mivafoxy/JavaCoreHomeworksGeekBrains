package Actions;

import Obstacles.Racetrack;

public class RunAction implements PlayerAction<Racetrack> { // RunAction теперь явно работает с Racetrack, и instanceOf не нужен
    private int maxDistance;
    private ActionState actionState;

    public RunAction(int maxDistance) {
        this.maxDistance = maxDistance;
        actionState = ActionState.NONE;
    }

    @Override
    public void pass(Racetrack obstacle) {
        if (obstacle.getDistance() <= maxDistance) {
            System.out.println("Игрок пробежал.");
            actionState = ActionState.VICTORY;
        } else {
            System.out.println("Игрок не смог пробежать. Может пробежать максимум: " + maxDistance);
            actionState = ActionState.DEFEAT;
        }
    }

    @Override
    public ActionState getActionState() {
        return actionState;
    }
}
