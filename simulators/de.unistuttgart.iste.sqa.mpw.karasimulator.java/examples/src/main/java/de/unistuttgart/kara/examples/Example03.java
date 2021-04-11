package de.unistuttgart.kara.examples;

import de.unistuttgart.kara.main.SimpleKaraGame;

public class Example03 extends SimpleKaraGame {
    public static void main(final String[] args) {
        createInstance(Example03.class);
    }

    /**
     * Another kara program, which tests the loading of another world.
     */
    @Override
    protected void run() {
        displayInNewGameWindow();
        loadWorldFromResourceFile("de.unistuttgart.kara.worlds/example03.ter");
        startGame();

        kara.turnRight();
        kara.move();
    }

}
