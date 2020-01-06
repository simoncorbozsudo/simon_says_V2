import javax.xml.bind.SchemaOutputResolver;
import java.io.*;
import java.util.Objects;

public class Model {
    private Controler ctrl;
    private File scoreFile= new File((System.getProperty("user.home")
            + "/Desktop/scores.txt").replace("\\", "/"));;
    private BufferedReader fileReader;
    private BufferedWriter fileWriter;

    private class Score{
        private String name;
        private int score;

        public int getScore() {
            return score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return false;
            if (o == null || getClass() != o.getClass()) return false;
            Score score1 = (Score) o;
            return score == score1.score;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, score);
        }
    }

    /**
     * Method to save the score and player name of the last game
     *
     * @param name  : name of the player
     * @param score : the score realised by the player
     */
    public void recordData(String name, int score) {
        String tmpScores = "";
        if (scoreFile.exists()) {
            tmpScores += getData() + name + " : " + score;
        } else {
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
     *
     * @return String containing all scores separated by '\n'
     */
    public String getData() {
        String tmpScores = "";
        if (scoreFile.exists()) {
            try {
                fileReader = new BufferedReader(new FileReader(scoreFile));
                String tmp = fileReader.readLine();
                while (tmp != null) {
                    tmpScores += tmp + "\n";
                    tmp = fileReader.readLine();
                }
                fileReader.close();
            } catch (IOException e) {
                System.err.println("An IOEXception happened.");
                e.printStackTrace();
            }
        } else {
            tmpScores = "No scores have been done yet.";
        }
        return tmpScores;
    }

    public void setCtrl(Controler ctrl) {
        this.ctrl = ctrl;
    }

}