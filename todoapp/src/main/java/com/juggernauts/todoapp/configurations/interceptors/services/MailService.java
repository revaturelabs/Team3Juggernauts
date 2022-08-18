package com.juggernauts.todoapp.configurations.interceptors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juggernauts.todoapp.clients.MailgunClient;
import com.juggernauts.todoapp.models.MailgunResponse;

/**
 * A service for sending e-mails through the Mailgun API
 * @author Jacob
 */
@Service
public class MailService {
    private static final String FROM = "Mailgun Sandbox <postmaster@sandbox2f4262e762e340fe926e9817e52a3ca1.mailgun.org>";

    @Autowired
    private MailgunClient client;

    public MailgunResponse sendEmail(String to, String subject, String text) {
        return this.client.sendEmail(FROM, to, subject, text);
    }
}
