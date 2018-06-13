package eu.michalbuda.pdfSecure.pdf_secure;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class App 
{	
	private static String file = "pdf.pdf";
	private static String ownerPass = "owner";
	private static String userPass = "userPass";
	
    public static void main( String[] args ) throws DocumentException
    {				
    			SecureFile secure = new SecureFile(ownerPass, userPass, file);
    
    			try {
					secure.secureFile();
				} catch (IOException e) {
					System.out.println("file not found");
					e.printStackTrace();
				}
    			
    }
    	 
}
