package Actions;

import Obstacles.Obstacle;

public interface PlayerAction {
    void pass(Obstacle obstacle);
    ActionState getActionState();
}
