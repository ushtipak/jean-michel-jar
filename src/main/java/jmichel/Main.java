package jmichel;

public class Main {
    public static void main(String[] args) {
        Midi midi = new Midi();
        String notes = midi.getNotes();
        System.out.println(notes);
    }
}
