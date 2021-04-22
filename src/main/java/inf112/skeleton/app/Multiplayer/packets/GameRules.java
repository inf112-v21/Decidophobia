package inf112.skeleton.app.Multiplayer.packets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
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

    public GameRules( int lifeTokens, int damageTokens, String boardPath){
        this.damageTokens = damageTokens;
        this.lifeTokens = lifeTokens;
        changeMap(boardPath);
        System.out.println("it happened");
    }

    public GameRules(String str) {
        String[] rules = str.split(";");
        this.lifeTokens = Integer.valueOf(rules[0]);
        this.damageTokens = Integer.valueOf(rules[1]);
        this.boardPath = rules[2];
        for(int i = 3; i<rules.length; i++){
            this.readBoardFile += rules[i]+";";
        }
    }

    public void changeMap(String fileName){
        try {
            File boardFile = new File(fileName);
            Scanner myReader = new Scanner(boardFile);

            String file = "";
            while (myReader.hasNextLine()) {
                file += myReader.nextLine();
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
