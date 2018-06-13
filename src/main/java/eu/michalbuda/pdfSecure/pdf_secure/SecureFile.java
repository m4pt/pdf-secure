package eu.michalbuda.pdfSecure.pdf_secure;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.exceptions.BadPasswordException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class SecureFile {
	private byte[] ownerPassword;
	private byte[] userPassword;
	private String file;
	
	public SecureFile(String ownerPass, String readerPass, String file, boolean duplicateFile, boolean changeFileName) {
		super();
		this.ownerPassword = ownerPass.getBytes();
		this.userPassword = readerPass.getBytes();
		this.file = file;
	}

	public SecureFile(String ownerPass, String readerPass, String file) {
		super();
		this.ownerPassword = ownerPass.getBytes();
		this.userPassword = readerPass.getBytes();
		this.file = file;
	}


	public void secureFile() throws IOException, DocumentException {
				
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		FileOutputStream fos;	
		PdfReader reader = null;
		PdfStamper stamper = null;
		
		
		try {
			reader = new PdfReader(file);
			stamper = new PdfStamper(reader, baos);
			
			stamper.setEncryption(userPassword, ownerPassword,  PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_256);
			
			stamper.close();
			reader.close();
			
			fos = new FileOutputStream(file);
			
			baos.writeTo(fos);
			fos.close();
			
		} catch (BadPasswordException e) {
			System.out.println("File already secured.");
			//e.printStackTrace();
		}
		

	}
}
