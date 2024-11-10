package com.example.twofactordemo;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    private static final String SMTP_SERVER = "smtp-relay.brevo.com"; // Brevo's SMTP server
    private static final String SMTP_PORT = "587"; // Brevo's SMTP port
    private static final String SENDER_EMAIL = "artin.rexhepi1@student.uni-pr.edu"; // Replace with your Brevo-verified email
    private static final String SENDER_PASSWORD = "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"; // Replace with your Brevo API key


    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Send OTP to recipient's email on a background thread
    public static void sendEmailInBackground(String recipientEmail, String otp) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                sendEmail(recipientEmail, otp);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }


    private static void sendEmail(String recipientEmail, String otp) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_SERVER);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SENDER_EMAIL));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);

        Transport.send(message);
    }
}
