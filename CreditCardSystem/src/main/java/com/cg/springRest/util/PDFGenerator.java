package com.cg.springRest.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cg.springRest.model.Transaction;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {
	
	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);
	
	public static ByteArrayInputStream customerPDFReport(List<Transaction> transactions) {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
        	
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph( "Customer Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
        	
        	PdfPTable table = new PdfPTable(5);
        	// Add PDF Table Header ->
			Stream.of("trans_id", "trans_value", "trans_date","trans_payment_method","credit_card")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setBorderWidth(2);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
            
            for (Transaction transaction : transactions) {
            	PdfPCell idCell = new PdfPCell(new Phrase(Long.toString(transaction.getTransId())));
            	idCell.setPaddingLeft(4);
            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell valueCell = new PdfPCell(new Phrase(Long.toString(transaction.getTransValue())));
                valueCell.setPaddingLeft(4);
                valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                valueCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(valueCell);

                PdfPCell transdateCell = new PdfPCell(new Phrase(String.valueOf(transaction.getTransDate().toString())));
                transdateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                transdateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                transdateCell.setPaddingRight(4);
                table.addCell(transdateCell);
                
                PdfPCell transpaymentCell = new PdfPCell(new Phrase(String.valueOf(transaction.getPaymentMethod())));
                transpaymentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                transpaymentCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                transpaymentCell.setPaddingRight(4);
                table.addCell(transpaymentCell);
                
                PdfPCell creditcardCell = new PdfPCell(new Phrase(String.valueOf(Long.toString(transaction.getcreditCardNumber()))));
                creditcardCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                creditcardCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                creditcardCell.setPaddingRight(4);
                table.addCell(creditcardCell);
                
                
            }
            document.add(table);
            
            document.close();
        }catch(DocumentException e) {
        	logger.error(e.toString());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}
}