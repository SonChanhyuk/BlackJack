package engine;
import java.io.*;

public class Login {
    private String userId;
    private int userChip;
    private File file;

    public Login() {
        file = new File("res/member.txt");
    }

    public int LoginCheck(String id, String pass) {
        if(id.contains(";")||pass.contains(";")){
            return 3;//특수문자 입력
        }
        else{
            try {
                BufferedReader inFile = new BufferedReader(new FileReader(this.file));
                String info = "";
                while ((info = inFile.readLine()) != null) {
                    String[] user = info.split(";");
                    System.out.println(user[0]+user[1]+user[2]);
                    if (user[0].equals(id)) {
                        if (user[1].equals(pass)) {
                            userId = id;
                            userChip = Integer.parseInt(user[2]);
                            if (userChip > 0) {
                                return 0;//로그인 성공
                            }
                            else{
                                return 4;//파산한 계정
                            }
                        } else {
                            return 1;//비밀번호 틀림
                        }
                    }
                }
                return 2;//존재하지 않는 아이디
            } catch (IOException e) {
                return -1;//기타 에러
            }
        }

    }

    public void ChangeChip(int chip){
        try {
            String input = "";
            BufferedReader inFile = new BufferedReader(new FileReader(this.file));
            String info;
            while ((info = inFile.readLine()) != null) {
                String[] user = info.split(";");
                if (user[0].equals(this.userId)) {
                    user[2] = Integer.toString(chip);
                }
                System.out.println(user[0]+user[1]+user[2]);
                input += user[0] + ";" + user[1] + ";" + user[2] + "\r\n";
            }
            System.out.println(input);
            FileWriter fw = new FileWriter("res/member.txt", false);
            fw.write(input);
            fw.close();
        }catch (IOException u){
        }
    }
    public int getUserChip(){ return this.userChip; }
}

