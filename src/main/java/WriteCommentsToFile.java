import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class WriteCommentsToFile {


    private String filePath;

    public void writeComments(URI postsURI, URI commentsURI) throws IOException, InterruptedException {
        List<Post> posts = JsonPaceHolder.sendGetPosts(postsURI);
        int maxID = 0;
        Post maxIdPost = null;
        for (Post post: posts) {
            if (post.getId() > maxID){
                maxID = post.getId();
                maxIdPost = post;
            }
        }
        filePath = "src/main/java/"+"user-"+ maxIdPost.getUserId() +"-post-"+maxIdPost.getId()+"-comments.json";
        List<Comment> allComments = JsonPaceHolder.sendGetComments(commentsURI);
        List<Comment> lastPostComments = new ArrayList<>();
        for (Comment comment : allComments) {
            if (comment.getPostId() == maxID){
                lastPostComments.add(comment);
            }
        }
        writeToFile(lastPostComments);
    }

    private void writeToFile(List<Comment> commentsToFile) {
        File userJson = new File(filePath);

        try (FileWriter writer = new FileWriter(userJson)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(commentsToFile, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
