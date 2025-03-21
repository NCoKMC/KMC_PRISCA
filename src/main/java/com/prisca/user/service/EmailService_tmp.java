package com.prisca.user.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import com.prisca.user.dto.UserAuthDto;
import com.prisca.user.dto.UserDto;
import com.prisca.user.mapper.UserMapper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EmailService_tmp {

	//의존성 주입을 통해서 필요한 객체를 가져온다.
    private final JavaMailSender emailSender;
    // 타임리프를사용하기 위한 객체를 의존성 주입으로 가져온다
    private final SpringTemplateEngine templateEngine;
    private String authNum; //랜덤 인증 코드

    @Autowired
    private UserMapper mapper;

    //랜덤 인증 코드 생성
    public void createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i=0;i<8;i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0 :
                    key.append((char) ((int)random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int)random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        authNum = key.toString();
    }

  //메일 양식 작성
    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {

        createCode(); //인증 코드 생성
        String setFrom = "jkthdwogud@naver.com"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
        String title = "새중앙선교센터 선교사 코드 발급 번호"; //제목

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email); //보낼 이메일 설정
        message.setSubject(title); //제목 설정
        message.setFrom(setFrom); //보내는 이메일

        return message;
    }

    //실제 code 메일 전송
    public String sendKmcCodeEmail(UserAuthDto dto) throws MessagingException, UnsupportedEncodingException {


        //메일전송에 필요한 정보 설정
        MimeMessage emailForm = createEmailForm(dto.getEmail());
        Context context = new Context();
        context.setVariable("kmcCd", dto.getKmcCd());
        emailForm.setText(templateEngine.process("mail", context), "utf-8", "html");
        //실제 메일 전송
        emailSender.send(emailForm);

        return "30"; //인증 코드 반환
    }

  //실제 password 메일 전송
    public String sendPwdEmail(UserAuthDto dto) throws MessagingException, UnsupportedEncodingException {

    	//메일전송에 필요한 정보 설정
        MimeMessage emailForm = createEmailForm(dto.getEmail());
        Context context = new Context();
        context.setVariable("imsyPwd", authNum);
        emailForm.setText(templateEngine.process("mail", context), "utf-8", "html");
        //실제 메일 전송
        emailSender.send(emailForm);

        return authNum; //인증 코드 반환
    }

}
