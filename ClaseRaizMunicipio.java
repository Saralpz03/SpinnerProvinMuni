package com.example.spinnerretrifit;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class ClaseRaizMunicipio {
    @ElementList
    private List<Municipio> municipiero;

    public List<Municipio> getMunicipiero() {
        return municipiero;
    }

    public void setMunicipiero(List<Municipio> municipiero) {
        this.municipiero = municipiero;
    }

    @Override
    public String toString() {
        return ""+municipiero;
    }
}
