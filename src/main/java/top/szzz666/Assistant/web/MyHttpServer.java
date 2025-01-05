package top.szzz666.Assistant.web;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import static top.szzz666.Assistant.AssistantMain.plugin;


@Deprecated
public class MyHttpServer {
    public static int WebUiPort = 8000;
    public static void httpServer(){
        // 创建并绑定HTTP服务器到本地的8000端口，第二个参数表示连接队列长度，0表示使用默认值
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(WebUiPort), 0);
        } catch (IOException e) {
            plugin.getLogger().error("WebUI服务器启动失败,查看端口是否被占用");
            e.fillInStackTrace();
        }

        // 设置处理"/test"路径请求的处理器，这里使用自定义的MyHandler类
        server.createContext("/", new MyHttpHandler());

        // 设置服务器的执行器为null，意味着使用其默认的执行策略（通常是一个基于线程池的执行器）
        server.setExecutor(null);

        // 启动服务器
        server.start();
        plugin.getLogger().info("WebUI启动成功，访问地址：http://localhost:"+WebUiPort+"/");

    }

}

