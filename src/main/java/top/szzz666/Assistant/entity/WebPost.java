package top.szzz666.Assistant.entity;

public class WebPost {
    private String PlayerName;
    private String Processing;
    private String Parameter;
    private String Username;
    private String Password;


    public WebPost() {
    }

    public WebPost(String PlayerName, String Processing, String Parameter, String Username, String Password) {
        this.PlayerName = PlayerName;
        this.Processing = Processing;
        this.Parameter = Parameter;
        this.Username = Username;
        this.Password = Password;
    }

    /**
     * 获取
     * @return PlayerName
     */
    public String getPlayerName() {
        return PlayerName;
    }

    /**
     * 设置
     * @param PlayerName
     */
    public void setPlayerName(String PlayerName) {
        this.PlayerName = PlayerName;
    }

    /**
     * 获取
     * @return Processing
     */
    public String getProcessing() {
        return Processing;
    }

    /**
     * 设置
     * @param Processing
     */
    public void setProcessing(String Processing) {
        this.Processing = Processing;
    }

    /**
     * 获取
     * @return Parameter
     */
    public String getParameter() {
        return Parameter;
    }

    /**
     * 设置
     * @param Parameter
     */
    public void setParameter(String Parameter) {
        this.Parameter = Parameter;
    }

    /**
     * 获取
     * @return Username
     */
    public String getUsername() {
        return Username;
    }

    /**
     * 设置
     * @param Username
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }

    /**
     * 获取
     * @return Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * 设置
     * @param Password
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String toString() {
        return "WebPost{PlayerName = " + PlayerName + ", Processing = " + Processing + ", Parameter = " + Parameter + ", Username = " + Username + ", Password = " + Password + "}";
    }
}
