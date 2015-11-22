package spadesCalc;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

class New_Game extends JFrame {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        public int[][][] data;
        public JButton next;
        public JSpinner startingNumber;
        ArrayList<Player> playerArray = new ArrayList<Player>();
        public String[] playerNames,playerNames2;
        public New_Game() {
                setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                setResizable(true);
                setTitle("Spades Calculator");
                setLayout(null);
                setMinimumSize(new Dimension(600, 600));
                setLocationRelativeTo(null);            
                setLayout(new GridLayout(20, 1, 5, 5));
                JPanel bar1 = new JPanel();
                JLabel question = new JLabel("Enter payer names in order into the boxes below");
                add(bar1);
                bar1.add(question);

                JPanel bar = new JPanel();
                add(bar);
                JLabel question2 = new JLabel("What trick number are you starting at?");
                final SpinnerNumberModel startingNumberModel = new SpinnerNumberModel(
                                1, 1, 26, 1);
                startingNumber = new JSpinner(startingNumberModel);
                startingNumber.setBounds(0, 0, 35, 50);
                bar.add(question2);
                bar.add(startingNumber);

                for (int count = 1; count < 16; count++) {
                        Player player = new Player(count);
                        playerArray.add(player);
                        add(player);
                }
                next = new JButton("Done");
                next.setMaximumSize(new Dimension(500, 50));
                add(next);
                
                next.addMouseListener(new MouseListener() {
                        
                        @Override
                        public void mouseReleased(MouseEvent arg0) {
                        }
                        
                        @Override
                        public void mousePressed(MouseEvent arg0) {
                        }
                        
                        @Override
                        public void mouseExited(MouseEvent arg0) {
                        }
                        
                        @Override
                        public void mouseEntered(MouseEvent arg0) {
                        }
                        
                        @Override
                        public void mouseClicked(MouseEvent arg0) {
                                System.out.println("Mouse clicked");
                                setVisible(false);
                                data = new int[20][20][3];
                                int round = 0;
                                int numberOPlayers = numOfPlayers();
                                int dealer = (int) ((Math.random()*playerNames2.length));
                                System.out.println(dealer);
                                Bidding bidding = new Bidding(numberOPlayers, playerNames,dealer,data,round,(int)startingNumber.getValue());
                                bidding.setVisible(true);
                                dispose();
                        }
                });
        }

        class Player extends JPanel {
                /**
                 * 
                 */
                private static final long serialVersionUID = 1L;
                public JTextField name;

                public Player(int number) {
                        String sNumber = Integer.toString(number);
                        JLabel playerNumber = new JLabel(sNumber + ":");
                        add(playerNumber);
                        name = new JTextField();
                        name.setPreferredSize(new Dimension(300, 30));
                        add(name);
                }
        }

        public int numOfPlayers() {
                int number = 0;
                playerNames = new String[16];
                for (int count = 0; count < playerArray.size(); count++) {
                        if(!playerArray.get(count).name.getText().isEmpty()){
                                System.out.println(count);
                                playerNames[number] = playerArray.get(count).name.getText();
                                number++;
                        }
                }
                playerNames2 = new String[number];
                for(int count = 0; count < playerNames2.length; count++){
                        playerNames2[count]=playerNames[count];
                }
                return number;
        }
        public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable(){
                        public void run() {
                                New_Game newGame = new New_Game();
                                newGame.setVisible(true);
                        }
                });
        }

}