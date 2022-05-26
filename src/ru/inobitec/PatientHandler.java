package ru.inobitec;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientHandler extends DefaultHandler {
    private static final String PATIENTS = "patients";
    private static final String PATIENT = "patient";
    private static final String FIRST_NAME = "first_name";
    private static final String MIDDLE_NAME = "middle_name";
    private static final String LAST_NAME = "last_name";
    private static final String BIRTHDAY = "birthday";
    private static final String GENDER = "gender";
    private static final String PHONE = "phone";

    private Patients allPatients;
    private StringBuilder elementValue;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        allPatients = new Patients();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        switch (qName) {
            case PATIENTS -> allPatients.patientsList = new ArrayList<>();
            case PATIENT -> allPatients.patientsList.add(new Patient());
            case FIRST_NAME -> elementValue = new StringBuilder();
            case MIDDLE_NAME -> elementValue = new StringBuilder();
            case LAST_NAME -> elementValue = new StringBuilder();
            case BIRTHDAY -> elementValue = new StringBuilder();
            case GENDER -> elementValue = new StringBuilder();
            case PHONE -> elementValue = new StringBuilder();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case FIRST_NAME:
                lastPatient().setFirst_name(elementValue.toString());
                break;
            case MIDDLE_NAME:
                lastPatient().setMiddle_name(elementValue.toString());
                break;
            case LAST_NAME:
                lastPatient().setLast_name(elementValue.toString());
                break;
            case BIRTHDAY:
                try {
                    Date birthdate = parseDate(elementValue.toString());
                    lastPatient().setBirthdate(birthdate);
                    lastPatient().setAge(getPatientAge(birthdate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case GENDER:
                lastPatient().setGender(elementValue.toString());
                break;
            case PHONE:
                lastPatient().setPhone(elementValue.toString());
                break;
        }
    }

    private Date parseDate(String stringDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(stringDate);
    }

    private Patient lastPatient() {
        List<Patient> articleList = allPatients.patientsList;
        return articleList.get(articleList.size() - 1);
    }

    public Patients getAllPatients() {
        return allPatients;
    }

    private Integer getPatientAge(Date birthdate) {
        LocalDate startDate = birthdate.toInstant().atZone(ZoneId.of("GMT+03:00")).toLocalDate();
        LocalDate endDate = LocalDate.now(ZoneId.of("GMT+03:00"));
        return Period.between(startDate, endDate).getYears();
    }
}
