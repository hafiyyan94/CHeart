package com.example.cheart.cheart.app;

/**
 * Created by David Gamaliel on 5/7/2016.
 */
public class EndPoints {
    public static final String BASE_URL = "http://103.229.72.137/gcm_chat/v1/index.php";
    public static final String LOGIN = BASE_URL + "/user/login";
    public static final String USER = BASE_URL + "/user/_ID_";
    public static final String CHAT_ROOMS = BASE_URL + "/chat_rooms";
    public static final String CHAT_THREAD =    BASE_URL + "/chat_rooms/_ID_";
    public static final String CHAT_ROOM_MESSAGE = BASE_URL + "/chat_rooms/_ID_/message";
}
