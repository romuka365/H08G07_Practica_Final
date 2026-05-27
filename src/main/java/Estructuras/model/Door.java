package model;

public class Door {
    private Boolean locked;
    private Key key;
    private Cell position;

    public Door() //arma estándar  ?
    {
        if (locked==true){
            key = new Key();
        }
        else {
            key = null;
        }
        position = new Cell(1,1);
    }

    public Door(Key key, Boolean locked,Cell position) {
        this.key = key;
        this.locked = locked;
        this.position = position;
    }


    public Key getKey() {
        return key;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Cell getPosition() {
        return position;
    }
}
