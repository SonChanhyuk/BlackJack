package screen;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RankScreen {
    private File file;
    private String[] top_users;
    private int[] top_chips;

    public RankScreen() {
        file = new File("res/member.txt");
        top_users = new String[] {"","","","","",""};
        top_chips = new int[]{0,0,0,0,0,0};
    }

    public void topUsers(){
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(file));
            String info = null;
            while ((info = inFile.readLine()) != null) {
                String[] user = info.split(";");
                int now = Integer.parseInt(user[2]);
                for(int rank = 0;rank<5;rank++) {
                    if(now>top_chips[rank]) {
                        for (int i = 4; i >rank-1; i--) {
                            top_users[i+1] = top_users[i];
                            top_chips[i+1] = top_chips[i];
                        }
                        top_chips[rank] = now;
                        top_users[rank] = user[0];
                        break;
                    }
                }
            }
            String text = "";
            for (int i = 0;i < 5;i++){
                text += i+1 + "등 - " + top_users[i] + " : " + top_chips[i] + "\n";
            }
            JOptionPane.showMessageDialog(null, text);
        }catch (IOException t){
            System.out.println("error");
        }
    }

}

