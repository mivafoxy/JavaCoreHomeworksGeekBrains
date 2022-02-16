package Actions;

import Obstacles.*;

public class JumpAction implements PlayerAction {
    private int maxDistance;
    private ActionState actionState;

    public JumpAction(int maxDistance) {
        this.maxDistance = maxDistance;
        actionState = ActionState.NONE;
    }

    @Override
    public void pass(Obstacle obstacle) {
        if (obstacle instanceof Wall) {
            Wall wall = (Wall) obstacle;
            if (wall.getDistance() <= maxDistance) {
                System.out.println("Игрок прыгнул.");
                actionState = ActionState.VICTORY;
            } else {
                System.out.println("Игрок не смог прыгнуть. Может совершить прыжок максимум на " + maxDistance);
                actionState = ActionState.DEFEAT;
            }
        }
    }

    @Override
    public ActionState getActionState() {
        return actionState;
    }
}
