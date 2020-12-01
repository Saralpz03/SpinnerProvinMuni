package com.example.spinnerretrifit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Provincia {
    @Element
    private String np;

    public String getNp() {
        return np;
    }

    public void setNp(String np) {
        this.np = np;
    }

    @Override
    public String toString() {
        return np;
    }
}
