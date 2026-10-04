# Assignment 3 — Bridge Pattern

| | |
|---|---|
| **Student** | Anarov Daniyal |
| **Group** | SE-2530 |
| **Topic** | A — Drawing (`Shape` / `Renderer`) |
| **Repository** | https://github.com/Collector119/asik-aitu-sdp3 |
| **Base commit (I1/I2)** | `e67f2e5` |

A console application that draws two shapes with three renderers. `Shape` and `Renderer` are two
independent class hierarchies connected by Bridge: every shape holds a reference to a `Renderer`
interface and delegates the actual drawing to it. Rendering is simulated as text.

## Role map

| Role | Class | Source |
|---|---|---|
| Abstraction | `Shape` | `src/shapes/Shape.java` |
| A1 (refined abstraction) | `Circle`, radius 2 | `src/shapes/Circle.java` |
| A2 (refined abstraction) | `Square`, side 3 | `src/shapes/Square.java` |
| Implementor | `Renderer` | `src/renderers/Renderer.java` |
| I1 | `VectorRenderer` | `src/renderers/VectorRenderer.java` |
| I2 | `RasterRenderer` | `src/renderers/RasterRenderer.java` |
| I3 (extension) | `AsciiRenderer` | `src/renderers/AsciiRenderer.java` |
| Client | `Main` | `src/Main.java` |

## Where to look

| What | Location |
|---|---|
| Bridge field | `src/shapes/Shape.java:7` — `private Renderer renderer` |
| `setImplementation(Renderer)` | `src/shapes/Shape.java:18` |
| `execute()` | declared `src/shapes/Shape.java:26`, implemented `src/shapes/Circle.java:18` and `src/shapes/Square.java:14` |
| T5 check | `src/Main.java:55` — `checkRuntimeSwitch()` |

## Build and run

JDK 17 or newer, no other dependencies. From the project folder:

```bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

## Expected results

| Check | Setup | Expected result |
|---|---|---|
| T1 | `Circle` + `VectorRenderer` | `VECTOR path: circle radius=2` |
| T2 | `Circle` + `RasterRenderer` | `RASTER pixels: circle radius=2` |
| T3 | `Square` + `VectorRenderer` | `VECTOR path: square side=3` |
| T4 | `Square` + `RasterRenderer` | `RASTER pixels: square side=3` |
| T5 | one `Circle`, `VectorRenderer` replaced by `RasterRenderer` | `sameObject=true`, `stateUnchanged=true`, before `VECTOR path: circle radius=2`, after `RASTER pixels: circle radius=2` |
| T6 | `Circle` + `AsciiRenderer` | `ASCII text: (o) circle radius=2` |
| T7 | `Square` + `AsciiRenderer` | `ASCII text: [#] square side=3` |

The program ends with `SUMMARY: 7/7 PASS`. The captured output is in `demo-output.txt`.

