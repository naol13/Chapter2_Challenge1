import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JOptionPane;

public class BouncingTextApplet extends Applet implements Runnable, MouseListener {

    private Thread thread;
    private boolean running = false;

    // Default texts (will be replaced by user input)
    private String text1 = "Player 1";
    private String text2 = "Player 2";

    private int x1 = 50, y1 = 80;
    private int dx1 = 4, dy1 = 3;
    private Color color1 = Color.RED;
    private int bounceCount1 = 0;

    private int x2 = 200, y2 = 180;
    private int dx2 = -3, dy2 = 4;
    private Color color2 = Color.CYAN;
    private int bounceCount2 = 0;

    private static final int MAX_BOUNCES = 15;
    private boolean gameEnded = false;

    private Random random = new Random();
    private String[] fonts = {"Arial", "Courier New", "Times New Roman", "Comic Sans MS", "Verdana"};

    @Override
    public void init() {
        setSize(600, 400);
        setFont(new Font("Arial", Font.BOLD, 26));
        setBackground(Color.BLACK);
        addMouseListener(this);

        // Get user input once at start
        askForTexts();

        resetGame();
    }

    private void askForTexts() {
        try {
            String input1 = JOptionPane.showInputDialog(this, "Enter Text for Player 1:", text1);
            String input2 = JOptionPane.showInputDialog(this, "Enter Text for Player 2:", text2);

            if (input1 != null && !input1.trim().isEmpty()) {
                text1 = input1.trim();
            }
            if (input2 != null && !input2.trim().isEmpty()) {
                text2 = input2.trim();
            }
        } catch (Exception e) {
            // If JOptionPane fails (e.g., in restricted env), keep defaults
        }
    }

    private void resetGame() {
        x1 = 50; y1 = 80;
        x2 = 200; y2 = 180;

        dx1 = 4; dy1 = 3;
        dx2 = -3; dy2 = 4;

        color1 = Color.RED;
        color2 = Color.CYAN;
        bounceCount1 = 0;
        bounceCount2 = 0;

        gameEnded = false;
        running = true;

        if (thread == null || !thread.isAlive()) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void start() {}

    @Override
    public void stop() {
        running = false;
        thread = null;
    }

    @Override
    public void run() {
        while (running) {
            if (!gameEnded) {
                moveText1();
                moveText2();

                if (bounceCount1 >= MAX_BOUNCES || bounceCount2 >= MAX_BOUNCES) {
                    gameEnded = true;
                    running = false;
                }
            }

            repaint();

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        repaint();
    }

    private void moveText1() {
        x1 += dx1;
        y1 += dy1;

        FontMetrics fm = getFontMetrics(getFont());
        int w = fm.stringWidth(text1);
        boolean bounced = false;

        if (x1 <= 0 || x1 + w >= getWidth()) {
            dx1 = -dx1;
            bounced = true;
        }
        if (y1 <= 30 || y1 >= getHeight() - 20) {
            dy1 = -dy1;
            bounced = true;
        }

        if (bounced) {
            bounceCount1++;
            color1 = randomColor();
            changeFont();
        }
    }

    private void moveText2() {
        x2 += dx2;
        y2 += dy2;

        FontMetrics fm = getFontMetrics(getFont());
        int w = fm.stringWidth(text2);
        boolean bounced = false;

        if (x2 <= 0 || x2 + w >= getWidth()) {
            dx2 = -dx2;
            bounced = true;
        }
        if (y2 <= 30 || y2 >= getHeight() - 20) {
            dy2 = -dy2;
            bounced = true;
        }

        if (bounced) {
            bounceCount2++;
            color2 = randomColor();
            changeFont();
        }
    }

    private Color randomColor() {
        return new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
    }

    private void changeFont() {
        setFont(new Font(fonts[random.nextInt(fonts.length)], Font.BOLD, 26));
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color1);
        g.drawString(text1, x1, y1);

        g.setColor(color2);
        g.drawString(text2, x2, y2);

        // Draw counters
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Bounces: " + bounceCount1, 10, 20);
        g.drawString("Bounces: " + bounceCount2, getWidth() - 120, 20);

        if (gameEnded) {
            String winnerText = (bounceCount1 >= MAX_BOUNCES ? text1 : text2) + " WINS!";
            g.setFont(new Font("Arial", Font.BOLD, 36));
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(winnerText)) / 2;
            g.setColor(Color.YELLOW);
            g.drawString(winnerText, x, getHeight() / 2);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            String tryAgain = "Click to Play Again";
            fm = g.getFontMetrics();
            x = (getWidth() - fm.stringWidth(tryAgain)) / 2;
            g.setColor(Color.LIGHT_GRAY);
            g.drawString(tryAgain, x, getHeight() / 2 + 40);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameEnded) {
            resetGame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}