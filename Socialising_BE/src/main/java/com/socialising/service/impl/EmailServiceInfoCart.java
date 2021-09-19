package com.socialising.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.socialising.dto.request.MailRequest;
import com.socialising.dto.response.MailResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailServiceInfoCart {
	 @Autowired
	    private Configuration config;
		 @Autowired
		    private JavaMailSender sender;
		 public MailResponse sendEmail(MailRequest request, Map<String, Object> model) {
			 MailResponse response = new MailResponse();
		        MimeMessage message = sender.createMimeMessage();
		        try {
		   
		            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
		                    StandardCharsets.UTF_8.name());
		 
		           // helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

		            Template t = config.getTemplate("emailInfocart.ftl");
		            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

		            helper.setTo(request.getTo());
		            helper.setText(html, true);
		            helper.setSubject(request.getSubject());
		            helper.setFrom(request.getFrom());
		            sender.send(message);

		            response.setMessage("mail send to : " + request.getTo());
		            response.setStatus(Boolean.TRUE);

		        } catch (MessagingException | IOException | TemplateException e) {
		            response.setMessage("Mail Sending failure : "+e.getMessage());
		            response.setStatus(Boolean.FALSE);
		        }
		        return response;
		    }
}
