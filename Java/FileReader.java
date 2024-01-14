import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
    private String path;

    public FileReader(String path){
        this.path = path;
    }

    public List<String> lines() throws IOException {
        String filePath = this.path;
        return Files.readAllLines(Paths.get(filePath));
    }
}