package com.jiangkang.klint.detector;

import com.android.annotations.Nullable;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.LintFix;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.SourceCodeScanner;
import com.intellij.psi.PsiMethod;

import org.jetbrains.uast.UCallExpression;

import java.util.Arrays;
import java.util.List;

public class LogUtilsDetector extends Detector implements SourceCodeScanner {


    public static final Issue ISSUE = Issue.create(
            "LogUtilsNotUsed",
            "应该使用`LogUtils`替换原生的`Log`",
            "LogUtil 设置了开关，功能更加强大",
            Category.MESSAGES,
            9,
            Severity.ERROR,
            new Implementation(LogUtilsDetector.class, Scope.JAVA_FILE_SCOPE)
    );


    //返回这个Detector感兴趣的method name 列表，如果返回不为null，则任何匹配列表中元素的AST节点都会被
    //传递给[.visitMethod]方法进行处理,被[.createPsiMethod]创建的visitor，不论是否为null，都会被传递给[.visitMethod]
    @Nullable
    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList(
                "v",
                "d",
                "i",
                "w",
                "e",
                "wtf"
        );
    }




    @Override
    public void visitMethod(JavaContext context, UCallExpression node, PsiMethod method) {

        if (!context.getEvaluator().isMemberInClass(method, "android.util.Log")) {
            return;
        }

        //修改bug
        LintFix fix = LintFix.create().name("Use LogUtils instead").replace()
                .pattern("Log")
                .with("LogUtils").build();

        context.report(ISSUE,
                node,
                context.getLocation(node),
                "You must use our `LogUtils`", fix);
    }

}
