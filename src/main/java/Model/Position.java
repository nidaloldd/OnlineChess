package Model;

import Model.Figures.Figure;

public class Position {
    private int posX;
    private int posY;
    private Figure figure;

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

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


        String posString = "";
        switch (position.posX){
            case 0: posString+="A";break;
            case 1: posString+="B";break;
            case 2: posString+="C";break;
            case 3: posString+="D";break;
            case 4: posString+="E";break;
            case 5: posString+="F";break;
            case 6: posString+="G";break;
            case 7: posString+="H";break;
        }
        switch (position.posY){
            case 0: posString+="8";break;
            case 1: posString+="7";break;
            case 2: posString+="6";break;
            case 3: posString+="5";break;
            case 4: posString+="4";break;
            case 5: posString+="3";break;
            case 6: posString+="2";break;
            case 7: posString+="1";break;
        }
        return posString;
    }

    public static Position toPosition(String str){
        String letter = str.substring(0,1);
        String number = str.substring(1,2);
        Position position = new Position();
        switch (letter){
            case "A": position.setPosX(0);break;
            case "B": position.setPosX(1);break;
            case "C": position.setPosX(2);break;
            case "D": position.setPosX(3);break;
            case "E": position.setPosX(4);break;
            case "F": position.setPosX(5);break;
            case "G": position.setPosX(6);break;
            case "H": position.setPosX(7);break;
        }
        switch (number){
            case "8": position.setPosY(0);break;
            case "7": position.setPosY(1);break;
            case "6": position.setPosY(2);break;
            case "5": position.setPosY(3);break;
            case "4": position.setPosY(4);break;
            case "3": position.setPosY(5);break;
            case "2": position.setPosY(6);break;
            case "1": position.setPosY(7);break;
        }
        return position;
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

    public boolean isPositionValid(){
        if(posX < Table.TABLE_SIZE && posX >= 0 &&
                posY < Table.TABLE_SIZE && posY >= 0){
            return true;
        }
        return false;
    }

    public Position stepToDirection(final Direction direction) {
        return new Position(posX + direction.getXChange(),
                posY + direction.getYChange());
    }
}
