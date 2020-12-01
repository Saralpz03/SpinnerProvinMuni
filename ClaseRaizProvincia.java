package com.example.spinnerretrifit;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class ClaseRaizProvincia {
    @ElementList
    private List<Provincia> provinciero;

    public List<Provincia> getProvinciero() {
        return provinciero;
    }

    public void setProvinciero(List<Provincia> provinciero) {
        this.provinciero = provinciero;
    }

    @Override
    public String toString() {
        return ""+provinciero;
    }
}
