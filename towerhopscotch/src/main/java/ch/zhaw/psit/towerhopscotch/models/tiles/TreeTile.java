package ch.zhaw.psit.towerhopscotch.models.tiles;

public class TreeTile extends Tile {

    TreeTile(int id) {
        super("tree", id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
