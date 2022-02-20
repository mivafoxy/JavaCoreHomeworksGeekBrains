package Actions;

import Obstacles.*;

public class JumpAction implements PlayerAction<Wall> { // JumpAction теперь явно работает с Wall, и instanceOf не нужен
    private int maxDistance;
    private ActionState actionState;

    public JumpAction(int maxDistance) {
        this.maxDistance = maxDistance;
        actionState = ActionState.NONE;
    }

    @Override
    public void pass(Wall obstacle) {
        if (obstacle.getDistance() <= maxDistance) {
            System.out.println("Игрок прыгнул.");
            actionState = ActionState.VICTORY;
        } else {
            System.out.println("Игрок не смог прыгнуть. Может совершить прыжок максимум на " + maxDistance);
            actionState = ActionState.DEFEAT;
        }
    }

    @Override
    public ActionState getActionState() {
        return actionState;
    }
}
