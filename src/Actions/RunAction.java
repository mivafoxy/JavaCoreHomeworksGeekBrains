package Actions;

import Obstacles.Obstacle;
import Obstacles.Racetrack;

public class RunAction implements PlayerAction {
    private int maxDistance;
    private ActionState actionState;

    public RunAction(int maxDistance) {
        this.maxDistance = maxDistance;
        actionState = ActionState.NONE;
    }

    @Override
    public void pass(Obstacle obstacle) {
        /*
        Проверка препятствия - если оно нужного типа, его можно преодолеть.
         */
        if (obstacle instanceof Racetrack) {
            Racetrack racetrack = (Racetrack) obstacle;
            if (racetrack.getDistance() <= maxDistance) {
                System.out.println("Игрок пробежал.");
                actionState = ActionState.VICTORY;
            } else {
                System.out.println("Игрок не смог пробежать. Может пробежать максимум: " + maxDistance);
                actionState = ActionState.DEFEAT;
            }
        }
    }

    @Override
    public ActionState getActionState() {
        return actionState;
    }
}
