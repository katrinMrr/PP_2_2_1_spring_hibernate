package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.query.Query;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();

    User getUserCar(Car car);
}


