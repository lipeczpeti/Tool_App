package hu.unideb.inf.eszkozrest.Controller;

import hu.unideb.inf.eszkozrest.Dto.EszkozDto;
import hu.unideb.inf.eszkozrest.Entity.EszkozEntity;
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

@Controller("eszkozUIController")
@SessionScope
@Getter
@Setter
public class EszkozUIController {

    private String marka = "";
    private String tipus;
    private String leiras;
    private String jelleg;
    private String error;
    private boolean hasError = false;
    EszkozDto eszkozEntity;
    private long activeTool;

    @Autowired
    private EszkozService eszkozService;

    public void deleteTool(int id) {
        eszkozService.deleteById(id);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/eszkoz-list.jsf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editTool(int id) {
        eszkozEntity = eszkozService.findById(id);
        marka = eszkozEntity.getMarka();
        tipus = eszkozEntity.getTipus();
        leiras = eszkozEntity.getLeiras();
        jelleg = eszkozEntity.getJelleg();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/eszkoz-modify.jsf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addOwner(int toolId) {
        activeTool = toolId;

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/add-owner.jsf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveChanges() {
        eszkozEntity.setMarka(marka);
        eszkozEntity.setTipus(tipus);
        eszkozEntity.setLeiras(leiras);
        eszkozEntity.setJelleg(jelleg);

        eszkozService.save(eszkozEntity);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/eszkoz-list.jsf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        eszkozService.save(new EszkozDto(marka, tipus, leiras, jelleg, null));
        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Tool save", "Tool saved successfully!"));
    }
}
