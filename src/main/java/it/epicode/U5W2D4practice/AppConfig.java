package it.epicode.U5W2D4practice;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    public Cloudinary getCloudinary(@Value("${cloudinary.cloud_name}") String cloudName,
                                    @Value("${cloudinary.api_key}") String apiKey,
                                    @Value("${cloudinary.api_secret}") String apiSecret){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        return new Cloudinary(config);
    }

    @Bean
    public JavaMailSenderImpl getJavaMailSender(@Value("${gmail.mail.transport.protocol}") String protocol,
                                                @Value("${gmail.mail.smtp.auth}") String auth,
                                                @Value("${gmail.mail.smtp.starttls.enable}") String starttls,
                                                @Value("${gmail.mail.debug}") String debug,
                                                @Value("${gmail.mail.from}") String from,
                                                @Value("${gmail.mail.from.password}") String password,
                                                @Value("${gmail.smtp.ssl.enable}") String ssl,
                                                @Value("${gmail.smtp.host}") String host,
                                                @Value("${gmail.smtp.port}") String port) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPort(Integer.parseInt(port));
        sender.setUsername(from);
        sender.setPassword(password);

        Properties props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.debug", debug);
        props.put("smtp.ssl.enable", ssl);

        return sender;
    }
}
