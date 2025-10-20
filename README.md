## BouncingTextApplet

A small Java Applet that animates two pieces of text bouncing around the applet area.
Each text changes color and font on bounces; after a text reaches a predefined number
of bounces, the game ends and displays the winner. The applet prompts for two custom
texts at startup (Player 1 and Player 2) and supports clicking to play again.

### Key file

- `src/BouncingTextApplet.java` — main applet source. Implements animation, mouse
  interaction (click to restart), and user text input via `JOptionPane`.

### Features

- Two independently moving text strings with simple physics (dx/dy) and bounce counting.
- Random color and font change on each bounce.
- Game ends when either text reaches 15 bounces (configurable in code via `MAX_BOUNCES`).
- Click the applet to restart the animation.

### Requirements

- JDK 8 is recommended for easiest applet support. Modern JDKs and browsers have
  removed applet support — use the `appletviewer` from older JDKs or run inside
  an IDE (NetBeans 8.x) which still supports applets.

### Run (NetBeans)

1. Open the project folder in NetBeans.
2. Right-click the project and choose Run (NetBeans will compile and run the applet view).

### Run (command line with appletviewer)

If you have `appletviewer` (usually bundled with older JDKs):

```powershell
# compile
javac -d build/classes src\BouncingTextApplet.java

# then run the provided HTML wrapper (there is a copy under build/)
appletviewer build\BouncingTextApplet.html
```

### Controls

- On startup, two dialogs ask for the text values for Player 1 and Player 2. Leave blank
  or cancel to use the defaults (`Player 1`, `Player 2`).
- Click inside the applet after a game ends to restart.

### Possible improvements

- Add a Swing-based launcher (JFrame) so the app can run without applet infrastructure.
- Expose `MAX_BOUNCES`, initial speeds, and fonts as configurable parameters.

---

## Project Structure

```
BouncingTextApplet/
├── src/
│   ├── BouncingTextApplet.java    # Main applet source code
│   ├── BouncingTextApplet.html    # HTML file for hosting the applet
│   └── BouncingTextApplet.class   # Compiled class (generated)
├── build/
│   ├── classes/
│   │   ├── BouncingTextApplet.class
│   │   └── BouncingTextAppletTest.class
│   └── BouncingTextApplet.html
├── nbproject/                     # NetBeans project files
│   ├── build-impl.xml
│   ├── genfiles.properties
│   ├── project.properties
│   └── project.xml
├── applet.policy                  # Security policy file
├── build.xml                      # Ant build script
├── manifest.mf                    # JAR manifest
├── README.md                      # This file
└── README.txt                     # Critical analysis answers
```

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.

Author: Naol Sisay
