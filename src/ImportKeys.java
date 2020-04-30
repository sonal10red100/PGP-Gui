import java.io.File;

import javax.swing.JFileChooser;

import com.didisoft.pgp.KeyStore;

public class ImportKeys {
	LocalPath l;
		public void importKeys() throws Exception {	
		KeyStore ks = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", l.keyStore_Password);
		 JFileChooser j = new JFileChooser(new File("C:/Users/saura/eclipse-workspace/Gui-PGP-Gui/src/KeyFiles/")); 
		   j.showOpenDialog(null); 
		   File fileToSave = j.getSelectedFile();
		   String path=fileToSave.getAbsolutePath();
		   System.out.println(path);
		  
		ks.importPublicKey(path);
		}
}
