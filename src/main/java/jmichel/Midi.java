package jmichel;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.Token;
import org.jfugue.player.Player;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;

public class Midi {
    /**
     * Return notes from given midi
     */
    public Pattern getPattern(String midiFile) throws IOException, InvalidMidiDataException {
        return MidiFileManager.loadPatternFromMidi(new File(midiFile));
    }

    /**
     * Extract theme from Oxygene (Part IV)
     */
    public String getThemeFromO4(Pattern notes) {
        StringBuilder builder = new StringBuilder();
        builder.append("C6q ");
        boolean include = false;
        for (Token token : notes.getTokens()) {
            if (token.toString().equals("R/0.16666666666666607")) {
                include = true;
            }
            if (include) {
                builder.append(token);
                builder.append(" ");
            }
            if (token.toString().equals("R/0.16145833333333215")) {
                break;
            }
        }
        return builder.toString();
    }

    /**
     * Save pattern to a MIDI file
     */
    public void save(Pattern pattern, String output) throws IOException {
        MidiFileManager.savePatternToMidi(pattern, new File(output));
    }

    /**
     * Play given pattern within JVM
     */
    public void play(Pattern pattern) {
        Player player = new Player();
        player.play(pattern);
    }

}
