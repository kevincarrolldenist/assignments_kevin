package org.assignment5;

public class MusicPlayerApp {

    public static void main(String[] args) {
        System.out.println("--- Demonstrating Interface Default Method ---");

        MP3Player mp3Player = new MP3Player();
        WAVPlayer wavPlayer = new WAVPlayer();

        System.out.println("\nMP3 Player Actions:");
        mp3Player.play("song.mp3");
        mp3Player.stop(); // Calls the default method

        System.out.println("\nWAV Player Actions:");
        wavPlayer.play("audio.wav");
        wavPlayer.stop(); // Calls the default method
    }
}

interface MusicPlayer {
    void play(String fileName);

    default void stop() {
        System.out.println("Music stopped.");
    }
}

class MP3Player implements MusicPlayer {
    @Override
    public void play(String fileName) {
        System.out.println("Playing MP3 file: " + fileName);
    }
}

class WAVPlayer implements MusicPlayer {
    @Override
    public void play(String fileName) {
        System.out.println("Playing WAV file: " + fileName);
    }
}
