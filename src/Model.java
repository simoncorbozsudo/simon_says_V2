import java.io.*;

public class Model {
    private Controler ctrl;
    private File scoreFile;
    private BufferedReader fileReader;
    private BufferedWriter fileWriter;

    public Model(){
        scoreFile = new File((System.getProperty("user.home")
                + "/Desktop/scores.txt").replace("\\", "/"));
    }

    /**
     * @param name
     * @param score
     */
    public void recordData(String name, int score) {
        String tmpScores = "";
        if(scoreFile.exists()){
            try {
                fileReader = new BufferedReader(new FileReader(scoreFile));
                String tmp = fileReader.readLine();
                while(tmp != null){
                    tmpScores += tmp + "\n";
                    tmp = fileReader.readLine();
                }
                tmpScores += name + " : " + score;
                fileReader.close();
            } catch (IOException e){
                System.err.println("An IOEXception happened.");
                e.printStackTrace();
            }
        }else{
            tmpScores = name + " : " + score;
        }

        try {
            fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(scoreFile), "utf-8"));
            fileWriter.write(tmpScores);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("An IOEXception happened.");
            e.printStackTrace();
        }
    }

    public void setCtrl(Controler ctrl) {
        this.ctrl = ctrl;
    }

}