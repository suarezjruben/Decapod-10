/**
 * Name: View.java
 * Description: This class extends the JFrame class and implements ActionListener.
 *              This class will manage the display window of the game.
 * Methods: view() constructor, actionPerformed(ActionEvent e), mainView(), gameView(), mainMenuGUI(), gameGUI(),
 *          rulesView(), resize(), clearGrid().
 *
 * Status: view() constructor, actionPerformed(ActionEvent e), mainMenu_Comps(), gameView_Comps(), mainMenu_GUI(), gameGUI(),
 *         rulesView_Comps(), rulesView_GUI(), resize(), clearGrid() all fully functional.
 */

package systemLife;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class View extends JFrame implements ActionListener, MouseListener {
    private JFrame functionalityWindow;
    private JFrame rulesWindow;
    private JPanel gamePanel;
    private JButton[] button;
    private Icon icon;
    private JLabel title, rulesTitle, mode, iterations, speed, grid_size, presets, functionalityTitle;
    private JButton startGame, viewRules, exit, start, next, stop, reset, back, nextFunctionality,playGame,backRules;
    private GroupLayout groupLayoutmain, groupLayoutplay, groupLayoutrules,groupLayoutFunctionality;
    private JComboBox modeSelect, presetsSelect;
    private JSpinner iterateSelect = new JSpinner();
    private JSlider speedSlider  = new JSlider( 5, 50, 50);
    private JSlider sizeSlider = new JSlider( 5, 50);
    private JRadioButton sprite1, sprite2, sprite3, sprite4, sprite5, sprite6;
    private Controller controller;
    private final int DEFAULT_SIZE = 20, DEFAULT_WIDTH = 850, DEFAULT_HEIGHT = 800;
    private int size, delay = 50, buttonHeight, buttonWidth;    // button height is 28 by default until clicked on or grid is resized.

    /**
     * This integer variable (size) sets the size of the cell grid - also
     * used to calculate the amount of buttons needed in the grid.
     * The default size is 20 and it's assigned to the private final int DEFAULT_SIZE.
     * Bellow are different options for testing.
     */
    //private int size = 5;
    //private int size = 20;
    //private int size = 25;
    //private int size = 50;

    /**
     * Constructor
     */
    public View(Controller cntlr) {
        super("Decapod-10");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        controller = cntlr;
        size = DEFAULT_SIZE;
        setLayout(null);
        setVisible(true);
    }

    /**
     * Action Listener
     */
    public void actionPerformed(ActionEvent e){
        /**
         * Main menu Action Listener
         */
        if(e.getSource() == startGame) gameView_Comps();
        else if(e.getSource() == viewRules) rulesView_Comps();
        else if(e.getSource() == exit) System.exit(0);
        /**
         * Rules/Functionality Action Listener
         */
        else if(e.getSource()==back) rulesWindow.setVisible(false); //Sets popup frame visibility to false so closes
        else if(e.getSource() == nextFunctionality){
            rulesWindow.setVisible(false); //Sets popup frame visibility to false so closes
            functionalityView_Comps();
        }
        else if(e.getSource()==playGame)gameView_Comps();
        else if(e.getSource()==backRules){
            functionalityWindow.setVisible(false);
            rulesView_Comps();
        }
        /**
         * Game menu Action Listener
         */
        else if(e.getSource() == modeSelect) controller.setMode((String) modeSelect.getSelectedItem());
        else if(e.getSource() == presetsSelect) controller.setPreset((String) presetsSelect.getSelectedItem());
        else if(e.getSource() == sprite1){
            sprite2.setSelected(false);
            sprite3.setSelected(false);
            sprite4.setSelected(false);
            sprite5.setSelected(false);
            sprite6.setSelected(false);
            controller.setSprite(sprite1.getActionCommand());
        }
        else if(e.getSource() == sprite2){
            sprite1.setSelected(false);
            sprite3.setSelected(false);
            sprite4.setSelected(false);
            sprite5.setSelected(false);
            sprite6.setSelected(false);
            controller.setSprite(sprite2.getActionCommand());
        }
        else if(e.getSource() == sprite3){
            sprite1.setSelected(false);
            sprite2.setSelected(false);
            sprite4.setSelected(false);
            sprite5.setSelected(false);
            sprite6.setSelected(false);
            controller.setSprite(sprite3.getActionCommand());
        }
        else if(e.getSource() == sprite4){
            sprite1.setSelected(false);
            sprite2.setSelected(false);
            sprite3.setSelected(false);
            sprite5.setSelected(false);
            sprite6.setSelected(false);
            controller.setSprite(sprite4.getActionCommand());
        }
        else if(e.getSource() == sprite5){
            sprite1.setSelected(false);
            sprite2.setSelected(false);
            sprite3.setSelected(false);
            sprite4.setSelected(false);
            sprite6.setSelected(false);
            controller.setSprite(sprite5.getActionCommand());
        }
        else if(e.getSource() == sprite6){
            sprite1.setSelected(false);
            sprite2.setSelected(false);
            sprite3.setSelected(false);
            sprite4.setSelected(false);
            sprite5.setSelected(false);
            controller.setSprite(sprite6.getActionCommand());
        }
        else if(e.getSource() == start) controller.startButton();
        else if(e.getSource() == next) controller.nextButton();
        else if(e.getSource() == stop) controller.stopButton();
        else if(e.getSource() == reset) controller.resetButton();
        /**
         * Else (if not one of the above buttons, then it will be a cell button
         */
        else{
            buttonHeight = button[Integer.parseInt(e.getActionCommand()) - 1].getHeight();
            buttonWidth =  button[Integer.parseInt(e.getActionCommand()) - 1].getWidth();
            System.out.println("View Button pressed. Size: " + button[Integer.parseInt(e.getActionCommand()) - 1].getHeight() + " button # " + (Integer.parseInt(e.getActionCommand()) - 1));
            icon = getIcon();
            controller.setCell(e.getActionCommand());
            if(button[Integer.parseInt(e.getActionCommand()) - 1].getIcon() == null){
                button[Integer.parseInt(e.getActionCommand()) - 1].setIcon(icon);
            }
            else button[Integer.parseInt(e.getActionCommand()) - 1].setIcon(null);
        }
    }

    /**
     * Main menu components setup
     */
    public void mainMenu_Comps(){

        title = new JLabel("Decapod-10");
        title.setHorizontalAlignment((SwingConstants.CENTER));
        title.setFont(new Font("PLAIN", Font.PLAIN,30));

        startGame = new JButton("Start Game");
        startGame.addActionListener(this);
        startGame.setFont(new Font("PLAIN", Font.PLAIN,20));

        exit = new JButton("Exit");
        exit.addActionListener(this);
        exit.setFont(new Font("PLAIN", Font.PLAIN,20));

        viewRules = new JButton("Rules");
        viewRules.addActionListener(this);
        viewRules.setFont(new Font("PLAIN", Font.PLAIN,20));

        mainMenu_GUI();

    }

    /**
     * Rules window View Component setup
     * Not yet implemented
     */
    public void rulesView_Comps(){
        nextFunctionality = new JButton("Next");
        nextFunctionality.addActionListener(this);
        nextFunctionality.setFont(new Font("PLAIN",Font.PLAIN,20));

        back=new JButton("Back to Menu");
        back.addActionListener(this);
        back.setFont(new Font("PLAIN", Font.PLAIN,20));

        rulesTitle=new JLabel("RULES");
        rulesTitle.setHorizontalAlignment((SwingConstants.CENTER));
        rulesTitle.setFont(new Font("PLAIN", Font.PLAIN,20));


        rulesWindow = new JFrame("Game Rules");
        rulesWindow.setSize(500,500);
        rulesWindow.setLayout(null);
        rulesWindow.setVisible(true);

        rulesView_GUI();

    }

    /**
     * Game View components setup
     */
    public void gameView_Comps(){

        remove(startGame);
        remove(exit);
        remove(title);
        remove(viewRules);

        this.repaint();

        /**
         * Mouse Listener
         */
        addMouseListener(this);

        /**
         * Cells panel grid
         */
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(size, size));
        gamePanel.setBackground(SystemColor.control);

        /**
         * Grid buttons
         */
        button = new JButton[size * size];
        for(int i = 0; i < size * size; i++){
            button[i] = new JButton();
            button[i].setBackground(Color.white);
            button[i].setActionCommand(Integer.toString(i+1));
            gamePanel.add(button[i]); // Adds Button to content pane of frame
            button[i].addActionListener(this);
        }

        /**
         *  Buttons
         */
        start = new JButton("Start");
        start.addActionListener(this);

        next = new JButton("Next");
        next.addActionListener(this);

        stop = new JButton("Stop");
        stop.addActionListener(this);

        reset = new JButton("Reset");
        reset.addActionListener(this);

        /**
         * Game Functions Labels
         */
        title.setHorizontalAlignment(SwingConstants.CENTER);

        mode = new JLabel("Mode");
        //mode.setBounds(500,110,200,50);

        iterations = new JLabel("Iterations");
        //iterations.setBounds(550,210,200,50);

        speed = new JLabel("Speed");
        //speed.setBounds(550,310,200,50);

        grid_size = new JLabel("Grid Size");
        //grid_size.setBounds(550,410,200,50);

        presets = new JLabel("Presets");
        //presets.setBounds(550,510,200,50);

        /**
         * ComboBoxes
         */
        modeSelect = new JComboBox();       //Player Mode ComboBox
        modeSelect.setModel(new DefaultComboBoxModel(new String[] {"Single", "Multiplayer"}));
        modeSelect.addActionListener(this);

        presetsSelect = new JComboBox();       //Preset Selector ComboBox
        presetsSelect.setModel(new DefaultComboBoxModel(new String[] {"none", "John Conway", "Glider", "Exploder", "Tumbler"}));
        presetsSelect.addActionListener(this);

        /**
         * Spinner/Iterations selector
         * Are we setting a maximum?
         */
        //iterateSelect = new JSpinner();
        iterateSelect.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
        iterateSelect.addChangeListener(e -> controller.setIterations(iterateSelect));

        /**
         * Sliders
         * Have not yet setup a speed range or function.
         */
        //speedSlider = new JSlider( 5, 50, 25);
        speedSlider.addChangeListener(e -> controller.setSpeed(speedSlider));

        //sizeSlider = new JSlider( 5, 50);
        sizeSlider.setMinorTickSpacing(5);
        sizeSlider.setMajorTickSpacing(15);
        sizeSlider.setValue(size);
        //sizeSlider.setSnapToTicks(true);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);
        sizeSlider.addChangeListener(e -> controller.setSize(sizeSlider));

        /**
         * Sprites
         */
        sprite1 = new JRadioButton("Sprite 1");
        sprite1.addActionListener(this);
        sprite1.setSelected(true);
        sprite2 = new JRadioButton("Sprite 2");
        sprite2.addActionListener(this);
        sprite3 = new JRadioButton("Sprite 3");
        sprite3.addActionListener(this);
        sprite4 = new JRadioButton("Sprite 4");
        sprite4.addActionListener(this);
        sprite5 = new JRadioButton("Sprite 5");
        sprite5.addActionListener(this);
        sprite6 = new JRadioButton("Sprite 6");
        sprite6.addActionListener(this);

        gameGUI();

    }

    /**
     * Main Menu GUI setup
     */
    public void mainMenu_GUI(){

        groupLayoutmain = new GroupLayout(getContentPane());

        /**
         * Horizontal layout
         */
        groupLayoutmain.setHorizontalGroup(
                groupLayoutmain.createParallelGroup(Alignment.CENTER)
                        .addGroup(groupLayoutmain.createSequentialGroup()
                                .addPreferredGap(ComponentPlacement.RELATED,300, Short.MAX_VALUE)
                                .addGroup(groupLayoutmain.createParallelGroup(Alignment.CENTER)
                                        .addComponent(title, Alignment.CENTER, 170, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(startGame, Alignment.CENTER, 170, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(exit, Alignment.CENTER, 170, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(viewRules, Alignment.CENTER, 170, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                )
                                .addPreferredGap(ComponentPlacement.RELATED,300, Short.MAX_VALUE)
                        )

        );

        /**
         * Vertical layout
         */
        groupLayoutmain.setVerticalGroup(
                groupLayoutmain.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayoutmain.createSequentialGroup()
                                .addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, 100)
                                .addGroup(groupLayoutmain.createSequentialGroup()
                                        .addComponent(title, 50, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(150)
                                        .addComponent(startGame, 30, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(exit, 30, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(viewRules, 30, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                )
                                .addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );
        getContentPane().setLayout(groupLayoutmain);
    }

    /**
     * Game window GUI setup
     */
    public void gameGUI(){

        groupLayoutplay = new GroupLayout(getContentPane());
        /**
         * Horizontal layout
         */
        groupLayoutplay.setHorizontalGroup(
                groupLayoutplay.createParallelGroup(Alignment.CENTER)
                        .addGroup(groupLayoutplay.createSequentialGroup()
                                .addComponent(title)
                        )
                        .addGroup(groupLayoutplay.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayoutplay.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(gamePanel, Alignment.LEADING, getWidth()-280, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                        .addGroup(Alignment.LEADING, groupLayoutplay.createSequentialGroup()
                                                .addComponent(start, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                                .addGap(15)
                                                .addComponent(next, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                                .addGap(15)
                                                .addComponent(stop, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                                .addGap(15)
                                                .addComponent(reset, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        )
                                )
                                .addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(groupLayoutplay.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayoutplay.createSequentialGroup()
                                                .addGroup(groupLayoutplay.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(mode,75,75,75)
                                                        .addComponent(iterations,75,75,75)
                                                        .addComponent(speed,75,75,75)
                                                        .addComponent(grid_size,75,75,75)
                                                        .addComponent(presets,75,75,75)
                                                )
                                                .addGap(25)
                                                .addGroup(groupLayoutplay.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(modeSelect,130,130,130)
                                                        .addComponent(iterateSelect,130,130,130)
                                                        .addComponent(speedSlider,130,130,130)
                                                        .addComponent(sizeSlider,130,130,130)
                                                        .addComponent(presetsSelect,130,130,130)
                                                        .addGap(50)
                                                )
                                        )
                                        .addGroup(groupLayoutplay.createSequentialGroup()
                                                .addComponent(sprite1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(sprite2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(sprite3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(50)
                                        )
                                        .addGroup(groupLayoutplay.createSequentialGroup()
                                                .addComponent(sprite4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(sprite5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(sprite6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(50)
                                        )
                                )
                                .addContainerGap()
                        )
        );

        /**
         * Vertical layout
         */
        groupLayoutplay.setVerticalGroup(
                groupLayoutplay.createParallelGroup(Alignment.CENTER)
                        .addGroup(groupLayoutplay.createSequentialGroup()
                                .addGap(30)
                                .addComponent(title)
                                .addGap(30)
                                .addGroup(groupLayoutplay.createParallelGroup(Alignment.LEADING)
                                        .addComponent(gamePanel, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(groupLayoutplay.createSequentialGroup()
                                                .addGroup(groupLayoutplay.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(mode)
                                                        .addComponent(modeSelect)
                                                )
                                                .addGap(45)
                                                .addGroup(groupLayoutplay.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(iterations)
                                                        .addComponent(iterateSelect)
                                                )
                                                .addGap(45)
                                                .addGroup(groupLayoutplay.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(speed)
                                                        .addComponent(speedSlider)
                                                )
                                                .addGap(45)
                                                .addGroup(groupLayoutplay.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(grid_size)
                                                        .addComponent(sizeSlider)
                                                )
                                                .addGap(45)
                                                .addGroup(groupLayoutplay.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(presets)
                                                        .addComponent(presetsSelect)
                                                )
                                                .addGap(85)
                                                .addGroup(groupLayoutplay.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(sprite1)
                                                        .addComponent(sprite2)
                                                        .addComponent(sprite3)
                                                )
                                                .addGap(20)
                                                .addGroup(groupLayoutplay.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(sprite4)
                                                        .addComponent(sprite5)
                                                        .addComponent(sprite6)
                                                )
                                        )
                                )
                                .addGap(10)
                                .addGroup(groupLayoutplay.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(start)
                                        .addComponent(next)
                                        .addComponent(stop)
                                        .addComponent(reset)
                                )
                                .addGap(65)
                        )
        );

        getContentPane().setLayout(groupLayoutplay);
    }

    /**
     * Rules window View GUI setup
     * Not yet implemented
     */
    public void rulesView_GUI(){

        TextArea rulesText= new TextArea("The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of" + "\n" + "square cells, each of which is in one of two possible states, live or dead, " +
                "(or populated" + "\n" + "and unpopulated, respectively). Every cell interacts with its eight neighbours, which " + "\n" + "are the cells that are horizontally, vertically, or diagonally adjacent. " +
                "At each step in" + "\n" + "time, the following transitions occur:\n" +
                "\n" +
                "1.Any live cell with fewer than two live neighbours dies, as if by underpopulation.\n" +
                "2.Any live cell with two or three live neighbours lives on to the next generation.\n" +
                "3.Any live cell with more than three live neighbours dies, as if by overpopulation.\n" +
                "4.Any dead cell with exactly three live neighbours becomes a live cell, as if by " + "\n" +
                "   reproduction.");
        //rulesText.setBounds(0,100, 5000,5000);
        rulesText.setEditable(false);

        groupLayoutrules = new GroupLayout(rulesWindow.getContentPane());

        /**
         * Horizontal layout
         */
        groupLayoutrules.setHorizontalGroup(
                groupLayoutrules.createParallelGroup(Alignment.CENTER)
                        .addGroup(groupLayoutrules.createSequentialGroup()
                                        //.addPreferredGap(ComponentPlacement.RELATED,300, Short.MAX_VALUE)
                                        .addGroup(groupLayoutrules.createParallelGroup(Alignment.CENTER)
                                                .addComponent(rulesTitle, Alignment.CENTER, 500, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(rulesText/*, Alignment.CENTER, 170, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                                .addComponent(back/*, Alignment.CENTER, 170, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                                .addComponent(nextFunctionality/*, Alignment.CENTER, 170, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                        )
                                //.addPreferredGap(ComponentPlacement.RELATED,300, Short.MAX_VALUE)
                        )

        );

        /**
         * Vertical layout
         */
        groupLayoutrules.setVerticalGroup(
                groupLayoutrules.createParallelGroup(Alignment.CENTER)
                        .addGroup(groupLayoutrules.createSequentialGroup()
                                //.addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, 100)
                                .addGroup(groupLayoutrules.createSequentialGroup()
                                        .addComponent(rulesTitle/*, 50, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                        //.addGap(150)
                                        .addComponent(rulesText/*, 30, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                        .addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(back/*, 30, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                        .addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nextFunctionality/*, 30, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                )
                                .addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );
        rulesWindow.getContentPane().setLayout(groupLayoutrules);
    }

    /**
     * Functionality window View Component setup
     * Not yet implemented
     */
    public void functionalityView_Comps(){
        playGame = new JButton("Play Game");
        playGame.addActionListener(this);
        playGame.setFont(new Font("PLAIN",Font.PLAIN,20));

        backRules=new JButton("Back to Rules");
        backRules.addActionListener(this);
        backRules.setFont(new Font("PLAIN", Font.PLAIN,20));

        functionalityTitle=new JLabel("Functionality");
        functionalityTitle.setHorizontalAlignment((SwingConstants.CENTER));
        functionalityTitle.setFont(new Font("PLAIN", Font.PLAIN,20));



        functionalityWindow = new JFrame("Functionality");
        functionalityWindow.setSize(500,500);
        functionalityWindow.setLayout(null);
        functionalityWindow.setVisible(true);
        functionalityView_GUI();
    }

    /**
     * Functionality window View GUI setup
     * Not yet implemented
     */
    public void functionalityView_GUI(){
        TextArea funtionalityText= new TextArea(
                "Once -Start Game- is selected, the app will take your to the game board containing-\n" +
                        " Mode: Gives user options to select single or multiplayer mode.\n" +
                        "Iterations: allows the user to set the number of genertions the program willcycle through\n" +
                        "Speed Slider: Allows the user to adjust the speed of the iterations\n" +
                        " Grid Size Slider: Adjusts the grid size. (Moving to the right increase size and moving to the\n" +
                        "   left  decreases size of grid  \n" +
                        " Presets: Gives user drop down menu of different predetermined shapes to start game with.\n" +
                        "Sprites: User has three options of sprites to use to represent their live cells.\n" +
                        " Start: Starts the game and cycles through iterations.\n" +
                        "  Allows user to manually cycle through 1 iteration.\n" +
                        "Stop: Stops the iterations at the iteration the game is presently on.\n" +
                        "  Reset: Reverts game screen to orinal stae with clear grid and no settings chosen.");
        funtionalityText.setEditable(false);
        groupLayoutFunctionality = new GroupLayout(functionalityWindow.getContentPane());
        /**
         * Horizontal layout
         */
        groupLayoutFunctionality.setHorizontalGroup(
                groupLayoutFunctionality.createParallelGroup(Alignment.CENTER)
                        .addGroup(groupLayoutFunctionality.createSequentialGroup()
                                        //.addPreferredGap(ComponentPlacement.RELATED,300, Short.MAX_VALUE)
                                        .addGroup(groupLayoutFunctionality.createParallelGroup(Alignment.CENTER)
                                                .addComponent(functionalityTitle, Alignment.CENTER, 500, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(funtionalityText/*, Alignment.CENTER, 170, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                                .addComponent(backRules/*, Alignment.CENTER, 170, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                                .addComponent(playGame/*, Alignment.CENTER, 170, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                        )
                                //.addPreferredGap(ComponentPlacement.RELATED,300, Short.MAX_VALUE)
                        )

        );

        /**
         * Vertical layout
         */
        groupLayoutFunctionality.setVerticalGroup(
                groupLayoutFunctionality.createParallelGroup(Alignment.CENTER)
                        .addGroup(groupLayoutFunctionality.createSequentialGroup()
                                //.addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, 100)
                                .addGroup(groupLayoutFunctionality.createSequentialGroup()
                                        .addComponent(functionalityTitle/*, 50, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                        //.addGap(150)
                                        .addComponent(funtionalityText/*, 30, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                        .addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(backRules/*, 30, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                        .addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(playGame/*, 30, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE*/)
                                )
                                .addPreferredGap(ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );
        functionalityWindow.getContentPane().setLayout(groupLayoutFunctionality);
    }

    /**
     * Resize function
     */
    public void setSize(int newSize){
        System.out.println("In View setSize()");
        for(int i = 0; i < size * size; i++){
            gamePanel.remove(button[i]);
        }
        size = newSize;
        button = new JButton[size * size];
        gamePanel.setLayout(new GridLayout(size, size));
        gamePanel.setBackground(SystemColor.control);

        for(int i = 0; i < size * size; i++){
            button[i] = new JButton();
            button[i].setBackground(Color.white);
            button[i].setActionCommand(Integer.toString(i+1));
            gamePanel.add(button[i]); // Adds Button to content pane of frame
            button[i].addActionListener(this);
        }

        gameGUI();
    }

    /**
     * Reset function
     */
    public void resetGrid() {
        System.out.println("In View resetGrid()");
        for(int i = 0; i < size * size; i++){
            button[i].setIcon(null);
        }
        presetsSelect.setSelectedItem("none");
    }

    /**
     * setIcon() function
     */
    public void setIcon(){
        System.out.println("In View setIcon()");
        icon = null;
        if(sprite1.isSelected()) {
            icon = new ImageIcon(new ImageIcon("zoidberg.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_FAST));
        }
        if(sprite2.isSelected()){
            icon = new ImageIcon(new ImageIcon("fry.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT));
        }
        if(sprite3.isSelected()){
            icon = new ImageIcon(new ImageIcon("bender.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT));
        }
        if(sprite4.isSelected()) {
            icon = new ImageIcon(new ImageIcon("janitor.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_FAST));
        }
        if(sprite5.isSelected()){
            icon = new ImageIcon(new ImageIcon("professor.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT));
        }
        if(sprite6.isSelected()){
            icon = new ImageIcon(new ImageIcon("leela.png").getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT));
        }

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (button[(j) + (i) * size].getIcon() != null) {    // Removing cell icon if dead
                    button[(j) + (i) * size].setIcon(icon);
                }
            }
        }

    }

    /**
     * setPreset() function
     */
    public void setGrid(int[][] newGrid){
        System.out.println("In View setGrid()");

        buttonHeight = gamePanel.getHeight()/size + 8;
        buttonWidth = gamePanel.getWidth()/size + 8;


        setIcon();

        for(int i = 0; i < size * size; i++){
            gamePanel.remove(button[i]);
        }
        size = newGrid.length - 2;
        button = new JButton[size * size];
        gamePanel.setLayout(new GridLayout(size, size));
        gamePanel.setBackground(SystemColor.control);
        //sizeSlider.setValue(size);



        for(int i = 0; i < size * size; i++){
            button[i] = new JButton();
            button[i].setBackground(Color.white);
            button[i].setActionCommand(Integer.toString(i+1));
            gamePanel.add(button[i]); // Adds Button to content pane of frame
            button[i].addActionListener(this);
        }

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                // the +1 is to adjust the starting point at [1][1] as top-left corner
                if (newGrid[i + 1][j +1] == 0) {    // Removing cell icon if dead
                    button[(j) + (i) * size].setIcon(null);
                }
                else {                      // Adding cell icon if alive
                    button[(j) + (i) * size].setIcon(icon);
                }
            }
        }
        gameGUI();
        try {
            Thread.sleep(getDelay());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setSizeSliderValue(int value){
        System.out.println("In View setSizeSliderValue()");
        sizeSlider.setValue(value);
    }

    public void setDelay(int inDelay){
        System.out.println("In View inDelay()");
        delay = inDelay;
    }

    public void setIterations(int iterationValue){
        System.out.println("In View iterationValue()");
        iterateSelect.setValue(iterationValue);
    }

    public void setButtonHeight(int height){
        buttonHeight = height;
    }

    public int getDelay(){
        System.out.println("In View getDelay()");
        return 5000/delay;
    }
    public Icon getIcon(){
        System.out.println("In View getIcon()");
        setIcon();
        return icon;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Mouse Clicked!");
        if(button[1].getHeight() == 0){
            //Do nothing
        }
        else{
            buttonHeight = button[1].getHeight();
            buttonWidth = button[1].getWidth();
            icon = getIcon();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("Mouse Pressed!");
        if(button[1].getHeight() == 0){
            //Do nothing
        }
        else{
            buttonHeight = button[1].getHeight();
            buttonWidth = button[1].getWidth();
            icon = getIcon();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("Mouse Released!");
        if(button[1].getHeight() == 0){
            //Do nothing
        }
        else{
            buttonHeight = button[1].getHeight();
            buttonWidth = button[1].getWidth();
            icon = getIcon();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("Mouse Entered!");
        if(button[1].getHeight() == 0){
            //Do nothing
        }
        else{
            buttonHeight = button[1].getHeight();
            buttonWidth = button[1].getWidth();
            icon = getIcon();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("Mouse Exited!");
        if(button[1].getHeight() == 0){
            //Do nothing
        }
        else{
            buttonHeight = button[1].getHeight();
            buttonWidth = button[1].getWidth();
            icon = getIcon();
        }
    }
}
