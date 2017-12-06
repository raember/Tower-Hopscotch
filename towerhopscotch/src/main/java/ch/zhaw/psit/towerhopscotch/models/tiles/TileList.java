package ch.zhaw.psit.towerhopscotch.models.tiles;

/**
 * Each tile is initialized once in the static instance list.
 * With method getTile a tile instance can be gotten via its ID.
 */
public class TileList {

    public static Tile[] instanceList = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile pathTile = new PathTile(1);
    public static Tile longGrassTile = new LongGrassTile(2);
    public static Tile treeTile = new TreeTile(3);
    public static Tile leavesTile = new LeavesTile(4);
    public static Tile stoneTile = new StoneTile(5);
    public static Tile sandTile = new SandTile(6);
    public static Tile startTile = new StartTile(7);
    public static Tile fortressTile = new FortressTile(8);
    public static Tile templeTile = new TempleTile(9);
    public static Tile unknownTile = new UnknownTile(255);

    /**
     * Get the tile with the id
     * @param id ID
     * @return the tile with de id
     */
    public static Tile getTile(int id) {
        Tile tile = instanceList[id];
        if (tile == null)
            return unknownTile;
        return tile;
    }
}
