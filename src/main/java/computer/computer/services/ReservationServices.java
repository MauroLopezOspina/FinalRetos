package computer.computer.services;

import computer.computer.modelo.Client;
import computer.computer.modelo.DTOs.CompletedAndCancelled;
import computer.computer.modelo.DTOs.TotalAndClient;
import computer.computer.modelo.Reservation;
import computer.computer.repositorio.ClientRepository;
import computer.computer.repositorio.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServices {
    @Autowired
    private ReservationRepository reservationRepositorio;

    public List<Reservation> getAll() {
        return reservationRepositorio.getAll();


    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepositorio.getReservation(id);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepositorio.save(reservation);


        } else {
            Optional<Reservation> opt = reservationRepositorio.getReservation(reservation.getIdReservation());
            if (opt.isEmpty()) {
                return reservationRepositorio.save(reservation);

            } else {

                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> op = reservationRepositorio.getReservation(reservation.getIdReservation());
            if (op.isPresent()) {

                if (reservation.getStatus() != null) {
                    op.get().setStatus(reservation.getStatus());


                }
                if (reservation.getStartDate() != null) {
                    op.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    op.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getScore() != null) {
                    op.get().setScore(reservation.getScore());
                }

                reservationRepositorio.save(op.get());
                return op.get();

            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }

    public boolean delete(int id) {
        boolean flag = false;
        Optional<Reservation> reservation = reservationRepositorio.getReservation(id);
        if (reservation.isPresent()) {
            reservationRepositorio.delete(reservation.get());
            flag = true;
        }
        return flag;
    }

    /*public Status getStatus() {
        Status status = new Status();
        List<Reservation> reservations = reservationRepositorio.getAll();
        int contF = 0;
        int contA = 0;
        for (Reservation res: reservations) {
            if (res.getStatus().equals("completed")) {
                contF = contF + 1;


            } else if (res.getStatus().equals("cancelled")) {
                contA = contA + 1;
            }

        }
        status.setComplete(contF);
        status.setCancelled(contA);
        return status;
    }

    public List<ReportClient> getReportClient() {
        List<ReportClient> repoclient = new ArrayList<ReportClient>();
        List<Client> client = ClientRepository.getAll();
        int total = 0;
        for (Reservation res : client.getReservation()) {
            total = total + 1;
        }
        ReportClient reportClient = new ReportClient();
        reportClient.setTotal(total);
        reportClient.setClient(client);
        repoclient.add(reportClient);
        total = 0;


        return repoclient;
    }

    public List<Reservation>getReportDates(Date date1,Date date2) {
        List<Reservation> reservations = reservationRepositorio.getAll();
        List<Reservation> reservationsDate = new ArrayList<Reservation>();
        Reservation res;
        for (date1.compareTo(res.getStartDate()) * res.getStartDate().compareTo(date2) >= 0) {
            reservationsDate.add(res);


        }
        return reservationsDate;
    }

    }

*/

public List<Reservation>getReservationsBetweenDatesReport(String fechaA,String fechaB){
    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
    Date a= new Date();
    Date b= new Date();
    try {
         a= parser.parse(fechaA);
         b= parser.parse(fechaB);

    }catch (ParseException exception){
        exception.printStackTrace();
    }
    if (a.before(b)){
        return reservationRepositorio.getReservationsBetweenDates(a,b);

    }else {
        return new  ArrayList<>();

    }
}
public CompletedAndCancelled getReservationStatusReport(){
    List<Reservation> completed= reservationRepositorio.getReservationsByStatus("completed");
    List<Reservation> cancelled= reservationRepositorio.getReservationsByStatus("cancelled");
    int cantidadcompletadas= completed.size();
    int cantidadcanceladas = cancelled.size();

    return new CompletedAndCancelled ((long) cantidadcompletadas,(long) cantidadcanceladas);
}
public List<TotalAndClient> getTopClientsReport(){
    return reservationRepositorio.getTopClients();
}
}


