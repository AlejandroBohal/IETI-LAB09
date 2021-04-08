package eci.ieti;

import eci.ieti.data.CustomerRepository;
import eci.ieti.data.ProductRepository;
import eci.ieti.data.TodoRepository;
import eci.ieti.data.UserRepository;
import eci.ieti.data.model.Customer;
import eci.ieti.data.model.Product;

import eci.ieti.data.model.Todo;
import eci.ieti.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

        customerRepository.deleteAll();
        todoRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
        Calendar c = Calendar.getInstance();
        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Marley"));
        customerRepository.save(new Customer("Jimmy", "Page"));
        customerRepository.save(new Customer("Freddy", "Mercury"));
        customerRepository.save(new Customer("Michael", "Jackson"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        
        customerRepository.findAll().stream().forEach(System.out::println);
        System.out.println();
        
        productRepository.deleteAll();

        productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
        productRepository.save(new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
        productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
        productRepository.save(new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
        productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
        productRepository.save(new Product(6L, "Samsung S10 plus", "All new mobile phone Samsung S10 plus"));
        productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
        productRepository.save(new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
        productRepository.save(new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));
        
        System.out.println("Paginated search of products by criteria:");
        System.out.println("-------------------------------");
        productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 2)).stream()
        	.forEach(System.out::println);
        System.out.println();

        System.out.println("Prueba paginación");

        productRepository.findAll(PageRequest.of(0,4)).stream().forEach(System.out::println);

        System.out.println();
        System.out.println("Queries");

        User user1 = userRepository.save(new User(1,"Will Smith","WS@mail.com"));
        User user2 = userRepository.save(new User(2,"Charles Bukowsky","CB@mail.com"));
        User user3 = userRepository.save(new User(3,"Graham Bell","GB@mail.com"));
        User user4 = userRepository.save(new User(4,"Bad Bunny","BB@mail.com"));
        User user5 = userRepository.save(new User(5,"Freddy Mercury","FM@mail.com"));
        User user6 = userRepository.save(new User(6,"Odio Ieti","OdioIeti@mail.com"));
        User user7 = userRepository.save(new User(7,"Testeando Ando","TA@mail.com"));
        User user8 = userRepository.save(new User(8,"Alan Brito","AB@mail.com"));
        User user9 = userRepository.save(new User(9,"Viviana Love","VL@mail.com"));
        User user10 = userRepository.save(new User(10,"Stephany Crazy","SP@mail.com"));

        todoRepository.save(new Todo("a large descriptioooooooooooooooooooooooooooooooooooooooooooooooonooooooooooonnnnn",10,addDays(new Date(),150),user2,"Ready"));
        todoRepository.save(new Todo("tengo hambre",100,addDays(new Date(),-130),user2,"In Progress"));
        todoRepository.save(new Todo("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm",121,addDays(new Date(),130),user2,"Completed"));
        todoRepository.save(new Todo("tengo sueño",1000,addDays(new Date(),0),user2,"Ready"));
        todoRepository.save(new Todo("hola mundo2",10,addDays(new Date(),-31),user1,"Ready"));
        todoRepository.save(new Todo("la patada del mocho 1",2,addDays(new Date(),3),user1,"In Progress"));
        todoRepository.save(new Todo("la patada del mocho 2",3,addDays(new Date(),-33),user1,"Ready"));
        todoRepository.save(new Todo("la patada del mocho 3",4,addDays(new Date(),53),user6,"Completed"));
        todoRepository.save(new Todo("toc toc",10,addDays(new Date(),312),user6,"Ready"));
        todoRepository.save(new Todo("who are there",100,addDays(new Date(),40),user1,"Completed"));
        todoRepository.save(new Todo("its me",1000,addDays(new Date(),10),user9,"Ready"));
        todoRepository.save(new Todo("hating",10,addDays(new Date(),21),user9,"Completed"));
        todoRepository.save(new Todo("this",10,addDays(new Date(),-33),user10,"Ready"));
        todoRepository.save(new Todo("holita",100,addDays(new Date(),-30),user9,"In Progress"));
        todoRepository.save(new Todo("Crazy toc",1000,addDays(new Date(),-38),user10,"Ready"));
        todoRepository.save(new Todo("Breaking bad",2,addDays(new Date(),32),user10,"Completed"));
        todoRepository.save(new Todo("the good doctor",3,addDays(new Date(),-35),user8,"In Progress"));
        todoRepository.save(new Todo("doctor house",5,addDays(new Date(),31),user7,"Ready"));
        todoRepository.save(new Todo("can i take a photo?",23,addDays(new Date(),34),user5,"Completed"));
        todoRepository.save(new Todo("hello world todo",45,addDays(new Date(),-13),user6,"In Progress"));
        todoRepository.save(new Todo("bored todo",23,addDays(new Date(),13),user5,"Completed"));
        todoRepository.save(new Todo("complex todo",11,addDays(new Date(),63),user4,"In Progress"));
        todoRepository.save(new Todo("simple todo",23,addDays(new Date(),42),user3,"Ready"));
        todoRepository.save(new Todo("omg thats a really really largeeeeeeeeeeeeeeeeeeeeeeeee descriptiooooooooooooooooonnnnn",100,addDays(new Date(),-43),user4,"In Progress"));
        todoRepository.save(new Todo("a really large descriptiooooooooooooooooooooooooooooooooooooooooooooooooooooooooon",1000,addDays(new Date(),-42),user3,"In Progress"));

        // QUERY 1
        // Todos where the dueDate has expired
        System.out.println("-----QUERY 1--------");
        Query query = new Query();
        query.addCriteria(Criteria.where("dueDate").lt(new Date()));

        List<Todo> oldTodos = mongoOperation.find(query,Todo.class);
        System.out.println("Expired Todos: ");
        oldTodos.forEach(System.out::println);
        System.out.println();

        // QUERY 2
        // Todos that are assigned to given user and have priority greater equal to 5
        System.out.println("-------------------------------");
        System.out.println("Search by responsible");
        System.out.println("-------------------------------");
        Query query2 = new Query();
        query2.addCriteria(Criteria.where("priority").gte(5).andOperator(Criteria.where("responsible").is(user2)));
        List<Todo> todosByResponsible = mongoOperation.find(query2,Todo.class);
        todosByResponsible.forEach(System.out::println);
        System.out.println();

        // QUERY 3
        // This query can't be implemented because the users must have a collection of todos.
        // Users that have assigned more than 2 Todos.

        System.out.println("-------------------------------");
        System.out.println("the user must have a list of todos");
        System.out.println("-------------------------------");
        System.out.println();
        // QUERY 4
        //Todos that contains a description with a length greater than 30 characters

        System.out.println("-------------------------------");
        System.out.println("Todos which description is longer than 30 characters");
        System.out.println("-------------------------------");
        Query query4 = new Query();
        query4.addCriteria(Criteria.where("description").regex("^.{30,}"));
        List<Todo> largeDescriptionTodos = mongoOperation.find(query4,Todo.class);
        largeDescriptionTodos.forEach(System.out::println);

    }
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

}
