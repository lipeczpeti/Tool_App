package hu.unideb.inf.eszkozrest.Controller;

import hu.unideb.inf.eszkozrest.Dto.EszkozDto;
import hu.unideb.inf.eszkozrest.Service.EszkozService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

@Controller("eszkozFetcherController")
@RequestScope
@Getter
@Setter
public class EszkozFetcherController {

    private List<EszkozDto> tools;

    @Autowired
    private EszkozService eszkozService;

    @PostConstruct
    public void initController() {
        tools = eszkozService.listAll();
    }
}
