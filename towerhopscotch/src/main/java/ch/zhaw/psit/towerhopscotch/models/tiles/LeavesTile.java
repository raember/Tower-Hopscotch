package ch.zhaw.psit.towerhopscotch.models.tiles;

class LeavesTile extends Tile {

    LeavesTile(int id) {
        super("leaves", id);
    }

    @Override
    public boolean isTowerPlaceable() {
        return true;
    }
}
