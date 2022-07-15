package com.example.medicine.service.util;

import java.util.Locale;
import java.util.Random;

public class DoctorUtilService {
    public static String getMailForDoctor(String doctorName) {
        return doctorName.toLowerCase(Locale.ROOT).trim().replace(" ", "_") + "@clinic.com";
    }

    public static String getPhoneForDoctor() {
        Random random = new Random();
        int l = random.nextInt(9000) + 1000;
        return "8966123" + l;
    }
}
