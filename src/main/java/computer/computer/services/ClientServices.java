package computer.computer.services;

import computer.computer.modelo.Client;
import computer.computer.repositorio.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServices {
    @Autowired
    private ClientRepository clientRepositorio;

    public List<Client> getAll() {
        return clientRepositorio.getAll();


    }
    public Optional<Client> getClient(int id){
        return clientRepositorio.getClient(id);
    }

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepositorio.save(client);


        } else {
            Optional<Client> opt = clientRepositorio.getClient(client.getIdClient());
            if (opt.isEmpty()) {
                return clientRepositorio.save(client);

            } else {

                return client;
            }
        }
    }
    public Client update (Client client){
        if(client.getIdClient()!=null){
            Optional <Client> op = clientRepositorio.getClient(client.getIdClient());
            if (op.isPresent()){
                if (client.getName()!=null){
                    op.get().setName(client.getName());

                }
                if (client.getEmail()!=null){
                    op.get().setEmail(client.getEmail());

                }
                if (client.getAge()!=null){
                    op.get().setAge(client.getAge());
                }
                if (client.getPassword()!=null){
                    op.get().setPassword(client.getPassword());
                }
                clientRepositorio.save(op.get());
                return op.get();

            }else {
                return client;
            }
        }else {
            return client;
        }
    }
    public boolean delete(int id){
        boolean flag=false;
        Optional<Client>client=clientRepositorio.getClient(id);
        if (client.isPresent()){
            clientRepositorio.delete(client.get());
            flag=true;
        }
        return flag;
    }
}