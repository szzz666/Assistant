package top.szzz666.Assistant.web;


import com.google.gson.Gson;
import top.szzz666.Assistant.entity.WebPost;
import top.szzz666.StarrySkyAuth.apis.SAuth;

import static top.szzz666.Assistant.AssistantMain.hs;
import static top.szzz666.Assistant.config.AssistantConfig.AssistantrConfig;
import static top.szzz666.Assistant.config.AssistantConfig.useStarrySkyAuth;
import static top.szzz666.Assistant.dispose.HandleWebUI.handleWebUI;
import static top.szzz666.Assistant.form.AssistantForm.getPlayerNameList;


public class HttpRouter {
    public HttpRouter() {
        hs.post("", (req, res) -> {
            String body = req.body();
            WebPost wp = new Gson().fromJson(body, WebPost.class);
            if (wp == null || wp.getPlayerName().isEmpty() ||
                    wp.getProcessing().isEmpty() || wp.getParameter().isEmpty()) {
                return "{\"code\":\"401\"}";
            }
            if (!AssistantrConfig.containsKey(wp.getUsername())) {
                return "{\"code\":\"402\"}";
            }
            if (useStarrySkyAuth) {
                if (wp.getUsername().isEmpty() || wp.getPassword().isEmpty()
                        || (!new SAuth().isAuthSuccess(wp.getUsername(), wp.getPassword()))) {
                    return "{\"code\":\"403\"}";
                }
            }
            if (!useStarrySkyAuth) {
                if (wp.getUsername().isEmpty() || wp.getPassword().isEmpty()
                        || !(wp.getPassword().equals(AssistantrConfig.get(wp.getUsername())))) {
                    return "{\"code\":\"403\"}";
                }
            }
            if (handleWebUI(wp)) {
                return "{\"code\":\"200\"}";
            }
            return "{\"code\":\"0\"}";

        });
        hs.get("", (req, res) -> {
            if (req.queryParams("getPlayerNameList") != null) {
                Gson gson = new Gson();
                return gson.toJson(getPlayerNameList());
            }
            return null;
        });

    }
}


