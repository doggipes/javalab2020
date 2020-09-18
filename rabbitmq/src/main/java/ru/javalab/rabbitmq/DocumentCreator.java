package ru.javalab.rabbitmq;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;

import java.io.IOException;

public class DocumentCreator {

    public void createAkadem(User user){
        try {
            PdfDocument pdfDocument = new PdfDocument(new PdfReader("C:\\projects\\javalab2020\\rabbitmq\\src\\main\\java\\ru\\javalab\\rabbitmq\\example\\aka.pdf"),new PdfWriter("C:\\projects\\javalab2020\\rabbitmq\\src\\main\\java\\ru\\javalab\\rabbitmq\\result\\" + "akadem_" + user.getName() + "_" + user.getPass_number() + ".pdf"));
            PdfCanvas canvas = new PdfCanvas(pdfDocument.getFirstPage());
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(400, 235)
                    .showText(user.getName())
                    .endText();
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(400, 260)
                    .showText(user.getSurname())
                    .endText();
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(500, 205)
                    .showText(user.getAge() + " лет")
                    .endText();
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(400, 205)
                    .showText(user.getPass_number())
                    .endText();
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(210, 43)
                    .showText(user.getDate())
                    .endText();
            Document document = new Document(pdfDocument);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createUvolnenie(User user){
        try {
            PdfDocument pdfDocument = new PdfDocument(new PdfReader("C:\\projects\\javalab2020\\rabbitmq\\src\\main\\java\\ru\\javalab\\rabbitmq\\example\\uvol.pdf"),new PdfWriter("C:\\projects\\javalab2020\\rabbitmq\\src\\main\\java\\ru\\javalab\\rabbitmq\\result\\" + "otchislenie_" + user.getName() + "_" + user.getPass_number() + ".pdf"));
            PdfCanvas canvas = new PdfCanvas(pdfDocument.getFirstPage());
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(570, 235)
                    .showText(user.getName())
                    .endText();
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(535, 235)
                    .showText(user.getSurname())
                    .endText();
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(535, 220)
                    .showText(user.getAge() + " лет")
                    .endText();
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(550, 220)
                    .showText(user.getPass_number())
                    .endText();
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(350, 140)
                    .showText(user.getDate())
                    .endText();
            Document document = new Document(pdfDocument);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createOtchislenie(User user){
        try {
            PdfDocument pdfDocument = new PdfDocument(new PdfReader("C:\\projects\\javalab2020\\rabbitmq\\src\\main\\java\\ru\\javalab\\rabbitmq\\example\\otch.pdf"),new PdfWriter("C:\\projects\\javalab2020\\rabbitmq\\src\\main\\java\\ru\\javalab\\rabbitmq\\result\\" + "uvolnenie_" + user.getName() + "_" + user.getPass_number() + ".pdf"));
            PdfCanvas canvas = new PdfCanvas(pdfDocument.getFirstPage());
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(500, 378)
                    .showText("Test")
                    .endText();
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(460, 378)
                    .showText("Testov")
                    .endText();
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(460, 330)
                    .showText("18")
                    .endText();
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(500, 330)
                    .showText("1234 123456")
                    .endText();
            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 8)
                    .moveText(600, 75)
                    .showText("11/02/2000")
                    .endText();
            Document document = new Document(pdfDocument);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
