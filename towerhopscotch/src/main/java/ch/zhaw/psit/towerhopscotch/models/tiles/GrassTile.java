package ch.zhaw.psit.towerhopscotch.models.tiles;

class GrassTile extends Tile {

    GrassTile(int id) {
        super("grass", id);
    }

    @Override
    public boolean isTowerPlaceable() {
        return true;
    }
}
