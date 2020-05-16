package jmichel;

import org.apache.commons.cli.*;
import org.jfugue.pattern.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        Options options = new Options();

        Option input = new Option("m", "midi", true, "midi file");
        input.setRequired(true);
        options.addOption(input);

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            String midiFile = cmd.getOptionValue("midi");
            System.out.println("midiFile: " + midiFile);

            Midi midi = new Midi();
            Pattern notes = null;
            try {
                notes = midi.getPattern(midiFile);
            } catch (IOException | InvalidMidiDataException e) {
                e.printStackTrace();
            }
            Fiddle fiddle = new Fiddle();
            fiddle.play(notes);


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
