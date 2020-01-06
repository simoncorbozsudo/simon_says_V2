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
     * Method to save the score and player name of the last game
     * @param name : name of the player
     * @param score : the score realised by the player
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

    /**
     * Method to get all the saved scored
     * @return String containing all scores separated by '\n'
     */
    public String getData(){
        String tmpScores = "";
        if(scoreFile.exists()){
            try {
                fileReader = new BufferedReader(new FileReader(scoreFile));
                String tmp = fileReader.readLine();
                while(tmp != null){
                    tmpScores += tmp + "\n";
                    tmp = fileReader.readLine();
                }
                fileReader.close();
            } catch (IOException e){
                System.err.println("An IOEXception happened.");
                e.printStackTrace();
            }
        }else{
            tmpScores = "No scores have been done yet.";
        }
        return tmpScores;
    }

    public void setCtrl(Controler ctrl) {
        this.ctrl = ctrl;
    }

}