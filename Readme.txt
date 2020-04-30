To start the PGP tool run the file Begin.java .
Key Management function:
----------------------------
Before starting the application:
    1.Add these jar files in the Project folder to your classPath:

	-beansbinding-1.2.1
	-bcpg-lw-jdk15on-1.59
	-bcprov-lw-jdk15on-1.59
	-bcprov-jdk14-1.65
	-pgplib-3.2.1 (Open PGP library is used for PGP functions.)

    2. Change the "localPath" variable in LocalPath.java file to your path where the project is saved along with the project folder's 	     name.

    3. Change the "keyStore_Password" variable in LocalPath.java file in case if you want to renew the keystore.

Before encrypting, decrypting or signing any file it is necessary to generate atleast one pair of public private key pair.

Right click the project in the project explorer in Eclipse 
select buildPath--->Add External Archives
Choose the 4 jar files and add it to the project.
Generate Key button ---- Click on the button to create a key pair. 
Once the Key Generation Dialog box opens enter the user id which is suppose to be unique, user name and the private pass key which will be required to access the private key file.
View Key Button ---- To view  the keys created press the button and list of all the keys in the key store will appear.
Revoked key list button ---- To view the list of the keys that has been revoked and can no longer be used for encryption and digital signature. 

On the left top corner there are two menus Key and PGPTool:
-------------------------------------------------------------
Key ----- It presents two options View key details and Revocation of key.
Revoke keys option will allow the user to revoke their keys provided the enter correct pass code which they had set during key generation.

PGPTool ----- It has three options Help, Generate Revokation Certificate,Exit.
Help optin will provide details to user about how to use the application.
Generate Revokation Certificate will allow the user to generate certificate for revocation of keys which will help in revoking keys incase pass key is lost.
Exit will close the application.

Encryption and Decryption:
--------------------------------------------------------------
Enryption button ---- On clicking this button encryption dialog box will open
On clicking the source button browse the file you want to encrypt(input).
On clicking the Target button browse browse to the file location where you wnat to store the encrypted file(output).
Enter the user id of the recipient. 
Press the encrypt Button to encrypt the file incase the user(recipient) has revoked the key the file encryption will not be completed and message will appear.

Decryption Button ---- On clicking this button decryption dialog box will open
On clicking the source button browse the file you want to decrypt(input).
On clicking the Target button browse browse to the file location where you wnat to store the decrypted file(output).
Enter your pass key. 
Press the decrypt Button to decrypt the file incase the pass key is wrong file will not decrypt and message will appear.
 

Digital Signature:
---------------------
Digital Signature button ---- On clicking Digital Signature dialog box will open
Select the file you want to sign using file chooser and then enter your user id and passkey so that the file can be signed using your private key.
     

