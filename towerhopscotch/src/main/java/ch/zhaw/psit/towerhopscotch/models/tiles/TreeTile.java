package ch.zhaw.psit.towerhopscotch.models.tiles;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

public class TreeTile extends Tile {

    TreeTile(int id) {
        super(Assets.tiles.get("tree"), id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
