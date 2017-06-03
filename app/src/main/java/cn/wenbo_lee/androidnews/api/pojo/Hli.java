package cn.wenbo_lee.androidnews.api.pojo;

/**
 * Created by Administrator on 2016-11-26.
 */

public class Hli {

    /**
     * reason : successed
     * result : {"id":"1666","yangli":"2014-09-09","yinli":"甲午(马)年八月十六","wuxing":"杨柳木 开执位","chongsha":"冲牛(丁丑)煞西","baiji":"癸不词讼理弱敌强 未不服药毒气入肠","jishen":"天恩 母仓 月德 不将 四相 阴德 金堂 时阳 生气 天仓","yi":"祭祀 立碑 修坟 启钻 除服 成服 馀事勿取","xiongshen":"五虚 土符 触水龙","ji":"馀事勿取"}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * id : 1666
         * yangli : 2014-09-09
         * yinli : 甲午(马)年八月十六
         * wuxing : 杨柳木 开执位
         * chongsha : 冲牛(丁丑)煞西
         * baiji : 癸不词讼理弱敌强 未不服药毒气入肠
         * jishen : 天恩 母仓 月德 不将 四相 阴德 金堂 时阳 生气 天仓
         * yi : 祭祀 立碑 修坟 启钻 除服 成服 馀事勿取
         * xiongshen : 五虚 土符 触水龙
         * ji : 馀事勿取
         */

        private String id;
        private String yangli;
        private String yinli;
        private String wuxing;
        private String chongsha;
        private String baiji;
        private String jishen;
        private String yi;
        private String xiongshen;
        private String ji;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYangli() {
            return yangli;
        }

        public void setYangli(String yangli) {
            this.yangli = yangli;
        }

        public String getYinli() {
            return yinli;
        }

        public void setYinli(String yinli) {
            this.yinli = yinli;
        }

        public String getWuxing() {
            return wuxing;
        }

        public void setWuxing(String wuxing) {
            this.wuxing = wuxing;
        }

        public String getChongsha() {
            return chongsha;
        }

        public void setChongsha(String chongsha) {
            this.chongsha = chongsha;
        }

        public String getBaiji() {
            return baiji;
        }

        public void setBaiji(String baiji) {
            this.baiji = baiji;
        }

        public String getJishen() {
            return jishen;
        }

        public void setJishen(String jishen) {
            this.jishen = jishen;
        }

        public String getYi() {
            return yi;
        }

        public void setYi(String yi) {
            this.yi = yi;
        }

        public String getXiongshen() {
            return xiongshen;
        }

        public void setXiongshen(String xiongshen) {
            this.xiongshen = xiongshen;
        }

        public String getJi() {
            return ji;
        }

        public void setJi(String ji) {
            this.ji = ji;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", yangli='" + yangli + '\'' +
                    ", yinli='" + yinli + '\'' +
                    ", wuxing='" + wuxing + '\'' +
                    ", chongsha='" + chongsha + '\'' +
                    ", baiji='" + baiji + '\'' +
                    ", jishen='" + jishen + '\'' +
                    ", yi='" + yi + '\'' +
                    ", xiongshen='" + xiongshen + '\'' +
                    ", ji='" + ji + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Hli{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }
}
