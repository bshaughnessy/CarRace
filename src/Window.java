import javax.swing.*;
import java.awt.*;

/**
 * Created by Tom on 2/25/15.
 */
public class Window extends JFrame{

    private Game gamePanel;

    public Window(){
        makeWindow();
    }

    public void makeWindow(){
        //items
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel scoreBoard = new JPanel();

        // these dimensions can change-- just there to get something working
        gamePanel = new Game(500, 500);

        //setting the frame
        this.setLayout(new FlowLayout());
        int screenWidth = 800;
        int screenHeight = 600;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        //this.setSize(screenWidth, screenHeight);
        this.add(mainPanel);

        //visual purposes only!
        mainPanel.setBackground(Color.black);
        scoreBoard.setBackground(Color.blue);

        Container c = getContentPane();
        c.add(scoreBoard);

        //setting up the mainPanel
        mainPanel.setSize(screenWidth, screenHeight);
        mainPanel.setPreferredSize(new Dimension(screenWidth, screenHeight));
        mainPanel.add(scoreBoard, BorderLayout.EAST);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        scoreBoard.setPreferredSize(new Dimension(screenWidth / 4, screenHeight));

        this.pack();
        this.revalidate();
    }
}
