package cinema.controller;

import cinema.model.MovieTheater;
import cinema.model.MyError;
import cinema.model.Seat;
import cinema.model.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TheaterController {
    private MovieTheater mTheater = new MovieTheater();

    @GetMapping("/seats")
    public MovieTheater theater(){
        return mTheater;
    }

    @PostMapping("/purchase")
    public ResponseEntity purchase(@RequestBody Seat seat){
        if(mTheater.wrongSeat(seat)){
            MyError e = new MyError("The number of a row or a column is out of bounds!");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        } else {
            if(mTheater.checkTicketAvailable(seat)){
                return new ResponseEntity<>(mTheater.sellTicket(seat), HttpStatus.OK);
            } else {
                MyError e = new MyError("The ticket has been already purchased!");
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(e);
            }
        }

    }

    @PostMapping("/return")
    public ResponseEntity returnTicket(@RequestBody Token token){
        System.out.println(token);
        String t = token.getToken();
        if(mTheater.checkToken(t)){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mTheater.returnTicket(t));
        } else {
            MyError e = new MyError("Wrong token!");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }

    @PostMapping("/stats")
    public ResponseEntity stats(@RequestParam(name = "password", required = false) String password) {
        if (password == null || !password.equals("super_secret")) {
            MyError e = new MyError("The password is wrong!");
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e);
        } else {
            mTheater.updateStat();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mTheater.returnStatistics());

        }
    }


}
