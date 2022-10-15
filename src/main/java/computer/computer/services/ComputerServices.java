package computer.computer.services;

import computer.computer.modelo.Computer;
import computer.computer.repositorio.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerServices {
    @Autowired
    private ComputerRepository computerRepository;

    public List<Computer> getAll() {
        return computerRepository.getAll();


    }
    public Optional<Computer> getComputer(int id){
        return computerRepository.getComputer(id);
    }

    public Computer save(Computer computer) {
        if (computer.getId() == null) {
            return computerRepository.save(computer);


        } else {
            Optional<Computer> opt = computerRepository.getComputer(computer.getId());
            if (opt.isEmpty()) {
                return computerRepository.save(computer);

            } else {

                return computer;
            }
        }
    }
    public Computer update (Computer computer){
        if(computer.getId()!=null){
            Optional <Computer> op = computerRepository
                    .getComputer(computer.getId());
            if (op.isPresent()){
                if (computer.getName()!=null){
                    op.get().setName(computer.getName());

                }
                if (computer.getBrand()!=null){
                    op.get().setBrand(computer.getBrand());

                }

                computerRepository.save(op.get());
                return op.get();

            }else {
                return computer;
            }
        }else {
            return computer;
        }
    }
    public boolean delete(int id){
        boolean flag=false;
        Optional<Computer>computer=computerRepository.getComputer(id);
        if (computer.isPresent()){
            computerRepository.delete(computer.get());
            flag=true;
        }
        return flag;
    }
}