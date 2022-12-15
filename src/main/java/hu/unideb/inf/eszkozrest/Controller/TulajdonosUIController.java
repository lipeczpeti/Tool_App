package hu.unideb.inf.eszkozrest.Controller;

import hu.unideb.inf.eszkozrest.Dto.TulajdonosDto;
import hu.unideb.inf.eszkozrest.Service.TulajdonosService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;

@Controller("tulajdonosUIController")
@RequestScope
@Getter
@Setter
public class TulajdonosUIController {

    private String newOwnerName = "";
    private String newOwnerNationalId = "";
    private Date newOwnerBirthdate;
    List<TulajdonosDto> owners;

    @Autowired
    private TulajdonosService tulajdonosService;


    @PostConstruct
    public void fetchOwners() {
        owners = tulajdonosService.findAll();
    }

    public void editOwner(long ownerId, long toolId) {
        tulajdonosService.addOwner(ownerId, toolId);
        System.out.println("Saved");

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/eszkoz-list.jsf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addOwner() {
        tulajdonosService.newOwner(newOwnerName, newOwnerNationalId, newOwnerBirthdate);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/add-owner.jsf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOwner(long ownerId) {
        try {
            tulajdonosService.deleteOwner(ownerId);

            FacesContext.getCurrentInstance().getExternalContext().redirect("/add-owner.jsf");

        } catch (Exception e) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Couldn't delete owner! First you must delete from all tool owners."));
        }
    }
}
