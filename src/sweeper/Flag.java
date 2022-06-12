package sweeper;

class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;

    void stat() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;

    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }


    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfClosedBoxes--;
    }

    private void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);

    }

    private void setCloseToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);

    }

    public void tuggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGED:
                setCloseToBox(coord);
                break;
            case CLOSED:
                setFlagedToBox(coord);
                break;
        }
    }

    int getCountOfClosesBoxes() {
        return countOfClosedBoxes;
    }

    void setBombetToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenedToClosedBombBox(Coord coord) {
        if (flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord,Box.OPENED);
    }

    void setNoBombToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGED)
            flagMap.set(coord,Box.NOBOMB);
    }
}
