package util;


import java.lang.reflect.Type;

public class ReflectionUtils {

//    public static <T> Class getGenericClass(Class cls) { //, Class<?> classOfInterest, int parameterIndex) {
//        Type t = cls.getGenericSuperclass();
//        String typeName = t.getTypeName();
//        return null;
//    }

    public static <T extends Comparable<T>> int compareTo(T thisVal, T thatVal) {
        return thisVal.compareTo(thatVal);
    }

    public static <T extends Comparable> boolean lt(T thisVal, T thatVal) {
        if (thisVal == null && thatVal == null) return false;
        if (thisVal == null) return true;
        if (thatVal == null) return false;
        return (thisVal.compareTo(thatVal) == -1);
    }

    public static <T extends Comparable> boolean gt(T thisVal, T thatVal) {
        if (thisVal == null && thatVal == null) return false;
        if (thisVal == null) return false;
        if (thatVal == null) return true;
        return (thisVal.compareTo(thatVal) == 1);
    }

    public static <T extends Comparable> boolean eq(T thisVal, T thatVal) {
        if (thisVal == null && thatVal == null) return true;
        if (thisVal == null) return false;
        if (thatVal == null) return false;
        return (thisVal.compareTo(thatVal) == 0);
    }
}
