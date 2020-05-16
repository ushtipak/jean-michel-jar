package jmichel;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;

public class Midi {
    public Pattern getPattern(String midiFile) throws IOException, InvalidMidiDataException {
        return MidiFileManager.loadPatternFromMidi(new File(midiFile));
    }
}
