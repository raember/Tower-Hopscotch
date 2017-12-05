package ch.zhaw.psit.towerhopscotch.models.tiles;

class PathTile extends Tile {

    PathTile(int id) {
        super("dirt", id);
    }

    @Override
    public boolean isPath() {
        return true;
    }
}
