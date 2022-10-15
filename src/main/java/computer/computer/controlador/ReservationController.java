package computer.computer.controlador;


import computer.computer.modelo.DTOs.CompletedAndCancelled;
import computer.computer.modelo.DTOs.TotalAndClient;
import computer.computer.modelo.Reservation;
import computer.computer.services.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class ReservationController {
    @Autowired
    private ReservationServices reservationServices;

    @GetMapping("/all")
    public List<Reservation> getReservations() {
        return reservationServices.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int reservationId) {
        return reservationServices.getReservation(reservationId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation) {
        return reservationServices.save(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationServices.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return reservationServices.delete(id);
    }



@GetMapping("/report-dates/{fecha1}/{fecha2}")
public List<Reservation> getReservationsBetweenDatesReport(@PathVariable("fecha1")String fecha1,@PathVariable("fecha2")String fecha2){
    return reservationServices.getReservationsBetweenDatesReport(fecha1,fecha2);
}

@GetMapping("/report-status")
public CompletedAndCancelled getReservationsStatusReport(){
    return reservationServices.getReservationStatusReport();
}

@GetMapping("/report-clients")
public List<TotalAndClient> getTopClientsReport(){
    return reservationServices.getTopClientsReport();

}
}