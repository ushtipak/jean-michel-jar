package jmichel;


import org.jfugue.pattern.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        Midi midi = new Midi();
        Pattern notes = null;
        try {
            notes = midi.getPattern();
        } catch (IOException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
        Fiddle fiddle = new Fiddle();
        fiddle.play(notes);
    }
}
