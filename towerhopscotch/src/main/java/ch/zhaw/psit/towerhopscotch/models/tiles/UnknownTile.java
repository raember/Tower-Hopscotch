package ch.zhaw.psit.towerhopscotch.models.tiles;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

class UnknownTile extends Tile {

    UnknownTile(int id) {
        super(Assets.tiles.get("unknown"), id);
    }
}
