package com.example.mateusz.fantasy.Utilities;

import com.example.mateusz.fantasy.Model.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by Mateusz on 10.08.2017.
 */

final public class UserJsonUtils {

    /**
     * Return User if ServerResponse is OK
     * @param json
     * @return User object if User exist in database
     */
    public static User getUserFromJson (String json) {

        Gson gson = new Gson();
        try{
            User user = gson.fromJson(json,User.class);
            return user;
        } catch (JsonSyntaxException e) {
            return null;
        }
    }
}
