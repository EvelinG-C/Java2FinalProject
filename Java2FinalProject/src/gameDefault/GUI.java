package gameDefault;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI
{
	/* DATA */
	
	// Swing Elements
	private JFrame gameFrame = new JFrame("Dungeon Explorer");
	
	// JLABELS - Project Requirement
	private JLabel playerLabel = new JLabel("Player Information:");
	private JLabel playerInformation = new JLabel();
	
	// JBUTTONS - Project Requirement
	private JButton startButton = new JButton("Start");
	private JButton confirmButton = new JButton("Confirm");
	private JButton dataButton = new JButton("Get Data");
	private JButton restartButton = new JButton("Next Dungeon");
	
	// JTEXTFIELD - Project Requirement
	private JTextField choiceTextField = new JTextField(5);
	
	// JTEXTArea
	static public JTextArea gameDialogueArea = new JTextArea(15,15);
	
	// JSCROLLPANE - Project Requirement
	private JScrollPane scrollPane = new JScrollPane(gameDialogueArea);
	
	// JOPTIONPANE - Project Requirement
	private JOptionPane infoPane = new JOptionPane();
	
	// JPanels
	private JPanel playerPanel = new JPanel();
	private JPanel dialoguePanel = new JPanel();
	private JPanel inputPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	
	// Creating a Menu Bar
	private JMenuBar menuBar = new JMenuBar();
	private JMenu gameMenu = new JMenu("Game");
	private JMenuItem addPlayerItem = new JMenuItem("Add Player");
	private JMenu aboutMenu = new JMenu("About");
	private JMenuItem attributionItem = new JMenuItem("Attribution");
	
	// Object Instantiate
	GameMechanics game = new GameMechanics();
	CreateStatsFile playerFile = new CreateStatsFile();
	Clip clip;
	
	// Variables
	private int clickCount = 0;
	private final int MAX_CLICKS = 1;
	
	// Constructor
	public GUI()
	{
		initialize();
	}

	// Initializes the gameFrame GUI
	private void initialize() {
		gameFrame.setSize(600,600);
		
		// LAYOUT MANAGER WITH JFRAME - Project Requirements
		gameFrame.setLayout(new GridLayout(2,2));
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		
		gameFrame.add(playerPanel);
		gameFrame.add(dialoguePanel);
		gameFrame.add(inputPanel);
		gameFrame.add(buttonPanel);
		
		String backgroundColor = "#858b5f";
		playerPanel.setBackground(Color.decode(backgroundColor));
		dialoguePanel.setBackground(Color.decode(backgroundColor));
		inputPanel.setBackground(Color.decode(backgroundColor));
		buttonPanel.setBackground(Color.decode(backgroundColor));
	
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
		dialoguePanel.setLayout(new BoxLayout(dialoguePanel, BoxLayout.Y_AXIS));
		inputPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		playerPanel.setBorder(new EmptyBorder(10,20,10,10));
		playerPanel.add(playerLabel);
		playerPanel.add(playerInformation);
		
		gameDialogueArea.setLineWrap(true);
		gameDialogueArea.setWrapStyleWord(true);
		gameDialogueArea.setEditable(false);
		dialoguePanel.add(scrollPane);
		dialoguePanel.setBorder(new EmptyBorder(10,10,10,10));
		
		inputPanel.setBorder(new EmptyBorder(110, 15, 10, 15));
		inputPanel.add(choiceTextField);
		inputPanel.add(confirmButton);
		
		buttonPanel.add(startButton);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(dataButton);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(restartButton);
		
		gameMenu.add(addPlayerItem);
		aboutMenu.add(attributionItem);
		menuBar.add(gameMenu);
		menuBar.add(aboutMenu);
		gameFrame.setJMenuBar(menuBar);
		
		playerLabel.setFont(new Font("Display", Font.BOLD, 12));
		
		confirmButton.setEnabled(false);
		dataButton.setEnabled(false);
		restartButton.setEnabled(false);
		
		// Action Event Listeners
		
		addPlayerItem.addActionListener(e -> {
			AddPlayer();
		});
		
		startButton.addActionListener(e -> {
			setStartButton();
		});
		
		dataButton.addActionListener(e -> {
			getPlayerStats();
		});
		
		confirmButton.addActionListener(e -> {
			submitChoice();
		});
		
		restartButton.addActionListener(e -> {
			game.restartGame();
		});
		
		attributionItem.addActionListener(e -> {
			String backgroundMusic = "Background Music - \n"
					+ "Music by <a href=\"https://pixabay.com/users/kaden_cook-28038346/\n"
					+ "?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=251388\">\n"
					+ "Kaden Cook</a> from <a href=\"https://pixabay.com/music//?utm_source=link-attribution&utm_\n"
					+ "medium=referral&utm_campaign=music&utm_content=251388\">Pixabay</a>\r\n";
			infoPane.showMessageDialog(gameFrame, backgroundMusic);
		});
		
		gameFrame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				if (clip != null)
				{
					clip.stop();
					clip.close();
				}
			}
		});
	}
	
	// This method gets the information inside the ChoiceField text box
	private void submitChoice() {
		try
		{
			int choice = Integer.parseInt(choiceTextField.getText().trim());
			
			game.reciveChoice(choice);
			choiceTextField.setText("");
			
			if (game.endOfTurn)
			{
				restartButton.setEnabled(true);
			}
			else
			{
				restartButton.setEnabled(false);
			}
		}
		catch (NumberFormatException e)
		{
			gameDialogueArea.append("Enter valid number.\n");
		}
	}

	// This method gives the enables and disables some buttons and starts the game
	public void setStartButton()
	{
		if (game.player == null)
		{
			infoPane.showMessageDialog(gameFrame, "Please add the player information");
			return;
		}
		
		choiceTextField.setEnabled(true);
		startButton.setEnabled(false);
		confirmButton.setEnabled(true);
		dataButton.setEnabled(true);
		
		startGame();
	}
	
	// This method adds initializes the player variable 
	public void AddPlayer()
	{
		getPlayerInfo();
		if (game.player != null)
		{
			playerInformation.setText(game.player.getFirstName() + " " + game.player.getLastName());
			if (!(game.player.getNickName() == null) && !(game.player.getNickName().isBlank()))
			{
				playerInformation.setText(game.player.getFirstName() + " " + game.player.getLastName() 
				+ "(" + game.player.getNickName() + ")");
			}
			clickCount++;
		}
		
		if (clickCount >= MAX_CLICKS)
		{
			addPlayerItem.setEnabled(false);
		}
	}
	
	// This method asks for the player's information
	public void getPlayerInfo()
	{
		while (true)
		{
			JTextField firstNameField = new JTextField();
			JTextField lastNameField = new JTextField();
			JTextField nickNameField = new JTextField();
			
			JComponent[] nameInputs = new JComponent[]
			{
				new JLabel("What is your first name?  "), firstNameField,
				new JLabel("What is your last name? "), lastNameField,
				new JLabel("What is your nick name? "), nickNameField
					
			};
			
			int result = JOptionPane.showConfirmDialog(null, nameInputs, 
					"Please enter the following fields: ", 
					JOptionPane.OK_CANCEL_OPTION, 
		            JOptionPane.PLAIN_MESSAGE);
			
			if (result != JOptionPane.OK_OPTION)
			{
				return;
			}
			
			String firstName = firstNameField.getText().trim();
			String lastName = lastNameField.getText().trim();
			String nickName = nickNameField.getText().trim();
			
			if (firstName.isBlank() && lastName.isBlank())
			{
				infoPane.showMessageDialog(null, "Please insert all the informatiom.", "Input Error", JOptionPane.ERROR_MESSAGE);
				continue;
			}
				
			game.player = new Player(firstName, lastName, nickName);
			return;
		}
	}
	
	// This method writes the players information into a file
	public void getPlayerStats()
	{
		playerFile.createCharacterFile(game.player, game.itemsWon);
	}
	
	// This method starts the game
	public void startGame()
	{
		game.addHeros();
		game.gameIntroduction();
	}
	
	// This method makes the GUI visible and starts the audio
	public void showGUI()
	{
		gameFrame.setVisible(true);
		startAudio();
	}

	// AUDIO - Project Requirement
	// Method adds audio to the GUI
	private void startAudio() {
		try 
		{
			if (clip == null)
			{
				File audioFile = new File("src\\kaden_cook-8-bit-dungeon-251388.wav");
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
				clip = AudioSystem.getClip();
				clip.open(audioStream);
				clip.setFramePosition(0);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			else
			{
				clip.setFramePosition(0);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
		catch (UnsupportedAudioFileException e) {
            System.out.println("Error: This audio format is not supported.");
        } catch (LineUnavailableException e) {
            System.out.println("Error: Audio line is unavailable.");
        } catch (IOException e) {
            System.out.println("Error: File could not be read.");
        }
		
	}
	
}
