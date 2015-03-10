import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.TimerTask;
import java.util.Timer;
/**
 * Created by Tom on 2/25/15.
 */
public class Window extends JFrame{

	private JPanel scoreBoard;
    private Game gamePanel;
    private JTextArea scoreArea, currentTimes;
    private Timer timer;
    
    public Window(){
        makeWindow();
    }

    /**
     * initializes all of the gui elements to display on the screen
     * and sets their sizes, borders, and text, etc.
     */
    public void makeWindow(){
        // set title
        setTitle("The Candy Land Racing Game");
      
        //items
        JPanel mainPanel = new JPanel(new BorderLayout());
        scoreBoard = new JPanel();
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
        //textfield dimension
        Dimension dTF = new Dimension(200, 50);
        Dimension dSA = new Dimension(screenWidth / 4, screenHeight / 2);
        
        // create title
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        JLabel titleLabel = new JLabel("Candy Land");
        titleLabel.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.white);
        
        //creating a textfield to enter the player's name
        JTextField nameField = new JTextField("Enter name:", 10);
        nameField.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        nameField.setBackground(Color.white);
        nameField.addFocusListener((FocusListener) new NameFocuser());
        nameField.addActionListener(new NameAction());
        nameField.setPreferredSize(dTF);
        
        //creating a text area to display the time between the cars current locations
        timer = new Timer();
        timer.scheduleAtFixedRate(new Update(), 250, 250);
        Car[] cars = gamePanel.getCars();
        currentTimes = new JTextArea("" + cars[0].getDriver().getName() +" " + cars[0].getTime()+ " seconds\n"
        		+ cars[1].getDriver().getName() +" " + cars[1].getTime()+ " seconds\n"
        		+ cars[2].getDriver().getName() +" " + cars[2].getTime()+ " seconds\n"
        		+ cars[3].getDriver().getName() +" " + cars[3].getTime()+ " seconds");
        currentTimes.setLineWrap(true);
        currentTimes.setWrapStyleWord(true);
        currentTimes.setEditable(false);
        currentTimes.setPreferredSize(dSA);
        currentTimes.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        getContentPane().setBackground(new Color(198, 172, 127));

        //visual purposes only!
        mainPanel.setBackground(Color.black);
        gamePanel.setBackground(new Color(198, 172, 127));
        scoreBoard.setBackground(new Color(198, 172, 127));
        scoreBoard.add(titleLabel);
        scoreBoard.add(scoreArea);
        scoreBoard.add(nameField);
        scoreBoard.add(currentTimes);
        
        

        scoreArea.setText("First please enter your name.\n\nThen to start the game you can either make the cars move one leg of the race at a time or you can have the cars simulate the entire race.\n\nTo start the simulation press one of the two buttons. If you press the move one leg button you will need to press it again after the cars have all finished the leg.");
        scoreArea.setLineWrap(true);
        scoreArea.setWrapStyleWord(true);
        scoreArea.setEditable(false);
        //
        scoreArea.setPreferredSize(dSA);
        
        // add border and padding around text
        scoreArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        
        Container c = getContentPane();
        c.add(scoreBoard);
        
        
        //setting up the mainPanel
        mainPanel.setSize(screenWidth, screenHeight);
        mainPanel.setPreferredSize(new Dimension(screenWidth, screenHeight));
        mainPanel.add(scoreBoard, BorderLayout.EAST);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        scoreBoard.setPreferredSize(d);
        
        
        this.pack();
        this.revalidate();
        
        //waits for the game to be finished to print out the winner
        while(true){
            if(getGamePanel().raceFinished()){
                getScoreArea().setText(getGamePanel().checkWinner());
            }
            if(gamePanel.getRestartPressed()){
                getGamePanel().setRestartPressed(false);
                dispose();
                Window w = new Window();
                break;
            }

        } 
    }

    public Game getGamePanel(){
        return gamePanel;
    }

    public JTextArea getScoreArea(){
        return scoreArea;
    }
    
    /**
     * this class makes it so that enter name shows up in the name entering text field
     * and once you click on it enter name goes away
     * @author Brendan
     *
     */
    private class NameFocuser implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			JTextField nameField = (JTextField)e.getSource();
			if(nameField.getText().trim().equals("Enter name:"))
		           nameField.setText("");
		        else{
		           //do nothing
		        }
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			JTextField nameField = (JTextField)e.getSource();
			if(nameField.getText().trim().equals(""))
		           nameField.setText("Enter name:");
		        else{
		           //do nothing
		        }
		}
    	
    }
    
    /**
     * takes whatever the player entered and makes that string their name
     * also once enter has been pressed this text field will be removed
     * @author Brendan
     *
     */
    private class NameAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField nameField = (JTextField)e.getSource();
			
			gamePanel.setPlayerName(nameField.getText());
			
			scoreBoard.remove(nameField);
			revalidate();
			repaint();
			
		}
    }
    
    /**
     * the timer object calls this method every quarter of a second to
     * update the gui to show how many seconds a car has taken to get to its next location
     * @author Brendan
     *
     */
    private class Update extends TimerTask{

		@Override
		public void run() {
			Car[] cars = gamePanel.getCars();
	    	currentTimes.setText("" + cars[0].getDriver().getName() +" " + cars[0].getTime()+ " seconds\n"
	        		+ cars[1].getDriver().getName() +" " + cars[1].getTime()+ " seconds\n"
	        		+ cars[2].getDriver().getName() +" " + cars[2].getTime()+ " seconds\n"
	        		+ cars[3].getDriver().getName() +" " + cars[3].getTime()+ " seconds");
	    	if(getGamePanel().raceFinished()){
	    		currentTimes.setText("" + cars[0].getDriver().getName() +" " + cars[0].getTotalTime()+ " seconds\n"
		        		+ cars[1].getDriver().getName() +" " + cars[1].getTotalTime()+ " seconds\n"
		        		+ cars[2].getDriver().getName() +" " + cars[2].getTotalTime()+ " seconds\n"
		        		+ cars[3].getDriver().getName() +" " + cars[3].getTotalTime()+ " seconds");
	    		timer.cancel();
	    	}
	    	currentTimes.revalidate();
	    	currentTimes.repaint();
			
		}
    	
    }
}
