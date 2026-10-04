package renderers;

public class VectorRenderer implements Renderer {
    @Override
    public String renderCircle(int radius) {
        return "VECTOR path: circle radius=" + radius;
    }

    @Override
    public String renderSquare(int side) {
        return "VECTOR path: square side=" + side;
    }
}
