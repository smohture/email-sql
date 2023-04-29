package excel.sql.email.emailsql;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class GenerateQRCode {

    public static void generateQR(Student student) throws WriterException, IOException {

        String path ="D:\\QR code\\"+student.getEmail()+".png";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode("Name: "+student.getName()+"\n"+"Registered: "+(student.getAmountRemaining() == 0),BarcodeFormat.QR_CODE,400,400);
        Path pathObj = FileSystems.getDefault().getPath(path);
        MatrixToImageWriter.writeToPath(bitMatrix,"PNG",pathObj);

    }

    public static void generateQRForAll(List<Student> students) throws IOException, WriterException {

        for(Student entity: students )
        {
            generateQR(entity);
        }

    }
}
