package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.hibernate.sql.Insert;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);


        CarService carService = context.getBean(CarService.class);
        carService.addCar(new Car("BMW", 1));
        carService.addCar(new Car("BMW", 2));
        carService.addCar(new Car("BMW", 3));
        carService.addCar(new Car("BMW", 4));

        UserService userService = context.getBean(UserService.class);
        List<Car> cars = carService.getAllCars();
        userService.addUser(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.addUser(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.addUser(new User("User3", "Lastname3", "user3@mail.ru", cars.get(0)));
        userService.addUser(new User("User4", "Lastname4", "user4@mail.ru"));


        userService.addUser(new User("NAME", "LAST", "EMAIL", cars.get(2)));

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("Вывод пользователя по модели и серии машины: " + userService.getUserCar(cars.get(0)));
        System.out.println("Вывод пользователя по модели и серии машины: " + userService.getUserCar(cars.get(1)));
        context.close();
    }

}
