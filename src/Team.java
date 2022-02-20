import Obstacles.Obstacle;

/*
2. Добавить класс Team, который будет содержать:
название команды;
массив из четырех участников — в конструкторе можно сразу всех участников указывать);
метод для вывода информации о членах команды, прошедших дистанцию;
метод вывода информации обо всех членах команды.
 */
public class Team {
    private final String title;
    /*
    массив из участников
     */
    private final Player[] players;

    public Team(String title, Player[] players) {
        this.title = title;
        this.players = players;
    }

    /*
    метод вывода информации обо всех членах команды.
     */
    public void printAllPlayers() {
        System.out.println("Список игроков в команде: ");
        for (Player player : players) {
            System.out.println(player.getName());
        }
    }

    /*
    метод для вывода информации о членах команды, прошедших дистанцию;
     */
    public void printWinners() {
        for (Player player : players) {
            if (player.isWinner()) {
                System.out.println("Преодолел дистанцию команды " + title + ": " + player.getName());
            }
        }
    }

    /*
    Метод преодоления препятствия. Препятствие передается каждому игроку,
    и если тот имеет возможность его пройти (зависит от
    массива PlayersAction[]) - игрок пытается преодолеть препятствие.
     */
    public void overcomeObstacle(Obstacle obstacle) {
        for (Player player : players) {
            player.tryToPassObstacle(obstacle);
        }
    }
}
