package hu.unideb.inf.eszkozrest.Security;

import hu.unideb.inf.eszkozrest.Service.MysqlUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MysqlUserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);

        return authenticationProvider;
    }

    /**
     * Regisztráljuk a spring framewörkben a fenti authentication providert.
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/login.jsf").permitAll()
                .antMatchers("/registration.jsf").permitAll()
                .antMatchers("/index.jsf").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/**").permitAll()
                .antMatchers("/eszkoz-list.jsf").hasAnyRole("ADMIN", "USER")
                .antMatchers("/eszkoz-modify.jsf").hasAnyRole("ADMIN", "USER")
                .antMatchers("/add-owner.jsf").hasAnyRole("ADMIN", "USER")
                .antMatchers("/list-owner.jsf").hasAnyRole("ADMIN", "USER")
                .antMatchers("/new-eszkoz.jsf").hasAnyRole("ADMIN")
                .antMatchers("/h2-ui/**").permitAll()
                .anyRequest().hasAnyRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable()
                .logout().logoutSuccessUrl("/login.jsf").permitAll();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}









