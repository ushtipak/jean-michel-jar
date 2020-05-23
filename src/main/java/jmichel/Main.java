package jmichel;

import org.apache.commons.cli.*;
import org.jfugue.pattern.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    private static final Logger log;

    static {
        String path = Objects.requireNonNull(Main.class
                .getClassLoader()
                .getResource("logging.properties"))
                .getFile();
        System.setProperty("java.util.logging.config.file", path);
        log = Logger.getLogger("Jean-Michel Jar");
    }

    public static void main(String[] args) {
        Options options = new Options();
        Option input = new Option("m", "midi", true, "midi file");
        input.setRequired(true);
        options.addOption(input);

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            String midiFile = cmd.getOptionValue("midi");
            log.log(Level.FINEST, "midi: " + midiFile);

            try {
                Midi midi = new Midi();
                Pattern notes = midi.getPattern(midiFile);
                log.log(Level.FINEST, "notes: " + notes);

                String theme = midi.getThemeFromO4(notes);
                log.log(Level.FINE, "theme: " + theme);

                Pattern pattern = new Pattern(Markov.andreyUp(theme, 48));
                log.log(Level.INFO, "generated: " + pattern);

                String output = String.format("new-oxygene-%s.mid", Instant.now().getEpochSecond());
                log.log(Level.FINEST, "output: " + output);

                midi.save(pattern, output);
                midi.play(pattern);

            } catch (IOException | InvalidMidiDataException e) {
                log.log(Level.SEVERE, e.getMessage(), e);
            }

        } catch (ParseException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }

    }
}
