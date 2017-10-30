package ch.zhaw.psit.towerhopscotch.models.tiles;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

public class StartTile extends Tile {

    StartTile(int id) {
        super(Assets.tiles.get("start"), id);
    }

    @Override
    public boolean isPath() {
        return true;
    }
}