package controllers.temporary;

import java.util.HashMap;

public class InitializeStudents {

    static public HashMap<String, Student> initializeStudents(){
        String CARD_KZ = "0136E883";
        Student KZ_student = new Student(CARD_KZ,132188,"Kamil","Zieliński","WE","I4","kamil.k.zielinski@student.put.poznan.pl");
        String CARD_MS = "0136C1DE";
        Student MS_student = new Student(CARD_MS, 131831,"Marcin","Stasiak","WE","I4","marcin.r.stasiak@student.put.poznan.pl");
        String CARD_KK = "01371B68";
        Student KK_student = new Student(CARD_KK, 131776,"Konrad","Karkos","WE","I4","konrad.karkos@student.put.poznan.pl");
        HashMap<String, Student> hashMap = new HashMap<>();
        hashMap.put(CARD_KZ, KZ_student);
        hashMap.put(CARD_MS, MS_student);
        hashMap.put(CARD_KK, KK_student);
        return hashMap;
    }
}
