package ch.zhaw.psit.towerhopscotch.models.tiles;

class LongGrassTile extends Tile {

    LongGrassTile(int id) {
        super("long_grass", id);
    }

    @Override
    public boolean isTowerPlaceable() {
        return true;
    }
}
