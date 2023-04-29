package excel.sql.email.emailsql;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Component
public class StudentService {
    @Autowired
    private StudentRepository repository;
    public void insertAllRecords(MultipartFile file) throws IOException, WriterException {

        if(StudentHelper.checkIfExcel(file)){
            List<Student> students = StudentHelper.excelToList(file.getInputStream());
            repository.saveAll(students);

            GenerateQRCode.generateQRForAll(students);
        }

    }

    public List<Student> getAllRecords(){
        return repository.findAll();    }

    public void generateEmail() {
        String emailFrom = "smohture01@gmail.com";
        String content = "QR code attached";
        String subject = "QR code attached";
        List<Student> students = repository.findAll();
        for(Student entity: students){
            try {
                SendEmail.generateEmail(emailFrom, subject,content,entity.getEmail());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
