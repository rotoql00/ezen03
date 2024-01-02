/*
 * FileName: MailHelper.java
 * Description: 메일을 보내기 위한 Helper
 * Author: 임석현
 */
package com.ezen03.tieshoe.helper;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailHelper {
    // --> import org.springframework.mail.javamail.JavaMailSender;
    JavaMailSender mailSender;

    public MailHelper(JavaMailSender sender) {
        this.mailSender = sender;
    }

    /**
     * 메일을 발송한다.
     * 
     * @param receiver - 수신자 메일 주소
     * @param subject  - 제목
     * @param content  - 내용
     * @throws MessagingException
     */
    // --> import javax.mail.MessagingException;
    public void sendMail(String receiver, String subject, String content) 
        throws Exception {

        log.debug("----------------------------------------------------");
        log.debug(String.format("To: %s", receiver));
        log.debug(String.format("Subject: %s", subject));
        log.debug(String.format("Content: %s", content));
        log.debug("----------------------------------------------------");

        // --> import javax.mail.internet.MimeMessage;
        MimeMessage message = mailSender.createMimeMessage();
        // --> import org.springframework.mail.javamail.MimeMessageHelper;
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject(subject);
        // HTML 타입으로 보내기 위해 수정하였습니다. ()
        helper.setText(content, true);
        helper.setTo(new InternetAddress(receiver));
        mailSender.send(message);
    }
}
