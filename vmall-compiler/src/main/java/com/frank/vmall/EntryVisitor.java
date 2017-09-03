package com.frank.vmall;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public final class EntryVisitor extends SimpleAnnotationValueVisitor7 {
    private Filer mFiler;
    private String mPackageName;

    public void setFiler(Filer filer) {
        this.mFiler = filer;
    }

    @Override
    public Object visitString(String s, Object o) {
        this.mPackageName = s;
        return o;
    }

    @Override
    public Object visitType(TypeMirror typeMirror, Object o) {
        generateJavaCode(typeMirror);
        return o;
    }

    private void generateJavaCode(TypeMirror typeMirror) {
        final TypeSpec targetActivity =
                TypeSpec.classBuilder("WXEntryActivity")
                        .addModifiers(Modifier.PUBLIC)
                        .addModifiers(Modifier.FINAL)
                        .superclass(TypeName.get(typeMirror))
                        .build();

        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("wechat enter-into file")
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
