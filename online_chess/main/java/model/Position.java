package model;

import model.figures.Figure;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Position {
    private int posX;
    private int posY;
    public static Map<Character,Integer> ranksToPos = new HashMap<Character,Integer>();
    public static Map<Character,Integer> filesToPos = new HashMap<Character,Integer>();
    public static Map<Integer,Character> ranksToString = new HashMap<Integer,Character>();
    public static Map<Integer,Character> filesToString = new HashMap<Integer,Character>();

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Position() {
        this.posX = 0;
        this.posY = 0;
    }
    public Position(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    public Position(String str) {
        this(Position.toPosition(str).posX,
                Position.toPosition(str).posY);
    }
    public boolean isEmpty(Table table){
        for (Figure f : table.getFigures() ) {
            if (this.equals(f.getPosition())) {
                return false;
            }
        }
        return true;
    }
    public static String toString(Position position){
        if(position.isPositionNotValid()){
            return " ";
        }

        ranksToString.put(0,'A');
        ranksToString.put(1,'B');
        ranksToString.put(2,'C');
        ranksToString.put(3,'D');
        ranksToString.put(4,'E');
        ranksToString.put(5,'F');
        ranksToString.put(6,'G');
        ranksToString.put(7,'H');
        filesToString.put(0,'8');
        filesToString.put(1,'7');
        filesToString.put(2,'6');
        filesToString.put(3,'5');
        filesToString.put(4,'4');
        filesToString.put(5,'3');
        filesToString.put(6,'2');
        filesToString.put(7,'1');
        return ranksToString.getOrDefault(position.posX,' ').toString()+filesToString.getOrDefault(position.posY,' ').toString();
    }

    public static Position toPosition(String str){
        Pattern pattern = Pattern.compile("^([A-H][1-8])$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        if(!matcher.find()) {
            System.out.println("Match not found");
            return new Position(-1,-1);
        }
        ranksToPos.put('A',0);
        ranksToPos.put('B',1);
        ranksToPos.put('C',2);
        ranksToPos.put('D',3);
        ranksToPos.put('E',4);
        ranksToPos.put('F',5);
        ranksToPos.put('G',6);
        ranksToPos.put('H',7);
        filesToPos.put('8',0);
        filesToPos.put('7',1);
        filesToPos.put('6',2);
        filesToPos.put('5',3);
        filesToPos.put('4',4);
        filesToPos.put('3',5);
        filesToPos.put('2',6);
        filesToPos.put('1',7);
        return new Position(ranksToPos.get(Character.toUpperCase(str.charAt(0))),filesToPos.get(str.charAt(1)));
    }

    @Override
    public boolean equals(Object pos) {
        if(!(pos instanceof Position)){
            return false;
        }
        return posX==((Position) pos).posX && posY== ((Position) pos).posY;
    }
    @Override
    public String toString() {
        return posX+" : "+posY;
    }

    public boolean isPositionNotValid(){
        return posX >= Table.TABLE_SIZE || posX < 0 ||
                posY >= Table.TABLE_SIZE || posY < 0;
    }

    public Position stepToDirection(final Direction direction) {
        return new Position(posX + direction.getXChange(),
                posY + direction.getYChange());
    }
}
