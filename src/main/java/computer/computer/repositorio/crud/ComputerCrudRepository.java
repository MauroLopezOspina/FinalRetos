package computer.computer.repositorio.crud;

import computer.computer.modelo.Computer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComputerCrudRepository extends CrudRepository<Computer,Integer> {


}
