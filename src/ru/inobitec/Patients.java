package ru.inobitec;

import java.util.*;

public class Patients {
    public List<Patient> patientsList;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Patient patient : patientsList) {
            sb.append(patient.toString());
        }
        return sb.toString();
    }

    public String toStringSorted(String sortType) {
        switch (sortType) {
            case "age" -> patientsList.sort(Comparator.comparing(Patient::getAge));
            case "name" -> patientsList.sort(Comparator.comparing(Patient::getLast_name));
        }
        return this.toString();
    }
}
