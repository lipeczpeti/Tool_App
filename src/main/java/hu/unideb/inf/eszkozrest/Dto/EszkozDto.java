package hu.unideb.inf.eszkozrest.Dto;

import java.util.Objects;

public class EszkozDto {

    private long id;
    private String marka;
    private String tipus;
    private String leiras;
    private String jelleg;
    private TulajdonosDto tulajdonos;

    public EszkozDto() {
    }

    public EszkozDto(String marka, String tipus, String leiras, String jelleg, TulajdonosDto tulajdonos) {
        this.marka = marka;
        this.tipus = tipus;
        this.leiras = leiras;
        this.jelleg = jelleg;
        this.tulajdonos = tulajdonos;
    }

    public EszkozDto(long id, String marka, String tipus, String leiras, String jelleg, TulajdonosDto tulajdonos) {
        this.id = id;
        this.marka = marka;
        this.tipus = tipus;
        this.leiras = leiras;
        this.jelleg = jelleg;
        this.tulajdonos = tulajdonos;
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

    public TulajdonosDto getTulajdonos() {
        return tulajdonos;
    }

    public void setTulajdonos(TulajdonosDto tulajdonos) {
        this.tulajdonos = tulajdonos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EszkozDto eszkozDto = (EszkozDto) o;
        return id == eszkozDto.id && Objects.equals(marka, eszkozDto.marka) && Objects.equals(tipus, eszkozDto.tipus) && Objects.equals(leiras, eszkozDto.leiras) && Objects.equals(jelleg, eszkozDto.jelleg) && Objects.equals(tulajdonos, eszkozDto.tulajdonos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marka, tipus, leiras, jelleg, tulajdonos);
    }

    @Override
    public String toString() {
        return "EszkozDto{" +
                "id=" + id +
                ", marka='" + marka + '\'' +
                ", tipus='" + tipus + '\'' +
                ", leiras='" + leiras + '\'' +
                ", jelleg='" + jelleg + '\'' +
                ", tulajdonos=" + tulajdonos +
                '}';
    }
}

