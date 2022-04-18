package cz.mendelu.pjj.Alhambra;

import java.util.Objects;

public class Coordinate {
    public final char x;
    public final int y;

    public Coordinate(char x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @author xmoravc1
     * @version etapa 3
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }
    /**
     * @author xmoravc1
     * @version etapa 3
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    /**
     * @author xmoravc1
     * @version etapa 3
     */
    @Override
    public String toString() {
        return "[" +
                "x=" + x +
                ", y=" + y +
                ']';
    }
}
