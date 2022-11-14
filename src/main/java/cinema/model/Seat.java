package cinema.model;

public class Seat {
    private int row;
    private int column;
    private boolean isAvailable = true;
    private int price;

    public Seat(int row, int column, boolean isAvailable) {
        this.row = row;
        this.column = column;
        this.isAvailable = isAvailable;
        if (row <= 4){
            this.price = 10;
        } else {
            this.price = 8;
        }

    }

    public Seat() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }


    public boolean checkAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
