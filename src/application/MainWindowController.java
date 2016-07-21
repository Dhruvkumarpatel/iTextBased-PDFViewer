package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;

import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//import org.jpedal.examples.viewer.ViewerFX;
//import com.qoppa.pdf.PDFException;
//import com.qoppa.pdfNotes.PDFNotesBean;
//import com.qoppa.pdfViewerFX.PDFViewer;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;


public class MainWindowController {

	@FXML
	Button browse;

	@FXML
	Button upload;

	@FXML
	Label filenamedisplay;

	File pdffile;

	SwingNode swingnode = new SwingNode();


	public void browseButtonClick(ActionEvent e)
	{

		FileChooser chooser = new FileChooser();
		chooser.setTitle("choose only pdf files");

		pdffile = chooser.showOpenDialog(new Stage());

		//Define specific file for testing
	//	pdffile = new File("C:\\Users\\robin_000\\Documents\\ASE_Requirements.pdf");


		if(pdffile != null)
		{
			filenamedisplay.setText(pdffile.getName());
		}



		System.out.println("pdf file name :"+pdffile.getAbsolutePath());

	}


//, PDFException
	public void uploadButtonClick(ActionEvent e) throws FileNotFoundException, IOException
	{
		String filename = pdffile.getName();

		// check validation file must be necessary to extension with .pdf

		if(filename.endsWith(".pdf") && filename != null )
		{
			// call pdfDocumentcreation class to create a copy of that document
			String newgeneratedfile = PdfDocumentcreation.createFromOld(pdffile.getAbsolutePath());
			PdfDocumentcreation.savePDF();


			String newgeneratedfile2 = PdfDocumentcreation.openPDF(pdffile.getAbsolutePath());

			System.out.println("file path: " + newgeneratedfile);

//			PdfDocumentcreation.addAnnotation();

			 SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {

		            	try {
							new swingclass(newgeneratedfile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        });



		}
		else
		{
			Stage dialog = new Stage();
			dialog.initStyle(StageStyle.UTILITY);

			Scene scene = new Scene(new Group(new Text(25,25,"please upload valid file with .pdf extension")),300,100);

			dialog.setScene(scene);
			dialog.show();
		}




	}


}
