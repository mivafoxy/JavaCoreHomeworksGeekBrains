package Actions;

import Obstacles.Pool;

public class SwimAction implements PlayerAction<Pool> { // SwimAction теперь явно работает с Pool, и instanceOf не нужен
    private int maxDistance;
    private ActionState actionState;

    public SwimAction(int maxDistance) {
        this.maxDistance = maxDistance;
        actionState = ActionState.NONE;
    }

    @Override
    public void pass(Pool obstacle) {
        if (obstacle.getDistance() <= maxDistance) {
            System.out.println("Игрок проплыл.");
            actionState = ActionState.VICTORY;
        } else {
            System.out.println("Игрок не смог проплыть. Может проплыть максимум: " + maxDistance);
            actionState = ActionState.DEFEAT;
        }
    }

    @Override
    public ActionState getActionState() {
        return actionState;
    }
}
