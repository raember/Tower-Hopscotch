package ch.zhaw.psit.towerhopscotch.models.tiles;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

public class FortressTile extends Tile {

    FortressTile(int id) {
        super(Assets.tiles.get("fortress"), id);
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
