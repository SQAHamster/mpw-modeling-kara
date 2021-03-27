package de.unistuttgart.kara.examples;

import de.unistuttgart.kara.main.SimpleKaraGame;

public class Example02 extends SimpleKaraGame {
    public static void main(final String[] args) {
        createInstance(Example02.class);
    }

    @Override
    protected String getWorldFile() {
        return "/worlds/example02.ter";
    }

    /**
     * Another kara program.
     */
    @Override
    protected void run() {
        displayInNewGameWindow();

        kara.turnRight();

        for (int i = 0; i < 3; i++) {
            kara.move();
            kara.putLeaf();
        }

        rotate180Degrees();

        for (int i = 0; i < 3; i++) {
            kara.removeLeaf();
            kara.move();
        }
    }

    private void rotate180Degrees() {
        kara.turnLeft();
        kara.turnLeft();
    }
}
