package tile;

import entity.Entity;
import entity.Item;
import entity.rat.Rat;
import java.util.Random;

/**
 * Main
 *
 * @author Dafydd Maund (2003900)
 */
public class Movement {

    private static int random;
    public static Rat rat;
    public static Tile[][] tiles;
    public static Tile current;
    public static int curX;
    public static int curY;

    private static int generateRandom() {
        return new Random().nextInt((1) + 1);
    }

    public static void tryHorizontal(int x, int x2) {
        random = generateRandom();

        if (moveHorizontal(x)) {
            if (random == 1) {
                if (moveVertical(-1)) {
                    if (moveVertical(1)) {
                        if (moveHorizontal(x2)) {
                            System.out.println("couldn't move");
                        }
                    }
                }
            } else {
                if (moveVertical(1)) {
                    if (moveVertical(-1)) {
                        if (moveHorizontal(x2)) {
                            System.out.println("couldn't move");
                        }
                    }
                }
            }
        }
    }

    public static void tryVertical(int y, int y2) {
        random = generateRandom();

        if (moveVertical(y)) {
            if (random == 1) {
                if (moveHorizontal(-1)) {
                    if (moveHorizontal(1)) {
                        if (moveVertical(y2)) {
                            System.out.println("couldn't move");
                        }
                    }
                }
            } else {
                if (moveHorizontal(1)) {
                    if (moveHorizontal(-1)) {
                        if (moveVertical(y2)) {
                            System.out.println("couldn't move");
                        }
                    }
                }
            }
        }
    }

    private static boolean moveHorizontal(int x) {
        if (NoEntry(0, x)) {
            if (tiles[curY][curX + x].isWalkable()) {
                current.removeEntityFromTile(rat);
                tiles[curY][curX].getEntitiesOnTile().remove(rat);
                tiles[curY][curX + x].addEntityToTile(rat);

                rat.setCurrentPosX(curX + x);
                rat.setCurrentPosY(curY);

                if (x == -1) {
                    rat.setRotatedImage(rat.getLeftImage());
                    rat.setDirection(Rat.Direction.LEFT);
                } else {
                    rat.setRotatedImage(rat.getRightImage());
                    rat.setDirection(Rat.Direction.RIGHT);
                }
                return false;
            }
        }

        return true;
    }

    private static boolean moveVertical(int y) {
        if (NoEntry(y, 0)) {
            if (tiles[curY + y][curX].isWalkable()) {
                current.removeEntityFromTile(rat);
                tiles[curY][curX].getEntitiesOnTile().remove(rat);
                tiles[curY + y][curX].addEntityToTile(rat);

                rat.setCurrentPosX(curX);
                rat.setCurrentPosY(curY + y);

                if (y == -1) {
                    rat.setRotatedImage(rat.getUpImage());
                    rat.setDirection(Rat.Direction.UP);
                } else {
                    rat.setRotatedImage(rat.getDownImage());
                    rat.setDirection(Rat.Direction.DOWN);
                }
                return false;
            }
        }
        return true;
    }

    private static boolean NoEntry(int y, int x) {
        for (Entity entity : tiles[curY + y][curX + x].getEntitiesOnTile()) {
            if (entity.getEntityType() == Entity.EntityType.ITEM) {
                Item item = (Item) entity;
                if (item.getType() == Item.TYPE.NO_ENTRY) {
                    return false;
                }
            }
        }
        return true;
    }

}
