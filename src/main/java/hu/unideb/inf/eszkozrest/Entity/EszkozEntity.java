package hu.unideb.inf.eszkozrest.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "eszkoz")
public class EszkozEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "marka")
    private String marka;

    @Column(name = "tipus")
    private String tipus;

    @Column(name = "leiras")
    private String leiras;

    @Column(name = "jelleg")
    private String jelleg;

    @ManyToOne
    @JoinColumn(name = "tulajdonos", nullable = true)
    private TulajdonosEntity tulajdonos;

    @Override
    public String toString() {
        return "EszkozEntity{" +
                "id=" + id +
                ", marka='" + marka + '\'' +
                ", tipus='" + tipus + '\'' +
                ", leiras='" + leiras + '\'' +
                ", jelleg='" + jelleg + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EszkozEntity that = (EszkozEntity) o;
        return id == that.id && Objects.equals(marka, that.marka) && Objects.equals(tipus, that.tipus) && Objects.equals(leiras, that.leiras) && Objects.equals(jelleg, that.jelleg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marka, tipus, leiras, jelleg);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public String getJelleg() {
        return jelleg;
    }

    public void setJelleg(String jelleg) {
        this.jelleg = jelleg;
    }

    public EszkozEntity(long id, String marka, String tipus, String leiras, String jelleg) {
        this.id = id;
        this.marka = marka;
        this.tipus = tipus;
        this.leiras = leiras;
        this.jelleg = jelleg;
    }

    public EszkozEntity(String marka, String tipus, String leiras, String jelleg) {
        this.marka = marka;
        this.tipus = tipus;
        this.leiras = leiras;
        this.jelleg = jelleg;
    }

    public EszkozEntity() {
    }

    public TulajdonosEntity getTulajdonos() {
        return tulajdonos;
    }

    public EszkozEntity(long id, String marka, String tipus, String leiras, String jelleg, TulajdonosEntity tulajdonos) {
        this.id = id;
        this.marka = marka;
        this.tipus = tipus;
        this.leiras = leiras;
        this.jelleg = jelleg;
        this.tulajdonos = tulajdonos;
    }

    public void setTulajdonos(TulajdonosEntity tulajdonos) {
        this.tulajdonos = tulajdonos;
    }
}
