package entity.weapon;

import static main.external.Audio.playGameEffect;
import entity.Item;
import javafx.scene.canvas.GraphicsContext;
import main.Resources;
import main.level.Level;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * No entry sign weapon class.
 *
 * @author Harry Boyce
 * @author Bryan Kok
 * @author Dafydd -Rhys Maund
 */
public class NoEntrySign extends Item {

    /**
     * Constructor
     */
    public NoEntrySign() {
        setEntityType(EntityType.ITEM);
        setEntityName("NoEntry");
        setImage(Resources.getEntityImage("no-entry-sign-5"));
        setHp(10);
        setDamage(0);
        setRange(1);
        setType(TYPE.NO_ENTRY);
        setOffsetY(5);
    }

    /**
     * Instantiates item.
     *
     * @return new no entry sign item
     */
    @Override
    public Item createNewInstance() {
        return new NoEntrySign();
    }

    /**
     * Plays sound effect.
     */
    @Override
    public void playSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playGameEffect(Resources.getGameAudio("oof"));
    }

    /**
     * Prevents rats from walking into its tile until broken.
     *
     * @param level gets tiles
     * @param gc    unused attribute
     */
    public void activate(Level level, GraphicsContext gc) {
        switch (getHp()) {
            case 8 -> setImage(Resources.getEntityImage("no-entry-sign-4"));
            case 6 -> setImage(Resources.getEntityImage("no-entry-sign-3"));
            case 4 -> setImage(Resources.getEntityImage("no-entry-sign-2"));
            case 2 -> setImage(Resources.getEntityImage("no-entry-sign-1"));
            case 0 -> {
                level.getTiles()[getCurrentPosY()][getCurrentPosX()].removeEntityFromTile(this);
                level.getItems().remove(this);
            }
        }
    }

}
