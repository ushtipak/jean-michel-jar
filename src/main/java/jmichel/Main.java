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

            try {
                Midi midi = new Midi();
                Pattern notes = midi.getPattern(midiFile);
                System.out.println("notes: " + notes);

                String theme = midi.getThemeFromO4(notes);
                System.out.println("theme: " + theme);


                Pattern pattern = new Pattern("R/0.04843749999999858 D5/0.5833333333333334 R/0.5078125 A5/0.1234375 R/0.02083333333333215 G5/0.0484375 R/0.0442708333333357 F5/0.12083333333333333 R/0.0390625 C5/0.4817708333333333 R/0.11093749999999858 A5/0.140625 R/0.014062500000001421 G5/0.052083333333333336 R/0.04322916666666643 F5/0.11197916666666667 R/0.02708333333333357 C5/0.45208333333333334 R/0.16145833333333215 R/0.16666666666666607 G5T. R/0.036458333333333925 G5I R/0.040104166666667496 G5/0.203125 R/0.04843749999999858 C5/0.5833333333333334 RH BB5Q R/0.16666666666666607 G5T. R/0.036458333333333925 EB5I R/0.040104166666667496 G5/0.203125 R/0.04843749999999858 C5/0.5833333333333334 RH BB5Q R/0.16666666666666607 G5T. R/0.036458333333333925 G5I R/0.040104166666667496 G5/0.203125 R/0.04843749999999858 C5/0.5833333333333334 RH BB5Q R/0.16666666666666607 G5T. R/0.036458333333333925 EB5I R/0.040104166666667496 A5/0.203125 R/0.04843749999999858 C5/0.5833333333333334 RH BB5Q R/0.16666666666666607 G5T. R/0.036458333333333925 G5I R/0.040104166666667496 G5/0.203125 R/0.04843749999999858 C5/0.5833333333333334 RH BB5Q R/0.16666666666666607 A5T. R/0.036458333333333925 EB5I R/0.040104166666667496 G5/0.203125 R/0.04843749999999858 C5/0.5833333333333334 RH BB5Q R/0.16666666666666607 G5T.");
                midi.play(pattern);

            } catch (IOException | InvalidMidiDataException e) {
                e.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
