package sweeper;

import java.awt.*;

public class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState state;


    public GameState getState() {
        return state;
    }

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {
        bomb.start();
        flag.stat();
        state = GameState.PLAYED;

    }

    public Box getBox(Coord coord) {
        if (flag.get(coord) == Box.OPENED) return bomb.get(coord);
        else return flag.get(coord);
    }

    public void pressLeftButton(Coord coord) {
        openBox(coord);
        checkWinner();
    }

    private void checkWinner() {
        if (state == GameState.PLAYED)
            if (flag.getCountOfClosesBoxes() == bomb.getTotalBombs())
                state = GameState.WINNER;
    }

    private void openBox(Coord coord) {
        switch (flag.get(coord)) {
            case OPENED:
            case FLAGED:
                return;
            case CLOSED:
                switch (bomb.get(coord)) {
                    case ZERO:
                        openBoxesAround(coord);
                        return;
                    case BOMB:
                        openBomb(coord);
                        return;
                    default:
                        flag.setOpenedToBox(coord);
                        return;
                }
        }
    }

    private void openBomb(Coord bombes) {
        state = GameState.BOMBED;
        flag.setBombetToBox(bombes);
        for (Coord coord: Ranges.getAllCoords())
            if (bomb.get(coord) == Box.BOMB)
                flag.setOpenedToClosedBombBox(coord);
        else
            flag.setNoBombToFlagedSafeBox(coord);
    }

    private void openBoxesAround(Coord coord) {
        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord))
            openBox(around);

    }

    public void pressRighttButton(Coord coord) {
        flag.tuggleFlagedToBox(coord);
    }
}
