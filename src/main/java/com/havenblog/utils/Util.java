package com.havenblog.utils;

import com.havenblog.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by haven .
 */
public class Util {
    public static User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
