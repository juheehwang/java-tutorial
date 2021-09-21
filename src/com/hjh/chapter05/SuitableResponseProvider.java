package com.hjh.chapter05;

public class SuitableResponseProvider {
    private final String commendLine;
    private FileNameValidator fileNameValidator;
    private ResponseDataGenerator responseDataGenerator;

    public SuitableResponseProvider(String commendLine) {
        this.commendLine = commendLine;
    }

    public String forwardToFileWriter() {
        fileNameValidator = new FileNameValidator(commendLine);
        String result = "";
        int statusCode;
        String message;

        if (fileNameValidator.validate()) {
            responseDataGenerator = new ResponseDataGenerator(fileNameValidator.getRequestedFile());
            if (fileNameValidator.getRequestedFile().length() > 10 * 1024 * 1024) {
                statusCode = 400;
                message = "File is too large, Cannot serve a file larger than 10MB: requested file size is : " + fileNameValidator.getRequestedFile().length();
            } else {
                statusCode = 200;
                message = "Response is successfully";
            }
            result = responseDataGenerator.getResponseHeader(statusCode, message);

        } else {
            responseDataGenerator = new ResponseDataGenerator(fileNameValidator.getRequestedFile());
            statusCode = 404;
            message = "No Such File Found: " + fileNameValidator.getRequestedFile().getAbsolutePath();
            result = responseDataGenerator.getResponseHeader(statusCode, message);

        }

        return result;
    }

}
