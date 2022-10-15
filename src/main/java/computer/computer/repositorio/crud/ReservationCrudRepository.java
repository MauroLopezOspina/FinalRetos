package computer.computer.repositorio.crud;

import computer.computer.modelo.Computer;
import computer.computer.modelo.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {



/*
*/

  public List<Reservation> findAllByStartDateAfterAndDevolutionDateBefore(Date fechaA, Date fechaB);

/*
*/

public List<Reservation>findAllByStatus(String status);


@Query("SELECT c.client,COUNT (c.client)FROM Reservation AS c GROUP BY c.client ORDER BY COUNT (c.client)DESC ")


        public List<Object[]>getTotalReservationsByClient();


/*

 */
}