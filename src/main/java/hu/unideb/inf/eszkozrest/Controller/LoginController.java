package hu.unideb.inf.eszkozrest.Controller;

import hu.unideb.inf.eszkozrest.Entity.UserEntity;
import hu.unideb.inf.eszkozrest.Repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Controller("loginController")
@SessionScope
@Getter
@Setter
public class LoginController {

    private String username = "";
    private String password = "";
    private boolean unsuccessfulLogin = false;
    private boolean loggedIn = false;
    private boolean successfulRegistration = false;

    @Autowired
    HttpSession mySession;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void autoLogout() {
        mySession.invalidate();
        SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext().setAuthentication((Authentication)null);
    }

    public void doLogout() {
        autoLogout();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/login.jsf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void doLogin() {
        try {
            Authentication authenticatedUser = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username, password));
            System.out.println(authenticatedUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/eszkoz-list.jsf");
            loggedIn = true;

        } catch (BadCredentialsException e) {
            System.out.println("unsuccessful login");
            onUnsuccessfulLogin();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void doRegistration() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(password);
        userEntity.setUsername(username);
        userEntity.setRole("ROLE_USER");

        userRepository.save(userEntity);

        successfulRegistration = true;

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/login.jsf?registrationSuccess=true");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
    }

    public void redirectToRegistration() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/registration.jsf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onUnsuccessfulLogin() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/login.jsf?error=true");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasRole(String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(role)) {
                    return true;
                }
            }
        }


        return false;
    }

    public boolean hasAnyRole(String... roles) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

            for (GrantedAuthority authority : authorities) {
                for (String role : roles) {
                    if (authority.getAuthority().equals("ROLE_" + role)) {
                        return true;
                    }
                }
            }
        }


        return false;
    }

    public boolean isSuccessfulRegistration() {
        boolean result = successfulRegistration;
        successfulRegistration = false;

        return result;
    }

    public boolean getUnsuccessfulLogin() {
        boolean current = unsuccessfulLogin;
        unsuccessfulLogin = false;
        return current;
    }
}
