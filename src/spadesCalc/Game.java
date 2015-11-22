package spadesCalc;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Game extends JFrame{
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        public int tricksTaken = 0;
        public int[][][] data;
        public int totalTricks;
        public int publicNumberOfPlayers;
        public String[] publicPlayerNames;
        public int publicDealer;
        Game(int numberOfPlayers, String[] playerNames, int dealer, final int[][][] importedData, final int round, final int numberOfTricks){
                publicPlayerNames = playerNames;
                publicDealer = dealer;
                publicNumberOfPlayers = numberOfPlayers;
                totalTricks = numberOfTricks;
                data = importedData;
                setMinimumSize(new Dimension(600, 600));
                setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH );
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                setResizable(true);
                setTitle("Calc Application");
                
                setLayout(new GridLayout(2, (int) ((numberOfPlayers+1)/2)));
                //setMinimumSize(new Dimension(1920,1080));
                setLocationRelativeTo(null);
                
                for(int count = 0;count<numberOfPlayers;count++){
                        Player player = new Player(data,round,count,playerNames,this);
                        add(player);

                }
                
        }
        public void trickTaken(int playerNumber,int round){
                data[round][playerNumber][1]++;
                tricksTaken++;
                if(totalTricks == tricksTaken){
                        setVisible(false);
                        Scorecard scorecard = new Scorecard(publicNumberOfPlayers, publicPlayerNames, publicDealer, data, round, totalTricks);
                        scorecard.setVisible(true);
                        dispose();
                        
                }
        }
}