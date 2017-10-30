package ch.zhaw.psit.towerhopscotch.models.tiles;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

class DirtTile extends Tile {

    DirtTile(int id) {
        super(Assets.tiles.get("dirt"), id);
    }

    @Override
    public boolean isPath() {
        return true;
    }
}
