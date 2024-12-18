package challenges.year2024.day6;

import helper.Coordinate;
import helper.Direction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Guard {
    private Coordinate position;
    private Direction direction;
    private final Coordinate initialPosition;
    private final Direction initialDirection;
    private final Set<Coordinate> visited = new HashSet<>();
    private final Map<Coordinate, Direction> coordinateDirectionMap = new HashMap<>();
    private Grid grid;

    public Guard(Coordinate position, Direction direction) {
        initialPosition = position;
        initialDirection = direction;
        this.position = position;
        this.direction = direction;
        visited.add(position);
        coordinateDirectionMap.put(position, direction);
    }

    public Set<Coordinate> getVisited() {
        return visited;
    }

    public void run(Grid grid) {
        reset();
        this.grid = grid;
        while (true) {
            TileType nextTile = getTileAhead();
            if (nextTile == null) {
                return;
            }

            while (nextTile.equals(TileType.BLOCKED)) {
                handleTurning(nextTile);
                nextTile = getTileAhead();
            }

            Coordinate ahead = getCoordinateAhead();
            visited.add(ahead);
            position = ahead;
        }
    }

    public boolean runAndReturnIfCycle(Grid grid) {
        reset();
        this.grid = grid;
        while (true) {
            TileType nextTile = getTileAhead();
            if (nextTile == null) {
                return false;
            }

            while (nextTile.equals(TileType.BLOCKED)) {
                handleTurning(nextTile);
                nextTile = getTileAhead();
            }

            Coordinate ahead = getCoordinateAhead();
            if (coordinateDirectionMap.containsKey(ahead) && coordinateDirectionMap.get(ahead).equals(direction)) {
                return true;
            }
            coordinateDirectionMap.put(ahead, direction);
            position = ahead;
        }
    }

    private void handleTurning(TileType nextTile) {
        if (nextTile == TileType.BLOCKED) {
            turn();
        }
    }

    private void turn() {
        switch (direction) {
            case UP -> direction = Direction.RIGHT;
            case DOWN -> direction = Direction.LEFT;
            case LEFT -> direction = Direction.UP;
            case RIGHT -> direction = Direction.DOWN;
        }
    }

    private TileType getTileAhead() {
        Coordinate ahead = getCoordinateAhead();
        return grid.get(ahead);
    }

    private Coordinate getCoordinateAhead() {
        return switch (direction) {
            case UP -> position.up();
            case DOWN -> position.down();
            case LEFT -> position.left();
            case RIGHT -> position.right();
        };
    }

    private void reset() {
        visited.clear();
        coordinateDirectionMap.clear();
        position = initialPosition;
        direction = initialDirection;
    }
}