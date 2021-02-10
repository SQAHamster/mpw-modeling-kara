package de.unistuttgart.kara.util;

import de.unistuttgart.iste.sqa.mpw.framework.mpw.Direction;

import static de.unistuttgart.iste.sqa.mpw.framework.mpw.Direction.*;

public class DirectionTestHelper {
    public static Direction toDirection(char c) {
        switch (c) {
            case '>':
                return EAST;
            case '<':
                return WEST;
            case '^':
                return NORTH;
            case 'v':
                return SOUTH;
        }
        throw new IllegalArgumentException("invalid direction: " + c);
    }

    public static char toDirection(Direction d) {
        switch (d) {
            case WEST: {
                return '<';
            }
            case EAST: {
                return '>';
            }
            case NORTH: {
                return '^';
            }
            case SOUTH: {
                return 'v';
            }
        }
        return '-';
    }

}
