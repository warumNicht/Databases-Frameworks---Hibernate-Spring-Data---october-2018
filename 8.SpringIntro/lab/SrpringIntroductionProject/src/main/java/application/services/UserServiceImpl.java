package application.services;

import application.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import application.repositories.UserRepository;
@Service
@Primary
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        if(this.userRepository.getByUsername(user.getUsername())==null){
            this.userRepository.save(user);
        }else {
            System.out.printf("User with %s already exists!%n",user.getUsername());
        }
    }
}
