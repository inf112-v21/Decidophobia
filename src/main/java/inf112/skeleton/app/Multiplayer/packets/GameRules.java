package inf112.skeleton.app.Multiplayer.packets;

import java.io.*;
import java.util.Scanner;

public class GameRules implements Serializable {
    private int damageTokens;
    private int lifeTokens;
    private String boardPath;
    private String readBoardFile;

    public GameRules(){
        this.damageTokens = 9;
        this.lifeTokens = 3;
    }
    public GameRules( int lifeTokens, int damageTokens){
        this.damageTokens = damageTokens;
        this.lifeTokens = lifeTokens;
    }
    public GameRules( int lifeTokens, int damageTokens, String boardPath){
        this.damageTokens = damageTokens;
        this.lifeTokens = lifeTokens;
        changeMap(boardPath);
    }

    public GameRules(String str) {
        String[] rules = str.split(";");
        this.lifeTokens = Integer.valueOf(rules[0]);
        this.damageTokens = Integer.valueOf(rules[1]);
        this.boardPath = rules[2];

        for(int i = 3; i<rules.length; i++){
            this.readBoardFile += (rules[i]==null) ? "" : rules[i]+";";
        }
        this.readBoardFile = this.readBoardFile.substring(0,readBoardFile.length()-1);
        //writes down boardFile, if not it already exist.
        try {
            File f = new File(boardPath);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
                FileWriter boardWriter = new FileWriter(boardPath);
                boardWriter.write(readBoardFile);
                boardWriter.close();
            }
        } catch (IOException e) {}


    }

    public void changeMap(String fileName){
        try {
            File boardFile = new File(fileName);
            Scanner myReader = new Scanner(boardFile);

            String file = "";
            while (myReader.hasNextLine()) {
                file += myReader.nextLine() + "\n";
            }
            myReader.close();
            boardPath = fileName;
            readBoardFile = file;
        }
        catch (FileNotFoundException e) {
        }


    }

    public int getDamageTokens() {
        return damageTokens;
    }

    public void setDamageTokens(int damageTokens) {
        this.damageTokens = damageTokens;
    }

    public int getLifeTokens() {
        return lifeTokens;
    }

    public void setLifeTokens(int lifeTokens) {
        this.lifeTokens = lifeTokens;
    }

    @Override
    public boolean equals(Object obj) {
        GameRules newGame = (GameRules) obj;
        return getDamageTokens() == newGame.getDamageTokens() && getLifeTokens() == newGame.getLifeTokens();
    }

    @Override
    public String toString() {
        return lifeTokens+";"+damageTokens+";"+boardPath+";"+readBoardFile;
    }

    public String getBoardPath(){
        return boardPath;
    }

    public void setBoard(String file) {
        readBoardFile = file;
    }

    public String getBoard() {
        return readBoardFile;
    }
}
