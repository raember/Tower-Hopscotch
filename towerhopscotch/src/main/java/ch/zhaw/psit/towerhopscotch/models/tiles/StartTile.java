package ch.zhaw.psit.towerhopscotch.models.tiles;

public class StartTile extends Tile {

    StartTile(int id) {
        super("dirt", id);
    }

    @Override
    public boolean isPath() {
        return true;
    }
}