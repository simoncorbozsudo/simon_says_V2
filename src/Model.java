import java.io.*;
import java.util.Comparator;
import java.util.Objects;
import java.util.Arrays;

public class Model {
    private File scoreFile = new File((System.getProperty("user.home")
            + "/Desktop/scores.txt").replace("\\", "/"));
    ;
    private BufferedReader fileReader;
    private BufferedWriter fileWriter;

    private class Score {
        private String name;
        private int score;

        public Score(String name, int score){
            this.name = name;
            this.score = score;
        }

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

        @Override
        public String toString() {
            return name + ":" + score;
        }
    }

    private class SortByScore implements Comparator<Score>{
        @Override
        public int compare(Score o1, Score o2) {
            if(o1.getScore() < o2.getScore()) return 1;
            if(o1.getScore() > o2.getScore()) return -1;
            return 0;
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
            tmpScores += getData() + name + ":" + score;
        } else {
            tmpScores = name + ":" + score;
        }
        tmpScores = sortScores(tmpScores);
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
        return sortScores(tmpScores);
    }

    private String sortScores(String scores) {
        String tmpList[] = scores.split(":|\n");
        Score[] scoresList = new Score[tmpList.length/2];

        for (int i = 0; i < scoresList.length; i++) {
            scoresList[i] = new Score(tmpList[2 * i], Integer.parseInt(tmpList[2 * i + 1]));
        }

        Arrays.sort(scoresList, new SortByScore());

        String tmp = "";
        for (int i = 0; i < scoresList.length; i++) {
            tmp += scoresList[i] + "\n";
        }
        return tmp;
    }

    public static void main(String[] args) {
        Model mdl = new Model();
        mdl.recordData("Ahmed", 50);
    }

}