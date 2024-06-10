//package top.szzz666.Assistant.xlink;
//
//import cn.nukkit.Player;
//import com.google.gson.Gson;
//import top.szzz666.XLink.entity.Message;
//import top.szzz666.XLink.websocket.InitWebSocketClient;
//
//import static top.szzz666.Assistant.dispose.DealWithPlayers.*;
//import static top.szzz666.Assistant.dispose.HandleWebUI.getPlayerByName;
//import static top.szzz666.XLink.XLinkMain.*;
//import static top.szzz666.XLink.utils.AES.encrypt;
//import static top.szzz666.XLink.websocket.MyWebSocketServer.sendMessageToAllWs;
//
//public class ProcessInfo {
//    public static void SendProcessInfo(String playerName, String processing, String parameter) {
//        String sendText = playerName + "&" + processing + "&" + parameter;
//        Message message = new Message("Assistant", sendText, encrypt(WsPassword, key));
//        Gson gson = new Gson();
//        String sendTextJson = gson.toJson(message);
//        if (IsParentServer) {
//            sendMessageToAllWs(sendTextJson, null);
//        } else {
//            InitWebSocketClient.client.send(sendTextJson);
//        }
//    }
//
//    public static void ReceiveProcessInfo(Message message) {
//        if (message.getType().equals("Assistant")) {
//            String[] split = message.getText().split("&");
//            String playerName = split[0];
//            String processing = split[1];
//            String parameter = split[2];
//            Player playersBeingDealtWith = getPlayerByName(playerName);
//            if (playersBeingDealtWith != null) {
//                switch (processing) {
//                    case "1":
//                        killPlayer(playersBeingDealtWith, parameter);
//                        break;
//                    case "2":
//                        kickPlayer(playersBeingDealtWith, parameter);
//                        break;
//                    case "3":
//                        banPlayer(playersBeingDealtWith, parameter);
//                        break;
//                    case "4":
//                        banPlayerIP(playersBeingDealtWith, parameter);
//                        break;
//                    default:
//                        warnPlayer(playersBeingDealtWith, parameter);
//                        break;
//                }
//            }
//        }
//    }
//}
//
