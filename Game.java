import java.util.*;

class Game{
	public static void main(String[] args){
	    GameHelper game = new GameHelper(10);
	    game.startGame(9,8);
	}
}

class GameHelper{
    int gridSize;
    Grid grid;
    GameHelper(int size){
        this.gridSize = size;
        this.grid = new Grid(size);
    }
    public void startGame(int ball_x_coordinate, int ball_y_coordinate, int player_x_coordinate, int player_y_coordinate){
        Ball ball = new Ball(new Coordinate(ball_x_coordinate, ball_y_coordinate));
        this.grid.placeBall(ball);
        Player player = new Player(new Coordinate(player_x_coordinate, player_y_coordinate));
        player.travel(this.grid);
    }
    public void startGame(int ball_x_coordinate, int ball_y_coordinate){
        Ball ball = new Ball(new Coordinate(ball_x_coordinate, ball_y_coordinate));
        this.grid.placeBall(ball);
        Player player = new Player(new Coordinate(0,0));
        player.travel(this.grid);
    }
}
class Grid{
    int size;
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
}

class Player{
    Coordinate position;
    Player(Coordinate position){
        this.position = position;
    }
    public void travel(Grid grid){
    	ArrayList<Coordinate> queue = new ArrayList<Coordinate>();
    	int visited[][] = new int[grid.size][grid.size];
    	for(int i=0;i<grid.size;i++){
    		for(int j=0;j<grid.size;j++){
    			visited[i][j] = 0;
    		}
    	}
    	queue.add(this.position);
    	while(!queue.isEmpty()){
    		Coordinate player_position = queue.get(0);
    		queue.remove(0);
    		//System.out.println("Went to "+player_position.x_coordinate+","+player_position.y_coordinate);
    		if(grid.grid[player_position.x_coordinate][player_position.y_coordinate] == 1){
    			System.out.println("Found ball at "+player_position.x_coordinate+","+player_position.y_coordinate);
    			break;
    		}else{
    			if(visited[player_position.x_coordinate][player_position.y_coordinate]!=1){
		    		if(player_position.x_coordinate < grid.size-1){
		    			queue.add(new Coordinate(player_position.x_coordinate+1,player_position.y_coordinate));
		    		}
		    		if(player_position.y_coordinate < grid.size-1){
		    			queue.add(new Coordinate(player_position.x_coordinate,player_position.y_coordinate+1));
		    		}
		    		visited[player_position.x_coordinate][player_position.y_coordinate]=1;
		    	}
	    	}
    	}
    }
}
