package application;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.annotation.Annotation;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import org.apache.commons.io.FileUtils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;
import com.sun.pdfview.PDFRenderer;

public class swingclass extends JFrame implements ActionListener {


	float x1 = 0;
	float y1 = 0;
	float x2 = 0;
	float y2 = 0;

	int clickCount = 0;

	int mode = 0;

	JButton next, previous, normal, highlight, underline, strikethrough, box, remove,save;

	PDFFile pdffile;

	PagePanel pagepanel;

	int i = 1, n;

	public swingclass(String filename) throws IOException {
		// TODO Auto-generated constructor stub

		setTitle("PDF Display");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 900);
		setResizable(true);
		setDefaultLookAndFeelDecorated(true);

		setup(filename);
		setVisible(true);

		// Idea to try later
		// this.add(new PDFDisplay(bytes));
	}

	public void setup(String filename) throws IOException {
		Container container = getContentPane();

		JPanel panel = new JPanel();
		container.add(panel);
		panel.setLayout(null);

		next = new JButton("Next");
		next.setBounds(0, 10, 100, 30);
		panel.add(next);
		next.addActionListener(this);

		previous = new JButton("Previous");
		previous.setBounds(100, 10, 100, 30);
		panel.add(previous);
		previous.addActionListener(this);

		normal = new JButton("Normal");
		normal.setBounds(200, 10, 100, 30);
		panel.add(normal);
		normal.addActionListener(this);

		highlight = new JButton("Highlight");
		highlight.setBounds(300, 10, 100, 30);
		panel.add(highlight);
		highlight.addActionListener(this);

		underline = new JButton("Underline");
		underline.setBounds(400, 10, 100, 30);
		panel.add(underline);
		underline.addActionListener(this);

		strikethrough = new JButton("Strikethrough");
		strikethrough.setBounds(500, 10, 100, 30);
		panel.add(strikethrough);
		strikethrough.addActionListener(this);

		box = new JButton("Box");
		box.setBounds(600, 10, 100, 30);
		panel.add(box);
		box.addActionListener(this);

		remove = new JButton("Remove");
		remove.setBounds(700, 10, 100, 30);
		panel.add(remove);
		remove.addActionListener(this);
		
		save = new JButton("SAVE");
		save.setBounds(800,10,100,30);
		panel.add(save);
		save.addActionListener(this);

		// load a pdf from a byte buffer
		File file = new File(filename);
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileChannel channel = raf.getChannel();
		ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

		pdffile = new PDFFile(buf);

		n = pdffile.getNumPages();

		System.out.println("no of pages from swing clas:" + pdffile.getNumPages());

		PDFPage page = pdffile.getPage(i);


		pagepanel = new PagePanel();
		pagepanel.setBounds(0, 50, 595, 842 + 50);
		panel.add(pagepanel);

		pagepanel.showPage(page);

		pagepanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
            	System.out.println("Location: " + e.getX() + " " + e.getY());

            	if(clickCount == 0) {
            		x1 = e.getX();
            		y1 = e.getY();
            		clickCount++;
            	}
            	else {
            		clickCount = 0;
            		x2 = e.getX();
            		y2 = e.getY();

//            		System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);

            		System.out.println("Before: ");
            		Annotations.printAnnots(PdfDocumentcreation.getDocument(), i);

            		switch(mode) {
            		case 0:
            			break;
            		case 1:
            			Annotations.addHighlightAnnotation(PdfDocumentcreation.getDocument(), x1,(845-y1),x2-x1, (845-y2)-(845-y1), i);
            			break;
            		case 2:
            			Annotations.addUnderlineAnnotation(PdfDocumentcreation.getDocument(), x1,(845-y1),x2-x1, (845-y2)-(845-y1), i);
            			break;
            		case 3:
            			Annotations.addStrikeThroughAnnotation(PdfDocumentcreation.getDocument(), x1,(845-y1),x2-x1, (845-y2)-(845-y1), i);
            			break;
            		case 4:
            			Annotations.addBoxAnnotation(PdfDocumentcreation.getDocument(), x1,(845-y1),x2-x1, (845-y2)-(845-y1), i);
            			break;
            		case 5:
            			Annotations.deleteAnnot(PdfDocumentcreation.getDocument(), i, x1,(845-y1));
            			break;
            		}
            		System.out.println("After: ");
            		Annotations.printAnnots(PdfDocumentcreation.getDocument(), i);
            	}
            }
        });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();

		if (src == next) {

			n = pdffile.getNumPages();

			System.out.println("no of pages inside next:" + pdffile.getNumPages());

			if (i < n) {

				PDFPage page = pdffile.getPage(++i);

				pagepanel.showPage(page);

			} else {

				PDFPage page = pdffile.getPage(i);

				pagepanel.showPage(page);

			}

		} else if (src == previous) {

			//NEED THIS IN SAVE OR SOMETHING LIKE IT
//			PdfDocumentcreation.savePDF();

			n = pdffile.getNumPages();

			System.out.println("no of pages inside previous:" + pdffile.getNumPages());

			if (i > 0) {

				PDFPage page = pdffile.getPage(--i);

				pagepanel.showPage(page);

			} else {
				PDFPage page = pdffile.getPage(i);

				pagepanel.showPage(page);

			}

		} else if (src == normal) {
			mode = 0;
		} else if (src == highlight) {
			mode = 1;
		} else if (src == underline) {
			mode = 2;
		} else if (src == strikethrough) {
			mode = 3;
		} else if (src == box) {
			mode = 4;
		} else if (src == remove) {
			mode = 5;
		}
		else if (src == save)
		{

			JFileChooser openFile = new JFileChooser();
			openFile.setDialogTitle("select file for save");
        //    openFile.showSaveDialog(swingclass.this);
			
			PdfDocumentcreation.savePDF();
            
            int userselection = openFile.showSaveDialog(this);
          
            if (userselection == JFileChooser.APPROVE_OPTION) {
    			File fileToSave = openFile.getSelectedFile();
    			System.out.println("Save as file: " + fileToSave.getAbsolutePath());
    			
    			File srcDir = new File("src/application/temp2.pdf");
    			File destDir = new File(fileToSave.getAbsolutePath());
    			try {
					FileUtils.copyFile(srcDir, destDir);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			
    			
    		}
		}

	}

}