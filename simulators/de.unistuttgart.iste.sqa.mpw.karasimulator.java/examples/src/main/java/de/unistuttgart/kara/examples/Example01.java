package de.unistuttgart.kara.examples;

import de.unistuttgart.kara.main.SimpleKaraGame;

public class Example01 extends SimpleKaraGame {
    public static void main(final String[] args) {
        createInstance(Example01.class);
    }

    @Override
    protected void run() {
        displayInNewGameWindow();
        kara.move();
        kara.turnRight();
        kara.move();
        kara.removeLeaf();
        kara.turnLeft();
        kara.turnLeft();
        kara.move();
        kara.putLeaf();
        kara.turnLeft();
        kara.move();
        kara.turnRight();
        kara.turnRight();
    }

}
