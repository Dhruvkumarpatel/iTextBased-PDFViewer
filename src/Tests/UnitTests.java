package Tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.*;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.filter.TextRegionEventFilter;
import com.itextpdf.kernel.pdf.canvas.parser.listener.FilteredTextEventListener;
import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;

import application.Annotations;
import com.itextpdf.kernel.geom.Rectangle;

public class UnitTests {

	public boolean checkInputFile(String filename){
		return filename.endsWith(".pdf") && filename != null ;
	}
	
	public boolean checkFileSave(String fileToSave){
		File srcDir = new File("src/application/temp2.pdf");
		File destDir = new File(fileToSave);
		try {
			FileUtils.copyFile(srcDir, destDir);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	@Test
	public void testInputFile() {
		String fileName  = "temp.pdf";
		assert(checkInputFile(fileName));
		System.out.println("Unit Test testInputFile: Passed!!");
	}
	
	@Test
	public void testFileSave() {
		String fileToSave  = "src/application/tempSave.pdf";
		assert(checkFileSave(fileToSave));
		System.out.println("Unit Test testFileSave: Passed!!");
	}
	
	@Test
	public void testAddUnderlineAnnotation() throws IOException {
		String fileName  = "src/application/temp.pdf";
		@SuppressWarnings("resource")
		PdfDocument myDocument = new PdfDocument(new PdfReader(fileName));
		float x = (float) 10.0; 
		float y = (float) 10.0; 
		float width = (float) 5.0;
		float height = (float) 1.0;
		int pageNum = 1;
		assert(Annotations.addUnderlineAnnotation(myDocument, x, y, width, height, pageNum));
		System.out.println("Unit Test testAddUnderlineAnnotation: Passed!!");
	}
	
	@Test
	public void testAddHighlightAnnotation() throws IOException {
		String fileName  = "src/application/temp.pdf";
		@SuppressWarnings("resource")
		PdfDocument myDocument = new PdfDocument(new PdfReader(fileName));
		float x = (float) 10.0; 
		float y = (float) 10.0; 
		float width = (float) 5.0;
		float height = (float) 1.0;
		int pageNum = 1;
		assert(Annotations.addHighlightAnnotation(myDocument, x, y, width, height, pageNum));
		System.out.println("Unit Test testAddHighlightAnnotation: Passed!!");
	}
	
	@Test
	public void testAddStrikeThroughAnnotation() throws IOException {
		String fileName  = "src/application/temp.pdf";
		@SuppressWarnings("resource")
		PdfDocument myDocument = new PdfDocument(new PdfReader(fileName));
		float x = (float) 10.0; 
		float y = (float) 10.0; 
		float width = (float) 5.0;
		float height = (float) 1.0;
		int pageNum = 1;
		assert(Annotations.addStrikeThroughAnnotation(myDocument, x, y, width, height, pageNum));
		System.out.println("Unit Test testAddStrikeThroughAnnotation: Passed!!");
	}
	
	@Test
	public void testAddBoxAnnotation() throws IOException {
		String fileName  = "src/application/temp.pdf";
		@SuppressWarnings("resource")
		PdfDocument myDocument = new PdfDocument(new PdfReader(fileName));
		float x = (float) 10.0; 
		float y = (float) 10.0; 
		float width = (float) 5.0;
		float height = (float) 1.0;
		int pageNum = 1;
		assert(Annotations.addBoxAnnotation(myDocument, x, y, width, height, pageNum));
		System.out.println("Unit Test testAddBoxAnnotation: Passed!!");
	}
	
	@Test
	//deleteAnnot(PdfDocument myDocument, int pageNum, float x, float y)
	public void testDeleteAnnot() throws IOException {
		String fileName  = "src/application/temp.pdf";
		@SuppressWarnings("resource")
		PdfDocument myDocument = new PdfDocument(new PdfReader(fileName));
		float x = (float) 10.0; 
		float y = (float) 10.0; 
		float width = (float) 5.0;
		float height = (float) 1.0;
		int pageNum = 1;
		assert(Annotations.addBoxAnnotation(myDocument, x, y, width, height, pageNum));
		System.out.println("Unit Test testAddBoxAnnotation: Passed!!");
		assert(testDeleteAnnot(myDocument, x, y, width, height, pageNum));
		System.out.println("Unit Test testDeleteAnnot: Passed!!");
	}
	
	public boolean testDeleteAnnot(PdfDocument myDocument, float x, float y, float width, float height, int pageNum) {
		Annotations.deleteAnnot(myDocument, pageNum, x, y);
		Rectangle rect = new Rectangle(x, y, width, height);
		TextRegionEventFilter regionFilter = new TextRegionEventFilter(rect);
		ITextExtractionStrategy strategy = new FilteredTextEventListener(new LocationTextExtractionStrategy(), regionFilter);
		String str = PdfTextExtractor.getTextFromPage(myDocument.getPage(pageNum), strategy);
		return str == null? true : false;
	}

}
