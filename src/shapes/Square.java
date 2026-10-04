package shapes;

import renderers.Renderer;

public class Square extends Shape {
    private final int side;

    public Square(String id, int side, Renderer renderer) {
        super(id, renderer);
        this.side = side;
    }

    @Override
    public String execute() {
        return getRenderer().renderSquare(side);
    }
}
