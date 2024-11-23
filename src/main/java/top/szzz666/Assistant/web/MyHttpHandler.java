package top.szzz666.Assistant.web;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import top.szzz666.Assistant.entity.WebPost;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.Objects;

import static top.szzz666.Assistant.AssistantMain.AssistantConfigPath;
import static top.szzz666.Assistant.AssistantMain.plugin;
import static top.szzz666.Assistant.config.AssistantConfig.AssistantrConfig;
import static top.szzz666.Assistant.dispose.HandleWebUI.handleWebUI;
import static top.szzz666.Assistant.form.AssistantForm.getPlayerNameList;
import static top.szzz666.Assistant.web.LoadHtmlFileAsString.loadHtmlFileAsString;


public class MyHttpHandler implements HttpHandler {
    // 自定义方法：读取输入流为字符串
    private String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // 获取请求方法
        String requestMethod = exchange.getRequestMethod();

        /// 获取客户端IP
        InetSocketAddress clientAddress = exchange.getRemoteAddress();
        String clientIp = clientAddress.getAddress().getHostAddress();

        if (requestMethod.equalsIgnoreCase("GET")) {
            plugin.getLogger().info("接到来自" + clientIp + "的GET请求");
            // 获取查询参数
            String query = exchange.getRequestURI().getQuery();
            plugin.getLogger().info("获得请求参数：" + query);
            if (query == null) {
                // 准备HTML响应
                String response = loadHtmlFileAsString(AssistantConfigPath + "/webui.html");

                // 设置响应头
                exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
                exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
                // 写入响应状态码和内容长度
                exchange.sendResponseHeaders(200, response.getBytes().length);
                // 输出响应内容
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                plugin.getLogger().info("HTML响应已发送至" + clientIp);
            }
            if (Objects.equals(query, "getPlayerNameList")) {
                Gson gson = new Gson();
                String JsonPlayerNameList = gson.toJson(getPlayerNameList());
                // 设置响应头
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
                // 写入响应状态码和内容长度
                exchange.sendResponseHeaders(200, JsonPlayerNameList.getBytes().length);
                // 发送响应
                OutputStream responseJson = exchange.getResponseBody();
                responseJson.write(JsonPlayerNameList.getBytes());
                responseJson.close();
                plugin.getLogger().info(JsonPlayerNameList + "响应已发送至" + clientIp);
            }
        } else if (requestMethod.equalsIgnoreCase("POST")) {
            plugin.getLogger().info("接收到来自" + clientIp + "的POST请求");

            // 读取POST请求的实体内容（请求体）
            InputStream inputStream = exchange.getRequestBody();
            String body = readInputStream(inputStream); // 自定义方法，用于读取输入流并转换为字符串
            String showbody = body.replaceAll("(\"Password\":\")([^\"]*)\"", "$1******\"");
            plugin.getLogger().info("POST请求的数据：" + showbody);
            String Response;
            Gson gson = new Gson();
            WebPost wp = gson.fromJson(body, WebPost.class);
            if (wp == null || wp.getPlayerName().isEmpty() ||
                    wp.getProcessing().isEmpty() || wp.getParameter().isEmpty()) {
                Response = "{\"code\":\"401\"}";
            } else if (!AssistantrConfig.containsKey(wp.getUsername())) {
                Response = "{\"code\":\"402\"}";
            } else if (wp.getUsername().isEmpty() || wp.getPassword().isEmpty()
                    || (!AssistantrConfig.get(wp.getUsername()).equals(wp.getPassword()))) {
                Response = "{\"code\":\"403\"}";
            } else if (handleWebUI(wp)) {
                Response = "{\"code\":\"200\"}";
            } else {
                Response = "{\"code\":\"0\"}";
            }

            // 设置响应头
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            // 写入响应状态码和内容长度
            exchange.sendResponseHeaders(200, Response.getBytes().length);
            // 输出响应内容
            OutputStream os = exchange.getResponseBody();
            os.write(Response.getBytes());
            os.close();
            plugin.getLogger().info("POST响应已发送至" + clientIp);
        } else {
            // 不支持的请求方法
            exchange.sendResponseHeaders(405, 0);
            plugin.getLogger().info("不支持的请求");
        }
    }
}

