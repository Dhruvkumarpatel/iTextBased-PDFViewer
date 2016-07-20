package application;

import java.util.List;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfPolyGeomAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextMarkupAnnotation;

public class Annotations {

	/**
	 *	The four coordinates of a rectangle are upper left, lower left, upper right, lower right,
	 *  which correspond to coordinate pairs (x,y+height), (x,y), (x+width, y+height), (x+width, y)
	 *
	 * @param myDocument
	 * @param x lower left x coordinate of a rectangle
	 * @param y lower left y coordinate of a rectangle
	 * @param width of rectangle
	 * @param height of rectangle
	 * @return
	 */
	static boolean addHighlightAnnotation(PdfDocument myDocument, float x, float y, float width, float height, int pageNum) {
		assert(myDocument != null);
		assert (x >= 0 && x <= myDocument.getDefaultPageSize().getRight());
		assert (y >= 0 && y <= myDocument.getDefaultPageSize().getTop());
		assert (pageNum > 0 && pageNum <= myDocument.getNumberOfPages());

		System.out.println("LRTB: " + myDocument.getDefaultPageSize().getLeft() + " " + myDocument.getDefaultPageSize().getRight() + " " + myDocument.getDefaultPageSize().getTop() + " " + myDocument.getDefaultPageSize().getBottom());

        PdfAnnotation ann = PdfTextMarkupAnnotation.createHighLight(new Rectangle(x, y, width, height),
                new float[]{x, y+height, x, y, x+width, y+height, x + width, y})
                .setColor(Color.YELLOW)
                .setTitle(new PdfString("Hello!"))
                .setContents(new PdfString("I'm a popup."))
                .setTitle(new PdfString("iText"))
                .setOpen(true);
//                .setRectangle(new PdfArray(new float[]{100, 300, 200, 100}));
        myDocument.getPage(pageNum).addAnnotation(ann);

		return true;
	}

	/**
	 *	The four coordinates of a rectangle are upper left, lower left, upper right, lower right,
	 *  which correspond to coordinate pairs (x,y+height), (x,y), (x+width, y+height), (x+width, y)
	 *
	 * @param myDocument
	 * @param x lower left x coordinate of a rectangle
	 * @param y lower left y coordinate of a rectangle
	 * @param width of rectangle
	 * @param height of rectangle
	 * @return
	 */
	static boolean addUnderlineAnnotation(PdfDocument myDocument, float x, float y, float width, float height, int pageNum) {
		assert(myDocument != null);
		assert (x >= 0 && x <= myDocument.getDefaultPageSize().getRight());
		assert (y >= 0 && y <= myDocument.getDefaultPageSize().getTop());
		assert (pageNum > 0 && pageNum <= myDocument.getNumberOfPages());

        PdfAnnotation ann = PdfTextMarkupAnnotation.createUnderline(new Rectangle(x, y, width, height),
                new float[]{x, y+height, x, y, x+width, y+height, x + width, y})
                .setColor(Color.BLUE)
                .setTitle(new PdfString("Underline"))
                .setContents(new PdfString("I'm a popup."))
                .setTitle(new PdfString("iText"))
                .setOpen(true);
        myDocument.getPage(pageNum).addAnnotation(ann);

		return true;
	}

	/**
	 *	The four coordinates of a rectangle are upper left, lower left, upper right, lower right,
	 *  which correspond to coordinate pairs (x,y+height), (x,y), (x+width, y+height), (x+width, y)
	 *
	 * @param myDocument
	 * @param x lower left x coordinate of a rectangle
	 * @param y lower left y coordinate of a rectangle
	 * @param width of rectangle
	 * @param height of rectangle
	 * @return
	 */
	static boolean addStrikeThroughAnnotation(PdfDocument myDocument, float x, float y, float width, float height, int pageNum) {
		assert(myDocument != null);
		assert (x >= 0 && x <= myDocument.getDefaultPageSize().getRight());
		assert (y >= 0 && y <= myDocument.getDefaultPageSize().getTop());
		assert (pageNum > 0 && pageNum <= myDocument.getNumberOfPages());

        PdfAnnotation ann = PdfTextMarkupAnnotation.createStrikeout(new Rectangle(x, y, width, height),
                new float[]{x, y+height, x, y, x+width, y+height, x + width, y})
                .setColor(Color.RED)
                .setTitle(new PdfString("Strikeout"))
                .setContents(new PdfString("I'm a popup."))
                .setTitle(new PdfString("iText"))
                .setOpen(true);
//                .setRectangle(new PdfArray(new float[]{100, 600, 200, 100}));
        myDocument.getPage(pageNum).addAnnotation(ann);

		return true;
	}

	/**
	 *	The four coordinates of a rectangle are upper left, lower left, upper right, lower right,
	 *  which correspond to coordinate pairs (x,y+height), (x,y), (x+width, y+height), (x+width, y)
	 *
	 * @param myDocument
	 * @param x lower left x coordinate of a rectangle
	 * @param y lower left y coordinate of a rectangle
	 * @param width of rectangle
	 * @param height of rectangle
	 * @return
	 */
	static boolean addBoxAnnotation(PdfDocument myDocument, float x, float y, float width, float height, int pageNum) {
		assert(myDocument != null);
		assert (x >= 0 && x <= myDocument.getDefaultPageSize().getRight());
		assert (y >= 0 && y <= myDocument.getDefaultPageSize().getTop());
		assert (pageNum > 0 && pageNum <= myDocument.getNumberOfPages());

        PdfAnnotation ann = PdfPolyGeomAnnotation.createPolygon(new Rectangle(x, y, width, height),
                new float[]{x, y, x, y+height, x+width, y+height, x + width, y})
                .setColor(Color.RED)
                .setTitle(new PdfString("Box"))
                .setContents(new PdfString("I'm a popup."))
                .setTitle(new PdfString("iText"))
                .setOpen(true);
//                .setRectangle(new PdfArray(new float[]{100, 600, 200, 100}));
        myDocument.getPage(pageNum).addAnnotation(ann);

		return true;
	}

	static void printAnnots(PdfDocument myDocument, int pageNum) {
		List<PdfAnnotation> myList = myDocument.getPage(pageNum).getAnnotations();

		System.out.println("PageNum: " + pageNum);
		for (int i = 0; i < myList.size(); i++) {
			System.out.println("i: " + i + " annot: " + myList.get(i).getRectangle().get(0) + " " + myList.get(i).getRectangle().get(1)+ " " + myList.get(i).getRectangle().get(2)+ " " + myList.get(i).getRectangle().get(3));
		}
	}

	static void deleteAnnot(PdfDocument myDocument, int pageNum, float x, float y) {
		List<PdfAnnotation> myList = myDocument.getPage(pageNum).getAnnotations();

		System.out.println("PageNum: " + pageNum);
		for (int i = 0; i < myList.size(); i++) {
			if(x >= myList.get(i).getRectangle().getAsNumber(0).floatValue() &&
					x <= myList.get(i).getRectangle().getAsNumber(2).floatValue() &&
					y >= myList.get(i).getRectangle().getAsNumber(1).floatValue() &&
					y <= myList.get(i).getRectangle().getAsNumber(3).floatValue()) {
					myDocument.getPage(pageNum).removeAnnotation(myList.get(i));
					System.out.println("Removing: " + i);
			}
		}
	}
}
