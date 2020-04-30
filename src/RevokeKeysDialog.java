import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.didisoft.pgp.*;


public class RevokeKeysDialog {
	
	 LocalPath l;
	 private JLabel pswd;
	  private JPasswordField passText;
	  private JLabel uId;
	  private JTextField uIdText;
	  private JButton submit;
	  private JDialog mydialog;
	  private JLabel forgot1;
	  private JLabel forgot2;
	  private JButton importCerti;
	  Color color = new Color(63,96,124);
	  Font  f2  = new Font(Font.SERIF,  Font.BOLD, 16);
		 
	  
	public void revokeKeysDialog() throws Exception {
		KeyStore ks = new KeyStore("src/KeyFiles/pgp_KeyStore.keystore", l.keyStore_Password);
		
		JPanel panel=new JPanel();
		String title = "Security check";
		TitledBorder b = BorderFactory.createTitledBorder(title);
		panel.setBorder(b);
		panel.setLayout(new GridBagLayout());
		 GridBagConstraints constraints = new GridBagConstraints();
	        constraints.anchor = GridBagConstraints.CENTER;
	        constraints.insets = new Insets(10, 10, 10, 10);
	           
	        panel.setSize(new Dimension(500,300));
	     
        uId=new JLabel("User Id :");
		uIdText=new JTextField(40);
		uIdText.setMinimumSize(new Dimension(200,20));
      
		pswd=new JLabel("Password :");
		passText=new JPasswordField(40);
		passText.setMinimumSize(new Dimension(200,20));
		
		forgot1=new JLabel("Forgot Password?");
		forgot2=new JLabel("Use Revocation Certificate to revoke keys");
		
		importCerti=new JButton("Import Revocation Certificate and Revoke");
		submit=new JButton("Submit");
		importCerti.setBackground(color);
		importCerti.setForeground(Color.WHITE);
		submit.setBackground(color);
		submit.setForeground(Color.WHITE);
		
		
		 
		 constraints.gridx = 0;
	     constraints.gridy = 0;
	     panel.add(uId,constraints);
	     constraints.gridx = 1;
	     panel.add(uIdText,constraints);
		 constraints.gridx = 0;
	     constraints.gridy = 1;
	     panel.add(pswd,constraints);
	     constraints.gridx = 1;
	     panel.add(passText,constraints);
	     constraints.gridx = 0;
	     constraints.gridy = 2;
	     constraints.gridwidth=2;
	     panel.add(submit,constraints);
	     
	     
	     constraints.gridx = 1;
	     constraints.gridy = 4;
	     constraints.gridwidth=1;
	     constraints.anchor=GridBagConstraints.CENTER;
	     panel.add(importCerti,constraints); 
	     
	     
	     constraints.fill=GridBagConstraints.BOTH;
		 constraints.anchor=GridBagConstraints.CENTER;
		 
	     constraints.gridx = 0;
	     constraints.gridy = 3;
	     constraints.gridwidth=1;
	     panel.add(forgot1,constraints); 
	     constraints.gridx = 1;
	     panel.add(forgot2,constraints); 
	     
	     
	     

	     panel.setVisible(true);
	     panel.setBackground(Color.WHITE);
			
		mydialog=new JDialog();
	    mydialog.add(panel);
	    mydialog.setSize(500,300);
	    mydialog.setLocationRelativeTo(null);
	    mydialog.setVisible(true);
	    
	    
    	submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String u=uIdText.getText();
            	String p=passText.getText();
            	
            	
            	
            	System.out.println(p);
            	int n = JOptionPane.showConfirmDialog(null, "<html>Are you sure you want to revoke your PGP keys?<br/>Once distributed, others will be unable to encrypt data to this key.</html>", "Select an Option...",
        				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            	if(n==0) {
                RevokeKey rk=new RevokeKey();
                try {
					rk.revokeKey(u,p);
					
					mydialog.dispose();
					
				} catch (Exception e1) {
					System.out.println("userid and password didnt match");
					 int input = JOptionPane.showConfirmDialog(null,
				                "<html>User Id and Password did not match<br/>Please try again</html>","Message", JOptionPane.DEFAULT_OPTION);
				    
					e1.printStackTrace();
				}
            	}
           }
         });
    	importCerti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser j = new JFileChooser(new File(l.localPath+"src/revocationCerti/")); 
     		   j.showOpenDialog(null); 
     		   File fileToSave = j.getSelectedFile();
     		   String path=fileToSave.getAbsolutePath();
     		   System.out.println(path);
     		   
     		  RevocationLib lib = new RevocationLib();
     		 try {
				lib.revokeKeyWithRevocationCertificateFile(ks, path);
				  int input = JOptionPane.showConfirmDialog(null,
			                "Your key has been successfully revoked","Message", JOptionPane.DEFAULT_OPTION);
				mydialog.dispose();
			} catch (PGPException e1) {
				  int input = JOptionPane.showConfirmDialog(null,"<html>No keys are found matching the selected revocation certificate.<br/>Please select the correct certificate file.</html>","Message", JOptionPane.DEFAULT_OPTION);
				mydialog.dispose();
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				int input = JOptionPane.showConfirmDialog(null,"<html>No keys are found matching the selected revocation certificate.<br/>Please select the correct certificate file.</html>","Message", JOptionPane.DEFAULT_OPTION);
				mydialog.dispose();
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
             
            		
            }});

	}
}
