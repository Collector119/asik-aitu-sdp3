package renderers;

public class RasterRenderer implements Renderer {
    @Override
    public String renderCircle(int radius) {
        return "RASTER pixels: circle radius=" + radius;
    }

    @Override
    public String renderSquare(int side) {
        return "RASTER pixels: square side=" + side;
    }
}
