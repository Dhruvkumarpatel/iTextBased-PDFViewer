package application;

import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;


public class swingclass  extends JFrame implements ActionListener{
	
	
		JButton next,previous;
		
		PDFFile pdffile;
		
		PagePanel pagepanel;
	
		int i=0,n;
	
		public swingclass(String filename) throws IOException {
			// TODO Auto-generated constructor stub
			
			setTitle("PDF Display");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(900,900);
			setResizable(true);
			setDefaultLookAndFeelDecorated(true);
			setup(filename);
			setVisible(true);
			
			
		}

	  public void setup(String filename) throws IOException {
		  
		  
		    Container container = getContentPane();
		    
		    JPanel panel = new JPanel();
		    container.add(panel);
	        panel.setLayout(null);
		    
		    
	        pagepanel = new PagePanel();
	        pagepanel.setBounds(0,100,800,600);
	        panel.add(pagepanel);
	        
	        
	        next = new JButton("Next");
	        next.setBounds(0,10,100,30);
	        panel.add(next);
	        next.addActionListener(this);
	        
	        previous = new JButton("Previous");
	        previous.setBounds(120,10,100,30);
	        panel.add(previous);
	        previous.addActionListener(this);

	        
	        
	        //load a pdf from a byte buffer
	        File file = new File(filename);
	        RandomAccessFile raf = new RandomAccessFile(file, "r");
	        FileChannel channel = raf.getChannel();
	        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,
	            0, channel.size());
	        
	        pdffile = new PDFFile(buf);
	        
	        
	        n = pdffile.getNumPages();
	        
	        System.out.println("no of pages from swing clas:"+pdffile.getNumPages());
	        
	        PDFPage page = pdffile.getPage(i);
	        
	        pagepanel.showPage(page);
	        
	      
	        
	        
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		Object src = e.getSource();
		
		if(src == next)
		{
			
			n = pdffile.getNumPages();
	        
	        System.out.println("no of pages inside next:"+pdffile.getNumPages());
			
			if(i<n)
			{

				 PDFPage page = pdffile.getPage(i++);
			        
			     pagepanel.showPage(page);
				
			}
			else
			{
				
				 PDFPage page = pdffile.getPage(i);
			        
			     pagepanel.showPage(page);
				
				
			}
		
			
			
		}
		else
		{
			
			n = pdffile.getNumPages();
	        
	        System.out.println("no of pages inside previous:"+pdffile.getNumPages());	
			
			if(i>0)
			{
				
				 PDFPage page = pdffile.getPage(i--);
			        
			     pagepanel.showPage(page);
				
			}
			else
			{
				 PDFPage page = pdffile.getPage(i);
			        
			     pagepanel.showPage(page);
				
			}
			
			
			
		}
		
		
	}
	  
	  
	 
	  
	  
	  

}