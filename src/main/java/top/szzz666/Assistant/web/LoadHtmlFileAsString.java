package top.szzz666.Assistant.web;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadHtmlFileAsString {


    public static String loadHtmlFileAsString(String htmlFilePath) {
        try {
            // 使用Files.readAllBytes()方法读取文件的所有字节
            byte[] bytes = Files.readAllBytes(Paths.get(htmlFilePath));

            // 使用String构造函数将字节转换为字符串，这里指定字符集为UTF-8
            return new String(bytes, StandardCharsets.UTF_8);

        } catch (IOException e) {
            // 处理可能出现的IO异常
            e.fillInStackTrace();
            return "网页文件丢失";
        }

    }
}