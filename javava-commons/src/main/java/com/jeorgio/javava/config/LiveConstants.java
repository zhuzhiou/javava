package com.jeorgio.javava.config;

public final class LiveConstants {

    /**
     * 直播中
     */
    public static final String ROOM_STATE_ON_LIVE = "ON_LIVE";

    /**
     * 停止直播
     */
    public static final String ROOM_STATE_STOP_LIVE = "STOP_LIVE";

    /**
     * 流媒体打开中
     */
    //public static final String STREAM_STATE_OPENED = "OPENED";

    /**
     * 流媒体已关闭
     */
    //public static final String STREAM_STATE_CLOSED = "CLOSED";

    /***
     * 正常关闭
     */
    public static final String CLOSE_REASON_LEGAL = "LEGAL";

    /**
     * 超时
     */
    public static final String CLOSE_REASON_TIMEOUT = "TIMEOUT";

    /**
     * 关闭上一次的直播流
     */
    public static final String CLOSE_REASON_PREVIOUS = "PREVIOUS";

    //流媒体格式
    public static final String LIVE_PROTOCOL_RTMP = "RTMP";
    public static final String LIVE_PROTOCOL_HLS = "HLS";
    public static final String LIVE_PROTOCOL_HDL = "HDL";
    public static final String LIVE_PROTOCOL_PIC = "PIC";

    private LiveConstants() {
    }
}
