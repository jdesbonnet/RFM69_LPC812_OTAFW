import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.zip.CRC32;

public class BinToPage64Hex {

	public static void main (String[] arg) throws Exception {

		RandomAccessFile binFile = new RandomAccessFile(new File(arg[0]),"r");
		
		// CRC32 of each page
		int endPage = (int)(binFile.length()/64);
		if (binFile.length()%64 != 0) {
			//endPage++;
		}
		
		byte[] buf64 = new byte[64];
		
		int i,j;
		CRC32 crc32 = new CRC32();
		for (i = 0; i < endPage; i++) {
			for (j = 0; j < 64; j++) {
				buf64[j] = 0;
			}
			crc32.reset();
			binFile.seek(i*64);
			binFile.readFully(buf64);
			crc32.update(buf64);
			
			String addr = String.format("%04x ", i*64);
			System.out.print(addr);
			for (j = 0; j < 64; j++) {
				System.out.print(String.format("%02x", buf64[j]));
			}
			
			System.out.println(String.format(" %08x", crc32.getValue()));
		}
		
		
		binFile.close();
		
	}
}
