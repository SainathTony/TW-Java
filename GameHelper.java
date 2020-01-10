import java.util.*;

class GameHelper{
	public static void main(String[] args){
	    Game game = new Game();
	    game.startGame();
	}
}

class Game{
	Grid grid;
	Ball ball;
	Player player;
    Game(){
        int gridSize = 10;
        this.grid = new Grid(gridSize);
        this.ball = new Ball(getRandomPosition());
        this.player = new Player(gridSize);
    }
    public void startGame(){
        ball.printBallPosition();
    	while(!isPlayerFoundBall()){
    		player.printPlayerPosition();
    		this.player.move();
    	}
    	if(isPlayerFoundBall()){
    		System.out.println("Ball found");
    		player.printPlayerPosition();
    	}else{
    		player.printPlayerPosition();
    	}
    }
    public Coordinate getRandomPosition(){
    	Random random = new Random();
    	int x_coordinate = random.nextInt(this.grid.size-1);
    	int y_coordinate = random.nextInt(this.grid.size-1);
    	return new Coordinate(x_coordinate,y_coordinate);
    }
    public boolean isPlayerFoundBall(){
    	if(this.player.position.x_coordinate == this.ball.position.x_coordinate && this.player.position.y_coordinate == this.ball.position.y_coordinate){
    		return true;
    	}else{
    		return false;
    	}
    }
}
final class Grid{
    final int size;
    int grid[][];
    Grid(int size){
        this.size = size;
        this.grid = new int[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                this.grid[i][j] = 0 ;
            }
        }
    }
    public void placeBall(Ball ball){
    	this.grid[ball.position.x_coordinate][ball.position.y_coordinate] = 1;
    }
}
class Coordinate{
    int x_coordinate, y_coordinate;
    Coordinate(int x, int y){
        this.x_coordinate = x;
        this.y_coordinate = y;
    }
}
final class Ball{
    final Coordinate position;
    Ball(Coordinate position){
        this.position = position;
    }
    public void printBallPosition(){
        System.out.println("Ball is at"+this.position.x_coordinate+","+this.position.y_coordinate);
    }
}

class Player{
    Coordinate position;
    ArrayList<Coordinate> queue;
    int visited[][];
    int gridSize;
    Player(int size){
        this.gridSize = size;
        this.position = new Coordinate(0,0);
        this.queue = new ArrayList<Coordinate>();
        this.queue.add(this.position);
        visited = new int[size][size];
    	for(int i=0;i<size;i++){
    		for(int j=0;j<size;j++){
    			visited[i][j] = 0;
    		}
    	}
    }
    public void move(){
    	position = queue.get(0);
    	queue.remove(0);
    		//System.out.println("Went to "+player_position.x_coordinate+","+player_position.y_coordinate);
    	if(!isVisited(position)){
    		if(position.x_coordinate < gridSize-1){
		    	queue.add(new Coordinate(position.x_coordinate+1, position.y_coordinate));
		    }
		    if(position.y_coordinate < gridSize-1){
		    	queue.add(new Coordinate(position.x_coordinate, position.y_coordinate+1));
		    }
		    visited[position.x_coordinate][position.y_coordinate]=1;
    	}
    }
    public boolean isVisited(Coordinate position){
    	if(visited[position.x_coordinate][position.y_coordinate] == 1){
    		return true;
    	}else{
    		return false;
    	}
    }
    public void printPlayerPosition(){
        System.out.println("Player is at"+this.position.x_coordinate+","+this.position.y_coordinate);
    }
}