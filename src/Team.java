import Obstacles.Obstacle;

public class Team {
    private final String title;
    private final Player[] players;

    public Team(String title, Player[] players) {
        this.title = title;
        this.players = players;
    }

    public void printAllPlayers() {
        System.out.println("Список игроков в команде: ");
        for (Player player : players) {
            System.out.println(player.getName());
        }
    }

    public void printWinners() {
        for (Player player : players) {
            if (player.isWinner()) {
                System.out.println("Преодолел дистанцию команды " + title + ": " + player.getName());
            }
        }
    }

    public void overcomeObstacle(Obstacle obstacle) {
        for (Player player : players) {
            player.tryToPassObstacle(obstacle);
        }
    }
}
