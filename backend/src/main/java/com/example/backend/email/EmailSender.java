package com.example.backend.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender emailSender;

    public void sendVerificationEmail(String to, String id) {
        SimpleMailMessage message = new SimpleMailMessage();
        String url = "http://localhost:8080/api/user/activateAccount/" + id;
        String body = "We are excited to tell you that your account is successfully created. Please click on the" +
                "below link to verify your account: " + url;
        message.setTo(to);
        message.setSubject("Verification email!");
        message.setText(body);
        emailSender.send(message);
    }

    public void sendAcceptingEmail(String email, String userId) {
        SimpleMailMessage message = new SimpleMailMessage();
        String url = "http://localhost:8080/api/user/activateAccount/" + userId;
        String body = "We are excited to tell you that your account is accepted.";
        message.setTo(email);
        message.setSubject("Accepted account email!");
        message.setText(body);
        emailSender.send(message);
    }

    public void sendDeclineEmail(String id, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String body = "Sorry to tell you, your account verification request has been declined";
        message.setTo(email);
        message.setSubject("Your account verification request has been declined!");
        message.setText(body);
        emailSender.send(message);
    }

    public void sendAcceptingAccountDeletionMail(String email, String response) {
        SimpleMailMessage message = new SimpleMailMessage();
        String body = "Your request for deleting account is accepted. \n" + response;
        message.setTo(email);
        message.setSubject("Your request for deleting account is accepted!");
        message.setText(body);
        emailSender.send(message);
    }

    public void sendDecliningAccountDeletionMail(String email, String response) {
        SimpleMailMessage message = new SimpleMailMessage();
        String body = "Your request for deleting account is declined. \n" + response;
        message.setTo(email);
        message.setSubject("Your request for deleting account is declined!");
        message.setText(body);
        emailSender.send(message);
    }

    public void sendBookNotify(String email, String id) {
        SimpleMailMessage message = new SimpleMailMessage();
        String body = "Successfully booked entity, reservation id " + id;
        message.setTo(email);
        message.setSubject("Reservation");
        message.setText(body);
        emailSender.send(message);
    }

    public void sendComplaint(String email, String text) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject("Complaint");
        smm.setText(text);
        smm.setTo(email);
        emailSender.send(smm);

    }
}
