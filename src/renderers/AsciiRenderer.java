package renderers;

public class AsciiRenderer implements Renderer {
    @Override
    public String renderCircle(int radius) {
        return "ASCII text: (o) circle radius=" + radius;
    }

    @Override
    public String renderSquare(int side) {
        return "ASCII text: [#] square side=" + side;
    }
}
