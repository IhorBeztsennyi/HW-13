import java.io.IOException;
import java.net.URI;
import java.util.List;

public class Main {

    private static final String USER_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String UPDATE_USER_URL = "https://jsonplaceholder.typicode.com/users/1";
    private static final String REMOVE_USER_URL = "https://jsonplaceholder.typicode.com/users/1";
    private static final String GET_POSTS_URL = "https://jsonplaceholder.typicode.com/users/1/posts";
    private static final String GET_COMMENTS_URL = "https://jsonplaceholder.typicode.com/posts/10/comments";
    private static final String GET_TASKS_URL = "https://jsonplaceholder.typicode.com/users/1/todos";


    public static void main(String[] args) throws IOException, InterruptedException {

        User user = createDefaultUser();

        // Task1
        System.out.println("Task 1");

        User createdUser = JsonPaceHolder.sendPost(URI.create(USER_URL), user);
        System.out.println("Created user = " + createdUser);

        User changedUser = JsonPaceHolder.sendPut(URI.create(UPDATE_USER_URL), user);
        System.out.println("Updated user = " + changedUser);

        User userDelete = JsonPaceHolder.sendPost(URI.create(USER_URL), user);
        int statusCode = JsonPaceHolder.sendDelete(URI.create(REMOVE_USER_URL), userDelete);
        System.out.println("Status code of the removing = " + statusCode);

        List<User> users = JsonPaceHolder.sendGetWithListOfUsers(URI.create(USER_URL));
        System.out.println("Users list = " + users);

        User userById = JsonPaceHolder.sendGetUserById(USER_URL, 2);
        System.out.println("User by ID = " + userById);

        List<User> userByUserName = JsonPaceHolder.sendGetUserByUserName(USER_URL, "Antonette");
        System.out.println("User by user name = " +userByUserName);

        // Task2
        WriteCommentsToFile writeCommentsToFile = new WriteCommentsToFile();
        writeCommentsToFile.writeComments(URI.create(GET_POSTS_URL), URI.create(GET_COMMENTS_URL));

        // Task3

        System.out.println("Task 3");
        WriteOpenedTasks writeOpenedTasks = new WriteOpenedTasks();
        writeOpenedTasks.writeOpenedToDoTasks(URI.create(GET_TASKS_URL));
    }

    public static User createDefaultUser(){
        User user = new User();
        user.setId(2000);
        user.setUsername("IhorBeztsennyi");
        user.setName("Ihor Beztsennyi");
        user.setPhone("555-5555-5555");
        return user;
    }
}
