package com.jiangkang.klint;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import com.jiangkang.klint.detector.LogUtilsDetector;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class KLintRegistry extends IssueRegistry {

    @NotNull
    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(
                LogUtilsDetector.ISSUE
//                ToastUsageDetector.Companion.getISSUE()
        );
    }
}
