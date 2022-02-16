import Actions.ActionState;
import Actions.PlayerAction;
import Obstacles.Obstacle;

public class Player {
    private final String name;
    private final PlayerAction[] playerActions;

    public Player(String name, PlayerAction[] playerActions) {
        this.name = name;
        this.playerActions = playerActions;
    }

    public String getName() {
        return name;
    }

    public void tryToPassObstacle(Obstacle obstacle) {
        System.out.println(getName() + " пытается преодолеть " + obstacle.getObstacleName());
        for (PlayerAction action : playerActions) {
            action.pass(obstacle);
        }
    }

    public boolean isWinner() {
        for (PlayerAction action : playerActions) {
            if (action.getActionState() == ActionState.NONE || action.getActionState() == ActionState.DEFEAT) {
                return false;
            }
        }
        return true;
    }
}
