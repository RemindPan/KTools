package com.jiangkang.compiler;

import com.google.auto.common.SuperficialValidation;
import com.google.auto.service.AutoService;
import com.jiangkang.annotations.apt.BindLayout;
import com.jiangkang.annotations.apt.Router;
import com.squareup.javapoet.TypeSpec;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by jiangkang on 2018/3/29.
 * description：
 */
@AutoService(BindLayout.class)
@SupportedAnnotationTypes({"com.jiangkang.annotations.apt.BindLayout"})
public class BindLayoutProcessor extends AbstractProcessor {

    private Elements elements;

    private Filer filer;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        elements = processingEnvironment.getElementUtils();
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        for (Element element : roundEnvironment.getElementsAnnotatedWith(BindLayout.class)) {
            if (!SuperficialValidation.validateElement(element)) continue;
            //得到类信息
            TypeElement typeElement = (TypeElement) element;

            System.out.println(typeElement.getSimpleName());

            //生成类
            TypeSpec.Builder routerHelperClass = TypeSpec
                    .classBuilder("BinderHelper")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

        }
        //不会有别的处理器处理了
        return true;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }
}
