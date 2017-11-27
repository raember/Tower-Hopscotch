package ch.zhaw.psit.towerhopscotch.models.tiles;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

public class StartTile extends Tile {

    StartTile(int id) {
        super("dirt", id);
    }

    @Override
    public boolean isPath() {
        return true;
    }
}