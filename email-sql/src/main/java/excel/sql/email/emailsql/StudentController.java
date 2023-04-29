package excel.sql.email.emailsql;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("/upload")
    public ResponseEntity uploadExcel(@RequestParam("file") MultipartFile file) throws IOException, WriterException {
        service.insertAllRecords(file);
        return ResponseEntity.ok("File is uploaded");
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/getAll")
    public List<Student> getAllRecord(){
        return service.getAllRecords();
    }

    @GetMapping("/emails")
    public String generateEmail(){
        service.generateEmail();
        return "Email generated";
    }

}
