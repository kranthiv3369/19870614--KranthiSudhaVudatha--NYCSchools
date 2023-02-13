package com.nycschools.demo.utils;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;

public class Utils {

    public static SpannableStringBuilder getSpannableString(String firstString, String secondString){
        SpannableStringBuilder builder= new SpannableStringBuilder();
        builder.append(firstString, new StyleSpan(android.graphics.Typeface.BOLD), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(" ");
        builder.append(secondString != null ? secondString : "");
        return builder;
    }
}
