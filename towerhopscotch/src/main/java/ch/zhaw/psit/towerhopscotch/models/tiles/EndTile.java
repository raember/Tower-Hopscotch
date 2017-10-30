package ch.zhaw.psit.towerhopscotch.models.tiles;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

public class EndTile extends Tile {

    EndTile(int id) {
        super(Assets.tiles.get("end"), id);
    }

    @Override
    public boolean isPath() {
        return true;
    }
}
