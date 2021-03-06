import javax.imageio.ImageIO;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;


import com.didisoft.pgp.*;
import javax.swing.border.TitledBorder;


import java.awt.Graphics;
import java.awt.Image;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class MyFrame extends JFrame {
  int width=850;
  int height=700;
  int flag;
  Icon iconEn = new ImageIcon("src/icons/encrypt.png");
  private JButton btnEncryptFile = new JButton("<html>Encrypt<br/> File<html>",iconEn);
  Icon iconDe = new ImageIcon("src/icons/decrypt.png");
  private JButton btnDecryptFile = new JButton("<html>Decrypt<br/> File<html>",iconDe);
  private JButton btnEncryptText = new JButton("<html>Encrypt<br/> Text<html>",iconEn);
  private JButton btnDecryptText = new JButton("<html>Decrypt<br/> Text<html>",iconDe);
  //Icon iconKe = new ImageIcon("src/icons/keyring.png");
  //private JButton btnKeyRings = new JButton("<html>Key <br/>Rings</html>",iconKe);
  Icon iconDigsign = new ImageIcon("src/icons/digsign.png");
  private JButton btnDigSign = new JButton("<html>Digital<br/> Signature",iconDigsign);
  Icon iconPGPKey = new ImageIcon("src/icons/genKey.png");
  private JButton btnCreateKey = new JButton("<html>Generate <br/> Key",iconPGPKey);
 // Icon iconVerify = new ImageIcon("src/icons/verify.png");
  //private JButton btnVerify = new JButton("Verify",iconVerify);
  
  Icon iconKeysList = new ImageIcon("src/icons/KeysList.png");
  private JButton btnKeysList = new JButton("<html>View Keys <br/> List",iconKeysList);
  Icon revokedKeysList = new ImageIcon("src/icons/revoke.png");
  private JButton btnRevokedKeysList = new JButton("<html>Revoked<br/>Keys List",revokedKeysList);
  
  private JMenuItem revocationCertiGen;
  private JMenuItem exitf;
  private JMenuItem viewKeys;
  private JMenuItem about;
  private JMenuItem revokeKey;
  private JLabel pswd;
  private JPasswordField passText;
  private JLabel uId;
  private JTextField uIdText;
  private JButton submit;
  private JDialog mydialog;
  JButton certiGen=new JButton("Generate Revocation Certificate");
  Color color = new Color(63,96,124);
	
  
  private JLabel picLabel = new JLabel(new ImageIcon("src/icons/workflow.png"));
  //picLabel.setBounds(80, 180, 450, 380);
  ///constraints.gridx = 0;
  //constraints.gridy = 3; 
     //getContentPane().add(picLabel);
  
  private JLabel lblA = new JLabel("KEY MANAGEMENT");
  private JLabel lblB = new JLabel("<html>ENCRYPTION/DECRYPTION & <br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp DIGITAL SIGNATURE</html>");
  
 // private JLabel lblC = new JLabel("<html>NOTE: Please check if the user has revoked keys or not before encryption.<br/>Generate Revocation Certificate and store it for future</html>");
 
  
  public void enterUnamePassDialog() {
  	JPanel panel=new JPanel();
		String title = "Security check";
		TitledBorder b = BorderFactory.createTitledBorder(title);
		panel.setBorder(b);
		panel.setLayout(new GridBagLayout());
		 GridBagConstraints constraints = new GridBagConstraints();
	        constraints.anchor = GridBagConstraints.CENTER;
	        constraints.insets = new Insets(10, 10, 10, 10);
	           
	        panel.setSize(new Dimension(400,200));
	     
        uId=new JLabel("User Id :");
		uIdText=new JTextField(40);
		uIdText.setMinimumSize(new Dimension(200,20));
      
		pswd=new JLabel("Password :");
		passText=new JPasswordField(40);
		passText.setMinimumSize(new Dimension(200,20));
      
		submit=new JButton("Submit");
		 
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

	     panel.setVisible(true);
			
		mydialog=new JDialog();
	    mydialog.add(panel);
	    mydialog.setSize(400,200);
	    mydialog.setLocationRelativeTo(null);
	    mydialog.setVisible(true);
	    JLabel picLabel = new JLabel(new ImageIcon("src/icons/flowchart.png"));
        picLabel.setBounds(80, 180, 400, 400);
        //constraints.gridx = 0;
	     //constraints.gridy = 3; 
	        panel.add(picLabel);
      
      
  }
  
  public MyFrame() {
	 

	setTitle("PGP Tool");
    ImageIcon logo = new ImageIcon("src/icons/logo.png");
    setIconImage(logo.getImage());
    
    getContentPane().setBackground(Color.WHITE);
    getRootPane().setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.darkGray));
    setSize(width,height);
    setLocation(new Point(300,200));
    setLayout(null);    
    setResizable(false);
   
    JMenuBar mb = new JMenuBar();
    mb.setBackground(color);
   // mb.setForeground(Color.WHITE);
    JMenu menu1 = new JMenu("PGP Tool");
    menu1.setForeground(Color.WHITE);
    JMenu menu2 = new JMenu("Keys");
    menu2.setForeground(Color.WHITE);
    
    revocationCertiGen =  new JMenuItem(new AbstractAction("Generate Revocation Certificate") {
        public void actionPerformed(ActionEvent e) {
        	JPanel panel=new JPanel();
        	panel.setSize(800, 500);
    		String title = "GENERATE REVOCATION CERTIFICATE";
    		TitledBorder b = BorderFactory.createTitledBorder(title);
    		panel.setBorder(b);
    	    Font  f2  = new Font(Font.SERIF,  Font.BOLD, 15);
    	    
    		JLabel desc1=new JLabel("A lost passphrase or private key could cause you a problem. Generate a key revocation certificate now to avoid this.");  
    		JLabel desc2=new JLabel("You might forget the passphrase to your private key. Without the passphrase, you can't access your private key and decrypt");  
    		desc1.setFont(f2);
    		desc2.setFont(f2);
    		JLabel desc3=new JLabel("email and files sent to you, thus making your key worthless. Moreover, without the passphrase, you can't even revoke the");
    		desc3.setFont(f2);
    		JLabel desc4=new JLabel("key to let people know that they shouldn't use that public key when encrypting messages and files to you. A  key ");
    		desc4.setFont(f2);
    		JLabel desc5=new JLabel("revocation certificate is a special, revoked copy of your public key. You can generate a key revocation certificate and");
    		desc5.setFont(f2);
    		JLabel desc6=new JLabel("store it for future use. Key revocation certificates are especially useful if you've forgotten the passphrase to your");
    		desc6.setFont(f2);
    		JLabel desc7=new JLabel("private key and you need some way to disable or revoke that key. Since you've forgotten the passphrase or lost the");
    		desc7.setFont(f2);
    		JLabel desc8=new JLabel("private key, the only way to revoke the key will be with a revocation certificate that you generated earlier.");
    		desc8.setFont(f2);
    		panel.setVisible(true);
           BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("src/icons/Certi.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
           JLabel picLabel = new JLabel(new ImageIcon(myPicture));
           picLabel.setBounds(20, 20, 700, 350);
           add(picLabel);
           
           panel.add(picLabel);
           panel.add(desc1);
           panel.add(desc2);
           panel.add(desc3);
           panel.add(desc4);
           panel.add(desc5);
           panel.add(desc6);
           panel.add(desc7);
           panel.add(desc8);
           certiGen.setBackground(color);
           certiGen.setForeground(Color.WHITE);
           
           panel.add(certiGen);
           panel.setBackground(Color.WHITE);
           
           
           JDialog myd=new JDialog();
           myd.add(panel);
           myd.setSize(840,480);
           myd.setLocationRelativeTo(null);
           myd.setVisible(true);
           
         
    		
        }
    });
    
    

    
    exitf =  new JMenuItem(new AbstractAction("Exit") {
        public void actionPerformed(ActionEvent e) {
        	System.exit(1);
        }
    });
    viewKeys =  new JMenuItem(new AbstractAction("View Keys Info") {
        public void actionPerformed(ActionEvent e) {
        	 DisplayKeysList kl=new DisplayKeysList();
       	  try {
       		kl.showKeyslist();
       	} catch (Exception e1) {
       		// TODO Auto-generated catch block
       		e1.printStackTrace();
       	}
        }
    });
    about =  new JMenuItem(new AbstractAction("Help") {
        public void actionPerformed(ActionEvent e) {
       	 	File f= new File("src/help.pdf");
       	 	try {
				Desktop.getDesktop().open(f);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
       }
   });
//    importKey =  new JMenuItem(new AbstractAction("Import keys") {
//        public void actionPerformed(ActionEvent e) {
//        	ImportKeys ik=new ImportKeys();
//        	try {
//				ik.importKeys();
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//        }
//    });
   revokeKey =  new JMenuItem(new AbstractAction("Revoke Keys") {
	   
        public void actionPerformed(ActionEvent e) {
        	RevokeKeysDialog rd= new RevokeKeysDialog();
        	try {
				rd.revokeKeysDialog();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }});
        	
        
    
    
    mb.add(menu1);
    mb.add(menu2);
    about.setBackground(Color.WHITE);
    menu1.add(about);
    menu1.addSeparator();
    revocationCertiGen.setBackground(Color.WHITE);
    menu1.add(revocationCertiGen);
    menu1.addSeparator();
    exitf.setBackground(Color.WHITE);
    menu1.add(exitf);
    viewKeys.setBackground(Color.WHITE);
    menu2.add(viewKeys);
    menu2.addSeparator();
   // menu2.add(importKey);
    revokeKey.setBackground(Color.WHITE);
    menu2.add(revokeKey);
    setJMenuBar(mb);
    
    initComponent();    
    initEvent();    
  }
  public void paint(Graphics gr)
  {
             super.paint(gr);
             gr.setColor(Color.GRAY);
             Graphics2D g2d=(Graphics2D) gr;
             g2d.drawRoundRect(10, 55, 410, 250,30,30);
             g2d.drawRoundRect(430, 55, 407, 250,30,30);
             g2d.drawRoundRect(10, 310, 827, 375,30,30);
            
  }

  private void initComponent(){
	  
	  btnEncryptFile.setBounds(470,70,130,60);
	  btnEncryptFile.setBackground(color);
	 // btnEncryptFile.setBorder(new RoundedBorder(20));
	  btnEncryptFile.setForeground(Color.white);
	    btnEncryptFile.setToolTipText("<html>Allows to make the file<br>content illegible to <br>third party viewers. Use to <br>keep confidential file safe</html>");
	    
	    btnDecryptFile.setBounds(655,70,130,60);
	    btnDecryptFile.setToolTipText("<html>Allows to make the file<br>content legible. Use to keep <br>confidential file safe</html>");
	    btnDecryptFile.setBackground(color);
	 //   btnDecryptFile.setBorder(new RoundedBorder(20));
	    btnDecryptFile.setForeground(Color.white);
		  
	    btnKeysList.setBounds(235, 70, 130, 60);
	    btnKeysList.setToolTipText("<html>View possible users<br>and check for revoked keys</html>");
	    btnKeysList.setBackground(color);
	 //   btnKeysList.setBorder(new RoundedBorder(20));
	    btnKeysList.setForeground(Color.white);
		
	    btnDigSign.setBounds(565,160,130,60);
	    btnDigSign.setToolTipText("<html>Digital Signature is <br> used to prove source <br>authenticity</html>");
	    btnDigSign.setBackground(color);
	  //  btnDigSign.setBorder(new RoundedBorder(20));
	    btnDigSign.setForeground(Color.white);
		
	    btnCreateKey.setBounds(50, 70, 130, 60);
	    btnCreateKey.setToolTipText("<html>Generate keys and use<br> for different functionalities</html>");
	    btnCreateKey.setBackground(color);
	  //  btnCreateKey.setBorder(new RoundedBorder(20));
	    btnCreateKey.setForeground(Color.white);
		
	    btnRevokedKeysList.setBounds(130, 160, 130, 60);
	    btnRevokedKeysList.setBackground(Color.BLUE);
	    btnRevokedKeysList.setToolTipText("<html>View all users<br>whoose keys are revoked</html>");
	    btnRevokedKeysList.setBackground(color);
	 //   btnRevokedKeysList.setBorder(new RoundedBorder(20));
	    btnRevokedKeysList.setForeground(Color.white);
		
	    
	    Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 15);
    lblA.setBounds(150,10,200,20);
    lblA.setFont(f2);
    lblA.setForeground(color);
    lblB.setBounds(520,10,450,40);
    lblB.setFont(f2);
    lblB.setForeground(color);
//    lblC.setBounds(170,390,450,40);
//    lblC.setBackground(Color.white);
//    lblC.setOpaque(true);
//    
    JLabel j=new JLabel("TOOL ROADMAP");
    j.setBounds(360, 260, 200, 20);
    j.setFont(f2);
    picLabel.setBounds(50, 230, 750, 450);
    add(btnEncryptFile);
    add(btnDecryptFile);
    add(btnEncryptText);
    add(btnDecryptText);
    //add(btnKeyRings);
    add(btnDigSign);
    add(btnCreateKey);
    //add(btnVerify);
    add(btnKeysList);
    add(btnRevokedKeysList);
     
    
    add(lblA);
    add(lblB);
    add(picLabel);
    add(j);
   // add(lblC);
   
  }

  private void initEvent(){

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
       System.exit(1);
      }
    });

    certiGen.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	enterUnamePassDialog();
        	submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	String u=uIdText.getText();
                	String p=passText.getText();
                	GenerateRevocationCertificate gc=new GenerateRevocationCertificate();
                	try {
						gc.revocationCertiGen(u,p);
						mydialog.dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("userid and password didnt match");
						 int input = JOptionPane.showConfirmDialog(null,
					                "<html>User Id and Password did not match<br/>Please try again</html>","Message", JOptionPane.DEFAULT_OPTION);
					   
						e1.printStackTrace();
					}
                
                }});
       }
     });
    
    btnEncryptFile.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            btnEncryptFile(e);
       }
     });
    
    btnDecryptFile.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            btnDecryptFile(e);
       }
     });
    
    btnDecryptText.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            btnEncryptText(e);
       }
     });
    
    btnDecryptFile.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            btnDecryptText(e);
       }
     });
    
//    btnKeyRings.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            btnKeyRings(e);
//       }
//     });
//    
    btnDigSign.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            btnDigSign(e);
       }
     });
    
    btnCreateKey.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	btnCreateKey(e);
       }
     });
    
    btnKeysList.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	btnKeysList(e);
       }
     });
    
    btnRevokedKeysList.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	btnRevokedKeysList(e);
       }
     });
  }
  

  private void btnEncryptFile(ActionEvent evt){
	    //Create separate class for implementing this function
	  EncryptDialog en = new EncryptDialog();
	  	en.setVisible(true);
  }
  
  private void btnDecryptFile(ActionEvent evt){
	    //Create separate class for implementing this function
	  DecryptDialog dn = new DecryptDialog();
	  	dn.setVisible(true);
  }
  private void btnEncryptText(ActionEvent evt){
	    //Create separate class for implementing this function
	  	
  }
  private void btnDecryptText(ActionEvent evt){
	    //Create separate class for implementing this function
	  	
  }
//  private void btnKeyRings(ActionEvent evt){
//	  DisplayKeysList kl=new DisplayKeysList();
//	  try {
//		kl.showKeyslist();
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	    //Create separate class for implementing this function
//  }
  private void btnDigSign(ActionEvent evt){
	    //Create separate class for implementing this function
	  try {
          for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
              if ("Nimbus".equals(info.getName())) {
                  javax.swing.UIManager.setLookAndFeel(info.getClassName());
                  break;
              }
          }
      } catch (ClassNotFoundException ex) {
          java.util.logging.Logger.getLogger(DigitalSignatureDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
          java.util.logging.Logger.getLogger(DigitalSignatureDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
          java.util.logging.Logger.getLogger(DigitalSignatureDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
          java.util.logging.Logger.getLogger(DigitalSignatureDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the dialog */
      java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
              try {
                  DigitalSignatureDialog dialog;
                  dialog = new DigitalSignatureDialog(new javax.swing.JFrame(), true);
                  dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                      @Override
                      public void windowClosing(java.awt.event.WindowEvent e) {
                          //System.exit(0);
                          dialog.setVisible(false);
                          dialog.dispose();
                      }
                  });
                  dialog.setVisible(true);
              } catch (IOException ex) {
                  Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
              } catch (PGPException ex) {
                  Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
      });

  }
  private void btnCreateKey(ActionEvent evt){
	    //Create separate class for implementing this function
	 
	  CreateKeyDialog cl=new CreateKeyDialog();
	  cl.createPanel();
  }
  private void btnKeysList(ActionEvent evt){
	  DisplayKeysList kl=new DisplayKeysList();
	  try {
		kl.showKeyslist();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	    //Create separate class for implementing this function
  }
	
	private void btnRevokedKeysList(ActionEvent evt){
		DisplayRevokedKeys dr= new DisplayRevokedKeys();
		try {
			dr.showRevokedUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		  
		    
	  }
	 
  
}