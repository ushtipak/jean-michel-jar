package jmichel;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;


public class Fiddle {
    public void play(Pattern pattern) {
        Player player = new Player();
        player.play(pattern);
    }
}
