import javax.swing.*;
import java.awt.*;

/**
 * Created by Tom on 2/25/15.
 */
public class Window extends JFrame{

    private Game gamePanel;
    private JTextArea scoreArea;

    public Window(){
        makeWindow();
    }

    public void makeWindow(){
        //items

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel scoreBoard = new JPanel();
        scoreArea = new JTextArea();

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

        Dimension d = new Dimension(screenWidth / 4, screenHeight);

        //visual purposes only!
        mainPanel.setBackground(Color.black);
        gamePanel.setBackground(Color.LIGHT_GRAY);
        scoreBoard.add(scoreArea);

        scoreArea.setText("First please enter your name. Then to start the game you can either make the cars move one leg of the race at a time or you can have the cars simulate the entire race. To start the simulation press one of the two buttons. If you press the move one leg button you will need to press it again after the cars have all finished the leg.");
        scoreArea.setLineWrap(true);
        scoreArea.setWrapStyleWord(true);
        Container c = getContentPane();
        c.add(scoreBoard);

        //setting up the mainPanel
        mainPanel.setSize(screenWidth, screenHeight);
        mainPanel.setPreferredSize(new Dimension(screenWidth, screenHeight));
        mainPanel.add(scoreBoard, BorderLayout.EAST);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        scoreBoard.setPreferredSize(d);
        scoreArea.setPreferredSize(d);

        this.pack();
        this.revalidate();


        //gamePanel.setPlayerName(nameInputHere); 
    }

    public Game getGamePanel(){
        return gamePanel;
    }

    public JTextArea getScoreArea(){
        return scoreArea;
    }
}
