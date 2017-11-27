package ch.zhaw.psit.towerhopscotch.models.tiles;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

class PathTile extends Tile {

    PathTile(int id) {
        super("dirt", id);
    }

    @Override
    public boolean isPath() {
        return true;
    }
}
