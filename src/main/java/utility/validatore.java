package utility;

public class validatore { public static boolean campiVuoti(String... valori) {
    for (String val : valori) {
        if (val == null || val.isEmpty()) return true;
    }
    return false;
}

}
