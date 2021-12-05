import java.io.IOException;
import java.net.URI;
import java.util.List;

public class WriteOpenedTasks {

    public void writeOpenedToDoTasks(URI uri) throws IOException, InterruptedException {
        List<ToDoTask> tasks = JsonPaceHolder.sendGetTasks(uri);
        for (ToDoTask task : tasks) {
            if (!task.isCompleted()){
                System.out.println(task);
            }
        }
    }
}
