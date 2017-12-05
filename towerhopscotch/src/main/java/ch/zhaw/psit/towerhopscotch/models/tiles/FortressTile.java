package ch.zhaw.psit.towerhopscotch.models.tiles;

public class FortressTile extends Tile {

    FortressTile(int id) {
        super("fortress", id);
    }

    @Override
    public boolean isPath() {
        return true;
    }

    @Override
    public boolean isFortress() {
        return true;
    }
}
