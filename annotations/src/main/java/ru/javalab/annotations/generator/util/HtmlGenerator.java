package ru.javalab.annotations.generator.util;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import ru.javalab.annotations.generator.model.Form;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HtmlGenerator {
    private final Configuration cfg;

    public HtmlGenerator() {
        cfg = new Configuration(Configuration.VERSION_2_3_30);
            cfg.setClassForTemplateLoading(this.getClass(), "/");
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
    }

    public void generateForm(Form form, String filePath) {
        try {
        Map<String, Object> root = new HashMap<>();
        root.put("form", form);
        Template temp = cfg.getTemplate("test.ftlh");
        File file = new File(filePath);
        FileWriter out = new FileWriter(file);
        temp.process(root, out);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }
}
