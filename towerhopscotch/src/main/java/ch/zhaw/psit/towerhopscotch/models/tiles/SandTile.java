package ch.zhaw.psit.towerhopscotch.models.tiles;

class SandTile extends Tile {

    SandTile(int id) {
        super("sand", id);
    }

    @Override
    public boolean isTowerPlaceable() {
        return true;
    }
}
