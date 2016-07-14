package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.io.source.RandomAccessFileOrArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

public class PdfDocumentcreation {

	public static final String tempdest = "src/application/temp.pdf";

	public static PdfDocument createFromOld(String source) throws FileNotFoundException, IOException {

		PdfDocument uploadeddocument = new PdfDocument(new PdfReader(source));

		PdfDocument newdocument = new PdfDocument(new PdfWriter(tempdest));
		
		
		int noofpages = uploadeddocument.getNumberOfPages();
		
		

		uploadeddocument.copyPagesTo(1, noofpages, newdocument);
		
		Document newone = new Document(newdocument);
		
		//newone.close();
		
		System.out.println("no of pages :"+noofpages);
		
		return newdocument;

	}

}
