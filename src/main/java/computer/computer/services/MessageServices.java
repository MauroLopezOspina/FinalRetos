package computer.computer.services;

import computer.computer.modelo.Message;
import computer.computer.repositorio.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServices {
    @Autowired
    private  MessageRepository messageRepository;

    public MessageServices(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAll() {
        return messageRepository.getAll();


    }

    public Optional<Message> getMessage(int messageId) {
        return messageRepository.getMessage(messageId);
    }

    public Message save(Message message) {
        if (message.getIdMessage() == null) {
            return messageRepository.save(message);


        } else {
            Optional<Message> opt = messageRepository.getMessage(message.getIdMessage());
            if (opt.isEmpty()) {
                return messageRepository.save(message);

            } else {

                return message;
            }
        }
    }
    public Message update(Message message){
        if (message.getIdMessage()!=null){
            Optional<Message> e= messageRepository.getMessage(message.getIdMessage());
            if (!e.isEmpty()){
                if (message.getMessageText()!=null){
                    e.get().setMessageText(message.getMessageText());
                }
                messageRepository.save(e.get());
                return e.get();
            }else {
                return message;
            }
        }else {
            return message;
        }
    }




    public boolean delete(int id){
        boolean flag=false;
        Optional<Message>message=messageRepository.getMessage(id);
        if (message.isPresent()){
           messageRepository.delete(message.get());
            flag=true;
        }
        return flag;
    }
}