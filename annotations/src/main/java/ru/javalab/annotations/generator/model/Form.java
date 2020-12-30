package ru.javalab.annotations.generator.model;

import java.util.ArrayList;
import java.util.List;

public class Form {
    private FormSignature formSignature;
    private List<Input> inputs;



    public Form(){
        inputs = new ArrayList<>();
    }

    public Form(FormSignature formSignature){
        this.formSignature = formSignature;
        inputs = new ArrayList<>();
    }

    public FormSignature getFormSignature() {
        return formSignature;
    }

    public void setFormSignature(FormSignature formSignature) {
        this.formSignature = formSignature;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void addInputs(Input input) {
        inputs.add(input);
    }
}
