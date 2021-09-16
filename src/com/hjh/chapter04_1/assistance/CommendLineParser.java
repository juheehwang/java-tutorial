package com.hjh.chapter04_1.assistance;

import java.io.IOException;
import java.io.Writer;

public class CommendLineParser {

    private String commendUtilName;

    public boolean confirmProperCommendLine(String commendLine) {
        boolean result = true;

        if (!commendLine.startsWith(CommonUtilization.COMMEND_LINE_PREFIX)) {
            result = false;
        } else {
            commendUtilName = commendLine.substring(CommonUtilization.COMMEND_LINE_PREFIX.length());
        }
        return result;
    }

    public String offerUtilCommendLine(Writer writer, boolean result) throws IOException {
        String commendLineUtilResult = "";
        if (result) {
            writer.write("not a proper / invalid File Name");
            writer.flush();
        } else {
            commendLineUtilResult = CommonUtilization.ROOT_PATH + commendUtilName;
        }
        return commendLineUtilResult;
    }
}
