package br.com.jota.shophub.services;

import br.com.jota.shophub.domain.interfaces.EntidadeComEmail;
import br.com.jota.shophub.exception.RegraDeNegorcioException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService<T extends EntidadeComEmail> {

    private final JavaMailSender enviadorEmail;
    private static final String EMAIL_ORIGEM = "shophub@email.com";
    private static final String NOME_ENVIADOR = "ShopHub";

    public static final String URL_SITE = "http://localhost:8080"; // "shophub.com.br"

    public EmailService(JavaMailSender enviadorEmail) {
        this.enviadorEmail = enviadorEmail;
    }

    @Async
    private void enviarEmail(String emailUsuario, String assunto, String conteudo) {
        MimeMessage message = enviadorEmail.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(EMAIL_ORIGEM, NOME_ENVIADOR);
            helper.setTo(emailUsuario);
            helper.setSubject(assunto);
            helper.setText(conteudo, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RegraDeNegorcioException("Erro ao enviar email");
        }

        enviadorEmail.send(message);
    }

    public void enviarEmailVerificacao(T entidade) {
        String assunto = "Aqui está seu link para verificar o email";
        String conteudo = gerarConteudoEmail("Olá [[nome]], <br>" +
                "Por favor clique no link abaixo para verificar sua conta: <br>" +
                "<h3><a href=\"[[URL]]\" target=\"_sefl\">Verificar</a></h3>" +
                "Obrigado!<br>" +
                "Shop Hub :).", entidade.getNome(), URL_SITE + entidade.getRotaVerificacao().getRota()  + "/" + entidade.getId());
        enviarEmail(entidade.getUsername(), assunto, conteudo);
    }

    private String gerarConteudoEmail(String template, String nome, String url) {
        return template.replace("[[nome]]", nome).replace("[[URL]]", url);
    }

}
