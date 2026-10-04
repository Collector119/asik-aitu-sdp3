package shapes;

import renderers.Renderer;

public abstract class Shape {
    private final String id;
    private Renderer renderer;

    protected Shape(String id, Renderer renderer) {
        this.id = id;
        this.renderer = renderer;
    }

    public String getId() {
        return id;
    }

    public void setImplementation(Renderer renderer) {
        this.renderer = renderer;
    }

    protected Renderer getRenderer() {
        return renderer;
    }

    public abstract String execute();
}
