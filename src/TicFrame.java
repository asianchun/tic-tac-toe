import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicFrame extends JFrame implements ActionListener {

    private JPanel playPanel;
    private JPanel textPanel;
    private JLabel textLabel;
    private JButton[] playButtons;
    private ImageIcon X;
    private ImageIcon O;
    private boolean xTurn;
    private boolean win;
    private ArrayList<Integer> xLocation;
    private ArrayList<Integer> oLocation;
    private JMenuBar menuBar;
    private JMenu settings;
    private JMenuItem newGame;

    TicFrame(){
        xTurn = true;
        win = true;
        X = new ImageIcon("close.png");
        O = new ImageIcon("o.png");
        xLocation = new ArrayList<>();
        oLocation = new ArrayList<>();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420,455);
        this.setLayout(new BorderLayout());

        textLabel = new JLabel("Tic-Tac-Toe");
        textLabel.setForeground(new Color(0x00ff00));
        textLabel.setBackground(Color.black);
        textLabel.setFont(new Font("Ink Free",Font.PLAIN,30));
        textLabel.setOpaque(true);

        textPanel = new JPanel();
        textPanel.setBounds(0,0,420,50);
        textPanel.setBackground(Color.black);
        textPanel.add(textLabel);

        playPanel = new JPanel();
        playPanel.setLayout(new GridLayout(3,3,0,0));

        playButtons = new JButton[9];
        for (int i = 0; i < playButtons.length; i++){
            playButtons[i] = new JButton();
            playButtons[i].setFocusable(false);
            playButtons[i].addActionListener(this);
            playPanel.add(playButtons[i]);
        }

        menuBar = new JMenuBar();
        settings = new JMenu("Settings");
        newGame = new JMenuItem("New Game");
        newGame.addActionListener(this);

        settings.add(newGame);
        menuBar.add(settings);

        this.add(textPanel, BorderLayout.NORTH);
        this.add(playPanel);
        this.setJMenuBar(menuBar);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void checkWin(ArrayList<Integer> moves){
        if (moves.contains(0) && moves.contains(1) && moves.contains(2)){
            win(0,1,2);
        } else if (moves.contains(3) && moves.contains(4) && moves.contains(5)){
            win(3,4,5);
        } else if (moves.contains(6) && moves.contains(7) && moves.contains(8)){
            win(6,7,8);
        } else if (moves.contains(0) && moves.contains(3) && moves.contains(6)){
            win(0,3,6);
        } else if (moves.contains(1) && moves.contains(4) && moves.contains(7)){
            win(1,4,7);
        } else if (moves.contains(2) && moves.contains(5) && moves.contains(8)){
            win(2,5,8);
        } else if (moves.contains(0) && moves.contains(4) && moves.contains(8)){
            win(0,4,8);
        } else if (moves.contains(2) && moves.contains(4) && moves.contains(6)){
            win(2,4,6);
        } else {
            win = false;
        }
    }

    private void win(int a, int b, int c){
        playButtons[a].setBackground(Color.green);
        playButtons[b].setBackground(Color.green);
        playButtons[c].setBackground(Color.green);

        if (xTurn){
            textLabel.setText("X wins");
        }
        else {
            textLabel.setText("O wins");
        }

        for (int i = 0; i < playButtons.length; i++){
            playButtons[i].setEnabled(false);
        }
        win = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < playButtons.length; i++){
            if (e.getSource() == playButtons[i]){
                if (xTurn){
                    textLabel.setText("O Turn");
                    playButtons[i].setIcon(X);
                    xLocation.add(i);
                    checkWin(xLocation);
                    xTurn = false;
                } else {
                    textLabel.setText("X Turn");
                    playButtons[i].setIcon(O);
                    oLocation.add(i);
                    checkWin(oLocation);
                    xTurn = true;
                }
                playButtons[i].setEnabled(false);

                if (!playButtons[0].isEnabled() && !playButtons[1].isEnabled()
                        && !playButtons[2].isEnabled() && !playButtons[3].isEnabled()
                        && !playButtons[4].isEnabled() && !playButtons[5].isEnabled()
                        && !playButtons[6].isEnabled() && !playButtons[7].isEnabled()
                        && !playButtons[8].isEnabled() && !win){
                    textLabel.setText("It's a draw");
                }
            }
        }

        if (e.getSource() == newGame){
            this.dispose();
            new TicFrame();
        }
    }
}