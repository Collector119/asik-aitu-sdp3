import renderers.AsciiRenderer;
import renderers.RasterRenderer;
import renderers.Renderer;
import renderers.VectorRenderer;
import shapes.Circle;
import shapes.Shape;
import shapes.Square;

public class Main {
    private static final String CIRCLE_ID = "circle-1";
    private static final String SQUARE_ID = "square-1";
    private static final int CIRCLE_RADIUS = 2;
    private static final int SQUARE_SIDE = 3;

    private static int passedChecks = 0;
    private static int totalChecks = 0;

    public static void main(String[] args) {
        if (args.length == 0 || !args[0].equals("--demo")) {
            System.out.println("Usage: java -cp out Main --demo");
            return;
        }

        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();
        Renderer ascii = new AsciiRenderer();

        checkCombination("T1", new Circle(CIRCLE_ID, CIRCLE_RADIUS, vector), vector,
                "VECTOR path: circle radius=2");
        checkCombination("T2", new Circle(CIRCLE_ID, CIRCLE_RADIUS, raster), raster,
                "RASTER pixels: circle radius=2");
        checkCombination("T3", new Square(SQUARE_ID, SQUARE_SIDE, vector), vector,
                "VECTOR path: square side=3");
        checkCombination("T4", new Square(SQUARE_ID, SQUARE_SIDE, raster), raster,
                "RASTER pixels: square side=3");
        checkRuntimeSwitch();
        checkCombination("T6", new Circle(CIRCLE_ID, CIRCLE_RADIUS, ascii), ascii,
                "ASCII text: (o) circle radius=2");
        checkCombination("T7", new Square(SQUARE_ID, SQUARE_SIDE, ascii), ascii,
                "ASCII text: [#] square side=3");

        System.out.println("SUMMARY: " + passedChecks + "/" + totalChecks + " PASS");
    }

    private static void checkCombination(String checkId, Shape shape, Renderer renderer, String expected) {
        String actual = shape.execute();
        String classes = shape.getClass().getSimpleName() + " + " + renderer.getClass().getSimpleName();
        String details = classes + " | result=" + actual;
        if (!actual.equals(expected)) {
            details = details + " | expected=" + expected;
        }
        printCheck(checkId, actual.equals(expected), details);
    }

    private static void checkRuntimeSwitch() {
        Circle circle = new Circle(CIRCLE_ID, CIRCLE_RADIUS, new VectorRenderer());
        Circle original = circle;
        String idBefore = circle.getId();
        int radiusBefore = circle.getRadius();

        String before = circle.execute();
        circle.setImplementation(new RasterRenderer());
        String after = circle.execute();

        boolean sameObject = circle == original;
        boolean stateUnchanged = circle.getId().equals(idBefore) && circle.getRadius() == radiusBefore;
        String expectedBefore = "VECTOR path: circle radius=2";
        String expectedAfter = "RASTER pixels: circle radius=2";
        boolean resultsCorrect = before.equals(expectedBefore) && after.equals(expectedAfter);

        String details = "Circle: VectorRenderer -> RasterRenderer | sameObject=" + sameObject
                + " | stateUnchanged=" + stateUnchanged
                + "\n   before=" + before + " | after=" + after;
        if (!resultsCorrect) {
            details = details + "\n   expected before=" + expectedBefore + " | after=" + expectedAfter;
        }
        printCheck("T5", sameObject && stateUnchanged && resultsCorrect, details);
    }

    private static void printCheck(String checkId, boolean passed, String details) {
        totalChecks++;
        if (passed) {
            passedChecks++;
        }
        System.out.println(checkId + " " + (passed ? "PASS" : "FAIL") + " | " + details);
    }
}
