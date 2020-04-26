package jmichel;
import org.jfugue.player.Player;


public class Fiddle {
    public void play(String notes) {
        Player player = new Player();
        player.play(notes);
    }
}
