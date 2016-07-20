package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.io.source.RandomAccessFileOrArray;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextMarkupAnnotation;
import com.itextpdf.layout.Document;



public class PdfDocumentcreation {

	static PdfDocument myDocument;

	public static final String tempdest = "src/application/temp.pdf";

	public static String createFromOld(String source) throws FileNotFoundException, IOException {

		PdfDocument uploadeddocument = new PdfDocument(new PdfReader(source));

		PdfDocument newdocument = new PdfDocument(new PdfWriter(tempdest));


		int noofpages = uploadeddocument.getNumberOfPages();

		uploadeddocument.copyPagesTo(1, noofpages, newdocument);

		Document newone = new Document(newdocument);

		newone.close();

		System.out.println("no of pages :"+noofpages);

		return tempdest;
	}

	public static boolean addAnnotation() {

		//For my requirements document, by first page dimensions are 0-595 in x, 0-842 in y, where origin is lower left.


		//Basic test case for each type of annotation
		Annotations.addHighlightAnnotation(myDocument, 100,100,100,100,1);
//		Annotations.addHighlightAnnotation(myDocument, 300,300,50,75,2);
		Annotations.addUnderlineAnnotation(myDocument, 100,100,100,100,3);
		Annotations.addStrikeThroughAnnotation(myDocument, 100,100,100,100,4);
		Annotations.addBoxAnnotation(myDocument, 100,100,100,20,5);

		Annotations.printAnnots(myDocument, 1);
		Annotations.printAnnots(myDocument, 2);
		Annotations.printAnnots(myDocument, 3);
		Annotations.printAnnots(myDocument, 4);
		Annotations.printAnnots(myDocument, 5);

		Annotations.deleteAnnot(myDocument, 3, 242, 595);

		Annotations.printAnnots(myDocument, 3);

		return true;
	}

	public static String openPDF(String source) throws IOException {

		PdfDocument uploadeddocument = new PdfDocument(new PdfReader(source));
		myDocument = new PdfDocument(new PdfWriter(tempdest));

		int noofpages = uploadeddocument.getNumberOfPages();

		uploadeddocument.copyPagesTo(1, noofpages, myDocument);

		uploadeddocument.close();

		return tempdest;
	}

	public static boolean savePDF() {

		Document newone = new Document(myDocument);

		newone.close();

		myDocument.close();

		return true;
	}

}
