import java.io.IOException;

import com.didisoft.pgp.*;
public class CheckRevokedKey {
	LocalPath l;
	public boolean checkIfRevoked(String uid) {
		
		try {
			KeyStore ks = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", l.keyStore_Password);
			KeyPairInformation[] keys = ks.getKeys();
			for(KeyPairInformation key: keys){
				if(key.isRevoked() && uid.equals(key.getUserID())) {
					return true;				
				}
			}
			return false;
			
			
			
		} catch (PGPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}