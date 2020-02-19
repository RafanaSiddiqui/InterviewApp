package com.ip.erss.competency.iamautomation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BackupFile {

	public void getFileBackUp() {
		FileInputStream instream = null;
		FileOutputStream outstream = null;
		// connect to server for time "2.00 am"
		try {
			File initial = new File("D:\\549445\\IAM\\db_backup\\app_db5.mv.db");
			File target = new File("app_db5.mv.db");

			instream = new FileInputStream(initial);
			outstream = new FileOutputStream(target);

			byte[] buffer = new byte[1024];

			int length;

			while ((length = instream.read(buffer)) > 0) {
				outstream.write(buffer, 0, length);
			}

			instream.close();
			outstream.close();

			System.out.println("File copied successfully!!");
			sendMail("The daily expected backup of the file is processed successfully", "Daily FileBack Up");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			sendMail("The daily expected backup of the file is not been processed successfully", "Daily FileBack Up");
		}
	}

	public void sendMail(String messageContent, String subject) {
		String recipient = "karthik.selvam2@ip.com";
		// String sender = "Srishti.Sharma2@ip.com";
		String sender = "Srishti.Sharma2@ip.com";
		String host = "APACSMTP.ip.COM";
		String port = "25";
		Properties properties = System.getProperties();

		// Setting up mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", port);

		// creating session object to get properties
		Session session = Session.getDefaultInstance(properties);

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(sender));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject);
			message.setText(messageContent);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		System.out.println("Mail successfully sent");// optional
	}

	/*public static void main(String[] args) {

		new BackupFile().getFileBackUp();

	}*/

}
