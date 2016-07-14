package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class MainWindowController {
	
	@FXML
	Button browse;
	
	@FXML
	Button upload;
	
	@FXML
	Label filenamedisplay;
	
	File pdffile;
	
	
	public void browseButtonClick(ActionEvent e)
	{
		FileChooser chooser = new FileChooser();
		chooser.setTitle("choose only pdf files");
		
		pdffile = chooser.showOpenDialog(new Stage());
		
		
		if(pdffile != null)
		{
			filenamedisplay.setText(pdffile.getName());
		}
		
		
		
		System.out.println("pdf ffile name :"+pdffile.getAbsolutePath());
			
	}

	public void uploadButtonClick(ActionEvent e) throws FileNotFoundException, IOException
	{
	
		String filename = pdffile.getName();
		
		// check validation file must be necessary to extension with .pdf
		
		if(filename.endsWith(".pdf") && filename != null )
		{
			// call pdfDocumentcreation class to create a copy of that document
			PdfDocument document = PdfDocumentcreation.createFromOld(pdffile.getAbsolutePath());
			
			System.out.println("no of pages return in new file :"+document.getNumberOfPages());
			
			document.close();
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
