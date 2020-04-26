package jmichel;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;

public class Midi {
    final String midiFile = "/home/m/Downloads/oxygene-midi/oxygene-04.mid";
    public Pattern getPattern() throws IOException, InvalidMidiDataException {
        return MidiFileManager.loadPatternFromMidi(new File(midiFile));
    }
}
