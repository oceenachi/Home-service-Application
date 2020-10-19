package com.order.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceUtils {

    private static final int  laundryBills = 5010;
    private static final int  cleaningBills = 6200;
    private static final int  cookingBills = 8700;
    private static final int  buddyBills = 10450;
    private static final int  gardeningBills = 2500;

    public static LocalDateTime formatDate(String dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    public static Double calculateServiceCharge(long duration, String serviceType) throws Exception {

        switch (serviceType) {
            case "LAUNDRY" :
                return (double) duration * laundryBills;
            case "HOUSE_CLEANING" :
                return (double) duration * cleaningBills;
            case "COOKING" :
                return (double) duration * cookingBills;
            case "GARDENING" :
                return (double) duration * gardeningBills;
            case "HOME_BUDDY" :
                return (double) duration * buddyBills;
            default :
                throw new Exception("Service not offered");
        }
    }


}
