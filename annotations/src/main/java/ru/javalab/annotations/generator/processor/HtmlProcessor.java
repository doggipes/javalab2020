package ru.javalab.annotations.generator.processor;

import com.google.auto.service.AutoService;
import ru.javalab.annotations.generator.util.HtmlGenerator;
import ru.javalab.annotations.generator.annotation.HtmlForm;
import ru.javalab.annotations.generator.annotation.HtmlInput;
import ru.javalab.annotations.generator.model.Form;
import ru.javalab.annotations.generator.model.FormSignature;
import ru.javalab.annotations.generator.model.Input;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"ru.javalab.annotations.generator.annotation.HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElement = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        HtmlGenerator htmlGenerator = new HtmlGenerator();
        for(Element element : annotatedElement){
            Form form = new Form();
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(1) + element.getSimpleName().toString() + ".html";

            HtmlForm annotation = element.getAnnotation(HtmlForm.class);
            FormSignature formSignature = new FormSignature(annotation.method(), annotation.action());
            form.setFormSignature(formSignature);

            for (Element element1 : element.getEnclosedElements()) {
                HtmlInput annotation1 = element1.getAnnotation(HtmlInput.class);

                if(annotation1 != null) {
                    Input input = new Input(annotation1.type(), annotation1.name(), annotation1.placeholder());
                    form.addInputs(input);
                }
            }

            htmlGenerator.generateForm(form , path);
        }
        return true;
    }
}
