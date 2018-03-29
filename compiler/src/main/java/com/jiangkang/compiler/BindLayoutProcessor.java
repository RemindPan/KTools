package com.jiangkang.compiler;

import com.google.auto.service.AutoService;
import com.jiangkang.annotations.apt.BindLayout;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;

/**
 * Created by jiangkang on 2018/3/29.
 * description：
 */
@AutoService(BindLayout.class)
@SupportedAnnotationTypes({"com.jiangkang.annotations.apt"})
public class BindLayoutProcessor extends AbstractProcessor{


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {





        return false;
    }
}
