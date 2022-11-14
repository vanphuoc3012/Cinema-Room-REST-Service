package cinema.model;

import java.util.ArrayList;
import java.util.List;

public class MovieTheater {
    private int totalRows = 9;
    private int totalColumns = 9;
    private Seat[][] seats = new Seat[totalRows][totalColumns];
    private List<Seat> availableSeats = new ArrayList<>();
    private List<Purchase> purchaseList = new ArrayList<>();
    private Statistics statistics = new Statistics();
    private int currentIncome;

    public MovieTheater() {
        for(int x = 0; x < seats.length; x++){
            for(int y = 0; y < seats[x].length; y++){
                Seat s = new Seat(x+1, y+1, true);
                seats[x][y] = s;
                this.addAvailableSeat(s);
            }
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    private void addAvailableSeat(Seat s){
        if(s.checkAvailable()){
            this.availableSeats.add(s);
        }
    }

    public boolean checkTicketAvailable(Seat seat){
        int x = seat.getRow() - 1;
        int y = seat.getColumn() - 1;
        return seats[x][y].checkAvailable();
    }

    public Purchase sellTicket(Seat seat){
        int x = seat.getRow() - 1;
        int y = seat.getColumn() - 1;

        seats[x][y].setAvailable(false);
        availableSeats.remove(seats[x][y]);
        currentIncome += seats[x][y].getPrice();
        Purchase p = new Purchase();
        p.setTicket(seats[x][y]);
        purchaseList.add(p);
        return p;
    }

    public boolean wrongSeat(Seat seat){
        int x = seat.getRow() - 1;
        int y = seat.getColumn() - 1;
        if(x >= totalRows || y >= totalColumns || x < 0 || y < 0){
            return true;
        } else {
            return false;
        }

    }

    public ReturnTicket returnTicket(String token){
        ReturnTicket rt = new ReturnTicket();
        for(Purchase p : purchaseList){
            if(p.getToken().toString().equals(token)){
                rt.setReturnedTicket(p.getTicket());
                Seat s = p.getTicket();
                s.setAvailable(true);
                currentIncome -= s.getPrice();
                availableSeats.add(s);
                return rt;
            }
        }
        return null;
    }

    public boolean checkToken(String token){
        for(Purchase p : purchaseList){
            if(p.getToken().toString().equals(token)){
                return true;
            }
        }
        return false;
    }

    public void updateStat(){
        this.statistics.setCurrentIncome(currentIncome);
        this.statistics.setNumberOfAvailableSeats(availableSeats.size());
        this.statistics.setNumberOfPurchasedTickets(81 - availableSeats.size());
    }

    public Statistics returnStatistics() {
        return statistics;
    }
}
