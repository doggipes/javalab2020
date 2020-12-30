package ru.javalab.annotations.generator.model;

public class FormSignature {
    private String method;
    private String action;

    public FormSignature(String method, String action) {
        this.method = method;
        this.action = action;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
