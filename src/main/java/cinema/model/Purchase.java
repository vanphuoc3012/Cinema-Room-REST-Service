package cinema.model;

import java.util.UUID;

public class Purchase {
    private UUID token;
    private Seat ticket;

    public Purchase(Seat ticket) {
        this();
        this.ticket = ticket;
    }

    public Purchase() {
        this.token = UUID.randomUUID();
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
