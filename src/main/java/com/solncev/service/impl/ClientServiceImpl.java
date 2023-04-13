package com.solncev.service.impl;


import com.solncev.config.MailConfig;
import com.solncev.dto.ClientResponseDto;
import com.solncev.dto.CreateClientRequestDto;
import com.solncev.model.Client;
import com.solncev.repository.ClientRepository;
import com.solncev.service.ClientService;
import lombok.AllArgsConstructor;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private PasswordEncoder encoder;
    private final JavaMailSender javaMailSender;
    private final MailConfig mailConfig;

    @Override
    public Client getClientById(Integer id) {
        return clientRepository.getClientById(id);
    }

    @Override
    public void deleteByIdNotNull(Integer id) {
        clientRepository.deleteByIdNotNull(id);
    }

    @Override
    public ClientResponseDto create(CreateClientRequestDto createClientRequestDto, String url) {
        String code = RandomString.make(64);
        Client client = Client.builder()
                .name(createClientRequestDto.getName())
                .email(createClientRequestDto.getEmail())
                .birth(createClientRequestDto.getBirth())
                .password(encoder.encode(createClientRequestDto.getPassword()))
                .verificationCode(code)
                .build();
        sendVerificationMail(createClientRequestDto.getEmail(), createClientRequestDto.getName(), code, url);
        return ClientResponseDto.fromEntity(clientRepository.save(client));
    }

    @Override
    public Client getClientByName(String name) {
        return clientRepository.findByEmail(name);
    }

    @Override
    public boolean verify(String verificationCode) {
        System.out.println("asf");
        Client client = clientRepository.findByVerificationCode(verificationCode);
        if (client != null) {
            System.out.println("asdfasdfa");
            client.setVerificationCode(null);
            client.setEnabled(true);
            clientRepository.save(client);
            return true;
        }
        return false;
    }

    @Override
    public void sendVerificationMail(String mail, String name, String code, String url) {
        String from = mailConfig.getFrom();
        String sender = mailConfig.getSender();
        String subject = mailConfig.getSubject();
        String content = mailConfig.getContent();


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(from, sender);

            helper.setTo(mail);
            helper.setSubject(subject);

            content = content.replace("{name}", name);
            content = content.replace("{url}", url + "/verification?code=" + code);

            helper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }
}
