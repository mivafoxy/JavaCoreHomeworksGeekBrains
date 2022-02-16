package Actions;

import Obstacles.Obstacle;
import Obstacles.Pool;

public class SwimAction implements PlayerAction {
    private int maxDistance;
    private ActionState actionState;

    public SwimAction(int maxDistance) {
        this.maxDistance = maxDistance;
        actionState = ActionState.NONE;
    }

    @Override
    public void pass(Obstacle obstacle) {
        if (obstacle instanceof Pool) {
            Pool pool = (Pool) obstacle;
            if (pool.getDistance() <= maxDistance) {
                System.out.println("Игрок проплыл.");
                actionState = ActionState.VICTORY;
            } else {
                System.out.println("Игрок не смог проплыть. Может проплыть максимум: " + maxDistance);
                actionState = ActionState.DEFEAT;
            }
        }
    }

    @Override
    public ActionState getActionState() {
        return actionState;
    }
}
