package spadesCalc;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

class Bidding extends JFrame {
        /**
         * This takes all of the data brought in by the Main app and uses it to let people bid
         */
        private static final long serialVersionUID = 1L;
        public int currentBidder;
        public JLabel name;
        public int numberOfTricksLeft;
        public JLabel tricksLeft;
        public JButton next;
        public int thisDealer;
        public JSpinner tricks;
        public int numberOfTricks;
        public Bidding(final int numberOfPlayers, final String[] playerNames, final int dealer, final int[][][] data, final int round, int importedNumberOfTricks) {
                numberOfTricks = importedNumberOfTricks;
                setMinimumSize(new Dimension(600, 600));
                setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH );
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                setResizable(true);
                setTitle("Spades Calculator");
                setLayout(null);
                setLocationRelativeTo(null);
                currentBidder = dealer + 1;
                if(currentBidder >= numberOfPlayers){
                        currentBidder = 0;
                }
                thisDealer = dealer;
                numberOfTricksLeft = numberOfTricks;
                setLayout(new GridLayout(1, 2));
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(2, 1));
                final JPanel setNumberOfTricks = new JPanel();
                final JLabel numberOfTricksLabel = new JLabel("Total tricks: ");
                numberOfTricksLabel.setFont(new Font("Serif", Font.PLAIN, 150));
                setNumberOfTricks.add(numberOfTricksLabel);
                
                final SpinnerNumberModel startingNumberModel = new SpinnerNumberModel(numberOfTricks, 1, 26, 1);
                final JSpinner startingNumber = new JSpinner(startingNumberModel);
                startingNumber.setFont(new Font("Serif", Font.PLAIN, 150));

                setNumberOfTricks.add(startingNumber);
                panel.add(setNumberOfTricks); 
                
                
                tricksLeft = new JLabel("Tricks left: "+Integer.toString(numberOfTricks));
                tricksLeft.setFont(new Font("Serif", Font.PLAIN, 150));
                panel.add(tricksLeft);
                panel.setBounds(0, 0, 100, 100);
                add(panel);
                
                JPanel panel2 = new JPanel();
                panel2.setLayout(new GridLayout(3, 1));
                JLabel dealerName = new JLabel(playerNames[dealer]+" is the dealer");
                dealerName.setFont(new Font("Serif", Font.PLAIN, 100));
                panel2.add(dealerName);
                
                JPanel biddingContainer = new JPanel();

                panel2.add(biddingContainer);
                name = new JLabel(playerNames[currentBidder]+":");
                

                name.setFont(new Font("Serif", Font.PLAIN, 150));
                biddingContainer.add(name);
                final SpinnerNumberModel tricksModel = new SpinnerNumberModel(0, 0, 26, 1);
                tricks = new JSpinner(tricksModel);
                
                JPanel panel3 = new JPanel();
                
                tricks.setFont(new Font("Serif", Font.PLAIN, 150));
                panel3.add(tricks);
                biddingContainer.add(panel3);
                JButton next = new JButton("Next");
                next.setFont(new Font("Serif", Font.PLAIN, 100));
                panel2.add(next);
                add(panel2);
                
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
                                if(currentBidder==dealer+1||(dealer==(numberOfPlayers-1)&&currentBidder==0)){
                                        numberOfTricks = (int)startingNumber.getValue();
                                        numberOfTricksLabel.setText("Total tricks: "+numberOfTricks);
                                        startingNumber.setVisible(false);
                                        numberOfTricksLeft = numberOfTricks;
                                        setNumberOfTricks.remove(startingNumber);
                                        revalidate();
                                }
                                if(currentBidder != thisDealer){
                                        data[round][currentBidder][0] = (int)tricks.getValue();
                                        numberOfTricksLeft = numberOfTricksLeft - data[round][currentBidder][0];
                                        currentBidder = currentBidder +1;
                                        if(currentBidder >= numberOfPlayers){
                                                currentBidder = 0;
                                        }
                                        name.setText(playerNames[currentBidder]+":");
                                        tricks.setValue(0);
                                        tricksLeft.setText("Tricks left: "+Integer.toString(numberOfTricksLeft));
                                }else{
                                        if(numberOfTricksLeft - (int)tricks.getValue() != 0){
                                                data[round][currentBidder][0] = (int)tricks.getValue();
                                                setVisible(false);
                                                Game game = new Game(numberOfPlayers, playerNames, dealer, data, round, numberOfTricks);
                                                game.setVisible(true);
                                                dispose();
                                        }
                                }
                        }
                });
        }
}