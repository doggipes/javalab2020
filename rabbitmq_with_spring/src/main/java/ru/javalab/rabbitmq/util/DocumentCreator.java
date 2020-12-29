package ru.javalab.rabbitmq.util;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import ru.javalab.rabbitmq.model.entity.User;
import ru.javalab.rabbitmq.model.enums.Certificate;

import java.io.IOException;

public class DocumentCreator {
    private String AKADEM = "C:\\projects\\javalab2020\\rabbitmq\\src\\main\\java\\ru\\javalab\\rabbitmq\\example\\aka.pdf";
    private String UVOLNENIE = "C:\\projects\\javalab2020\\rabbitmq\\src\\main\\java\\ru\\javalab\\rabbitmq\\example\\uvol.pdf";
    private String OTCHISLENIE = "C:\\projects\\javalab2020\\rabbitmq\\src\\main\\java\\ru\\javalab\\rabbitmq\\example\\otch.pdf";

    public String createDocument(Certificate type, User user){
        if(type.getUrl().equals(""))
            return null;
        try {
            String filename = "C:\\projects\\javalab2020\\rabbitmq_with_spring\\src\\main\\resources\\results\\" + type.name().toLowerCase() + "_" + user.getName() + "_" + user.getPass_number().replace(" ", "_") + ".pdf";
            PdfDocument pdfDocument = new PdfDocument(new PdfReader(type.getUrl()),new PdfWriter(filename));
            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, true);
            form.getField("name").setReadOnly(true);
            form.getField("name").setValue(user.getName());

            form.getField("surname").setReadOnly(true);
            form.getField("surname").setValue(user.getSurname());

            form.getField("age").setReadOnly(true);
            form.getField("age").setValue(user.getAge() + " лет");

            form.getField("pass_number").setReadOnly(true);
            form.getField("pass_number").setValue(user.getPass_number());

            form.getField("date").setReadOnly(true);
            form.getField("date").setValue(user.getDate());
            Document document = new Document(pdfDocument);
            document.close();
            pdfDocument.close();
            return filename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
