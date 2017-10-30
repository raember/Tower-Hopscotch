package ch.zhaw.psit.towerhopscotch.models.tiles;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

class StoneTile extends Tile {

    StoneTile(int id) {
        super(Assets.tiles.get("stone"), id);
    }
}
