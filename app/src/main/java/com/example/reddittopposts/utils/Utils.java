package com.example.reddittopposts.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static String convertUTC(Long created) {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();

        long diff = today.getTime() - created * 1000;

        String ans = "";
        if (diff > 0 && diff < TimeUnit.MINUTES.toMillis(60)) {
            ans += TimeUnit.MILLISECONDS.toMinutes(diff) + " minutes ago";
        } else if (diff > TimeUnit.MINUTES.toMillis(60) + 1L && diff < TimeUnit.HOURS.toMillis(24)) {
            ans += TimeUnit.MILLISECONDS.toHours(diff) + " hours ago";
        } else {
            ans += TimeUnit.MILLISECONDS.toDays(diff) + " days ago";
        }

        return ans;
    }
}
