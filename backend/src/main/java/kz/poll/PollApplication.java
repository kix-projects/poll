package kz.poll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;

@SpringBootApplication
@ComponentScan("kz.poll")
@EntityScan(basePackages = {"kz.poll.data.model"})
@EnableMongoRepositories(basePackages = "kz.poll.data.repo")
public class PollApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        // dev, test, prod
//        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, ProfileType.dev.name());
        SpringApplication.run(PollApplication.class, args);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                //.antMatchers("/index.html", "/home.html", "/").permitAll()
                .anyRequest()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**"); // #3
    }



}
