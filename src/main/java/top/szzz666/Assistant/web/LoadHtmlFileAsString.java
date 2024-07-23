package top.szzz666.Assistant.web;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadHtmlFileAsString {


    public static String loadHtmlFileAsString(String htmlFilePath) {
        try {

            byte[] bytes = Files.readAllBytes(Paths.get(htmlFilePath));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.fillInStackTrace();
            return "网页文件丢失";
        }

    }
}