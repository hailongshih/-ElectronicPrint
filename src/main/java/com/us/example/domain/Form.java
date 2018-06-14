package com.us.example.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
@SuppressWarnings("serial")
@Entity
@Table(name = "form")
public class Form {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    private Date dateofbirth;
    private String placeofbirth;
    private String fathername;
    private String mothername;
    private String citizenship;
    private String occupation;
    private String address;
    private String complexion;
    private Double height;
    private Date issuedate;
    private int consularid;
    private String timbre;
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="photo", columnDefinition="BLOB",length=16777215)
    private byte[] photo;
    private Boolean signature;
    private int userid;
    private int status;
    private Date applydate;
    private int type;
    private String maindenname;
    private String middlename;
    private Integer maritalstatus;
    private BigInteger telephone;
    private String durationperstay;
    private Integer authorizedentries;
    private String nameandaddress;
    private String durationofstay;
    private String passportno;
    private String initiainterviewref;
    private String chiefofstationobservation;
    private String ipaddress;
    private String entrydateexitdateaddress;
    private Integer numberofentries;
    private Integer numberoftravelers;
    private Date passportexpirationdate;
    private String passportissuedby;
    private String pointoftransit;
    private String schooloracademicsponsor;
    private Date visaexpirationdate;
    private String tavellingwith;
    private BigInteger visanumber;
    private String email;
    private String nearestembassy;
    private Boolean istravelalone;
    private String purposeofjourney;
    private Date visaissuedate;

    public Form(){}

    public Form(String firstname, String lastname, Date dateofbirth, String placeofbirth, String fathername, String mothername, String citizenship, String occupation, String address, String complexion, Double height, Date issuedate, int consularid, String timbre, byte[] photo, Boolean signature, int userid, int status, Date applydate, int type, String maindenname, String middlename, Integer maritalstatus, BigInteger telephone, String durationperstay, Integer authorizedentries, String nameandaddress, String durationofstay, String passportno, String initiainterviewref, String chiefofstationobservation, String ipaddress, String entrydateexitdateaddress, Integer numberofentries, Integer numberoftravelers, Date passportexpirationdate, String passportissuedby, String pointoftransit, String schooloracademicsponsor, Date visaexpirationdate, String tavellingwith, BigInteger visanumber,String email,String nearestembassy,Boolean istravelalone,String purposeofjourney,Date visaissuedate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
        this.placeofbirth = placeofbirth;
        this.fathername = fathername;
        this.mothername = mothername;
        this.citizenship = citizenship;
        this.occupation = occupation;
        this.address = address;
        this.complexion = complexion;
        this.height = height;
        this.issuedate = issuedate;
        this.consularid = consularid;
        this.timbre = timbre;
        this.photo = photo;
        this.signature = signature;
        this.userid = userid;
        this.status = status;
        this.applydate = applydate;
        this.type = type;
        this.maindenname = maindenname;
        this.middlename = middlename;
        this.maritalstatus = maritalstatus;
        this.telephone = telephone;
        this.durationperstay = durationperstay;
        this.authorizedentries = authorizedentries;
        this.nameandaddress = nameandaddress;
        this.durationofstay = durationofstay;
        this.passportno = passportno;
        this.initiainterviewref = initiainterviewref;
        this.chiefofstationobservation = chiefofstationobservation;
        this.ipaddress = ipaddress;
        this.entrydateexitdateaddress = entrydateexitdateaddress;
        this.numberofentries = numberofentries;
        this.numberoftravelers = numberoftravelers;
        this.passportexpirationdate = passportexpirationdate;
        this.passportissuedby = passportissuedby;
        this.pointoftransit = pointoftransit;
        this.schooloracademicsponsor = schooloracademicsponsor;
        this.visaexpirationdate = visaexpirationdate;
        this.tavellingwith = tavellingwith;
        this.visanumber = visanumber;
        this.email=email;
        this.nearestembassy=nearestembassy;
        this.istravelalone=istravelalone;
        this.purposeofjourney=purposeofjourney;
        this.visaissuedate=visaissuedate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getPlaceofbirth() {
        return placeofbirth;
    }

    public void setPlaceofbirth(String placeofbirth) {
        this.placeofbirth = placeofbirth;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComplexion() {
        return complexion;
    }

    public void setComplexion(String complexion) {
        this.complexion = complexion;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
    }

    public int getConsularid() {
        return consularid;
    }

    public void setConsularid(int consularid) {
        this.consularid = consularid;
    }

    public String getTimbre() {
        return timbre;
    }

    public void setTimbre(String timbre) {
        this.timbre = timbre;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getApplydate() {
        return applydate;
    }

    public void setApplydate(Date applydate) {
        this.applydate = applydate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMaindenname() {
        return maindenname;
    }

    public void setMaindenname(String maindenname) {
        this.maindenname = maindenname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Integer getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(Integer maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public BigInteger getTelephone() {
        return telephone;
    }

    public void setTelephone(BigInteger telephone) {
        this.telephone = telephone;
    }

    public String getDurationperstay() {
        return durationperstay;
    }

    public void setDurationperstay(String durationperstay) {
        this.durationperstay = durationperstay;
    }

    public Integer getAuthorizedentries() {
        return authorizedentries;
    }

    public void setAuthorizedentries(Integer authorizedentries) {
        this.authorizedentries = authorizedentries;
    }

    public String getNameandaddress() {
        return nameandaddress;
    }

    public void setNameandaddress(String nameandaddress) {
        this.nameandaddress = nameandaddress;
    }

    public String getDurationofstay() {
        return durationofstay;
    }

    public void setDurationofstay(String durationofstay) {
        this.durationofstay = durationofstay;
    }

    public String getPassportno() {
        return passportno;
    }

    public void setPassportno(String passportno) {
        this.passportno = passportno;
    }

    public String getInitiainterviewref() {
        return initiainterviewref;
    }

    public void setInitiainterviewref(String initiainterviewref) {
        this.initiainterviewref = initiainterviewref;
    }

    public String getChiefofstationobservation() {
        return chiefofstationobservation;
    }

    public void setChiefofstationobservation(String chiefofstationobservation) {
        this.chiefofstationobservation = chiefofstationobservation;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getEntrydateexitdateaddress() {
        return entrydateexitdateaddress;
    }

    public void setEntrydateexitdateaddress(String entrydateexitdateaddress) {
        this.entrydateexitdateaddress = entrydateexitdateaddress;
    }

    public Integer getNumberofentries() {
        return numberofentries;
    }

    public void setNumberofentries(Integer numberofentries) {
        this.numberofentries = numberofentries;
    }

    public Integer getNumberoftravelers() {
        return numberoftravelers;
    }

    public void setNumberoftravelers(Integer numberoftravelers) {
        this.numberoftravelers = numberoftravelers;
    }

    public Date getPassportexpirationdate() {
        return passportexpirationdate;
    }

    public void setPassportexpirationdate(Date passportexpirationdate) {
        this.passportexpirationdate = passportexpirationdate;
    }

    public String getPassportissuedby() {
        return passportissuedby;
    }

    public void setPassportissuedby(String passportissuedby) {
        this.passportissuedby = passportissuedby;
    }

    public String getPointoftransit() {
        return pointoftransit;
    }

    public void setPointoftransit(String pointoftransit) {
        this.pointoftransit = pointoftransit;
    }

    public String getSchooloracademicsponsor() {
        return schooloracademicsponsor;
    }

    public void setSchooloracademicsponsor(String schooloracademicsponsor) {
        this.schooloracademicsponsor = schooloracademicsponsor;
    }

    public Date getVisaexpirationdate() {
        return visaexpirationdate;
    }

    public void setVisaexpirationdate(Date visaexpirationdate) {
        this.visaexpirationdate = visaexpirationdate;
    }

    public String getTavellingwith() {
        return tavellingwith;
    }

    public void setTavellingwith(String tavellingwith) {
        this.tavellingwith = tavellingwith;
    }

    public BigInteger getVisanumber() {
        return visanumber;
    }

    public void setVisanumber(BigInteger visanumber) {
        this.visanumber = visanumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNearestembassy() {
        return nearestembassy;
    }

    public void setNearestembassy(String nearestembassy) {
        this.nearestembassy = nearestembassy;
    }

    public void setIstravelalone(boolean istravelalone) {
        this.istravelalone = istravelalone;
    }

    public String getPurposeofjourney() {
        return purposeofjourney;
    }

    public void setPurposeofjourney(String purposeofjourney) {
        this.purposeofjourney = purposeofjourney;
    }

    public Date getVisaissuedate() {
        return visaissuedate;
    }

    public void setVisaissuedate(Date visaissuedate) {
        this.visaissuedate = visaissuedate;
    }

    public Boolean getSignature() {
        return signature;
    }

    public void setSignature(Boolean signature) {
        this.signature = signature;
    }

    public Boolean getIstravelalone() {
        return istravelalone;
    }

    public void setIstravelalone(Boolean istravelalone) {
        this.istravelalone = istravelalone;
    }
}
