package com.us.example.service;

import com.us.example.domain.Form;
import com.us.example.util.Constant;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WordService {
    @Autowired
    private Environment env;
    private static Configuration configuration=new Configuration();
    Logger logger= Logger.getLogger(WordService.class);

    static {
        configuration.setDefaultEncoding("UTF-8");
    }

    public void genIDCardWordFile(Form form) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException,Exception{
        logger.info("start genIDCardWordFile");
        Map<String,Object> map = new HashMap<String,Object>();
        SimpleDateFormat dateFormat=new SimpleDateFormat(Constant.DATE_FORMAT);
        map.put(Constant.Applicant_First_Name, form.getFirstname());
        map.put(Constant.Applicant_Last_Name, form.getLastname());
        map.put(Constant.Applicant_Date_Of_Birth,dateFormat.format(form.getDateofbirth()));
        map.put(Constant.Applicant_Place_Of_Birth, form.getPlaceofbirth());
        map.put(Constant.Father_First_And_Last_Name, form.getFathername());
        map.put(Constant.Mother_First_And_Last_Name, form.getMothername());
        map.put(Constant.Applicant_Citizenship, form.getCitizenship());
        map.put(Constant.Applicant_Occupation, form.getOccupation());
        map.put(Constant.Applicant_Address, form.getAddress());
        map.put(Constant.Applicant_Complexion, form.getComplexion());
        map.put(Constant.Applicant_Height,String.valueOf(form.getHeight()));
        map.put(Constant.Issue_Date,dateFormat.format(form.getIssuedate()));
        map.put(Constant.Consular_ID_Number,String.valueOf(form.getConsularid()));
        map.put(Constant.Timbre, form.getTimbre());
        map.put(Constant.Photo, form.getPhoto());
//        map.put(Constant.Applicant_Signature, form.getSignature().toString());
        String templatePath = "/usr/share/nginx/printtemplate/idcard.docx";
        InputStream is = new FileInputStream(templatePath);
        CustomXWPFDocument document= new CustomXWPFDocument(is);
        Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
        while (itPara.hasNext()) {
            XWPFParagraph paragraph = itPara.next();
            Set<String> set = map.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                List<XWPFRun> run=paragraph.getRuns();
                for(int i=0;i<run.size();i++)
                {
                    String str=run.get(i).getText(run.get(i).getTextPosition());
                    if(str!=null && str.contains(key))
                    {
                        run.get(i).setText(str.substring(0,str.indexOf(key))+map.get(key),0);
                    }
                }
            }
        }
        logger.info("replace genIDCardWordFile fields");
        Iterator<XWPFTable> itTable = document.getTablesIterator();
        while (itTable.hasNext()) {
            XWPFTable table = (XWPFTable) itTable.next();
            int rcount = table.getNumberOfRows();
            for (int i = 0; i < rcount; i++) {
                XWPFTableRow row = table.getRow(i);
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    for (Map.Entry<String, Object> e : map.entrySet()) {
                        if (cell.getText().equals(e.getKey())) {
                            XWPFParagraph p=null;
                            if(cell.getText().equals(Constant.Photo)||cell.getText().equals(Constant.Timbre)){
                                p=cell.getParagraph(cell.getCTTc().getPArray()[1]);
                            }else{
                                p=cell.getParagraph(cell.getCTTc().getPArray()[0]);
                            }
                            if(cell.getText().equals(Constant.Photo)){

                                p.removeRun(0);
//                                String blipId = document.addPictureData(null, Document.PICTURE_TYPE_JPEG);
//                                document.createPicture(blipId,document.getNextPicNameNumber(Document.PICTURE_TYPE_JPEG), 500, 500,p);
                                /*p.createRun().addPicture(new FileInputStream("/Users/terryshi/Downloads/pic/car.jpeg"), Document.PICTURE_TYPE_JPEG,"car.jpeg",1500,1500);*/
                            }else{
                                p.removeRun(0);
                                p.createRun().setText(e.getValue().toString());
                            }
                        }
                    }
                }
            }
        }
        FileOutputStream outStream = null;
        logger.info("generate id card file: idcard_"+form.getId()+".doc");
        outStream = new FileOutputStream("/usr/share/nginx/html/printdoc/idcard/idcard_"+form.getId()+".doc");
        document.write(outStream);
        outStream.close();
    }

    public void genVisaWordFile(Form form) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
        logger.info("start genVisaWordFile");
        Map<String,String> map = new HashMap<String,String>();
        SimpleDateFormat dateFormat=new SimpleDateFormat(Constant.DATE_FORMAT);
        map.put(Constant.LastName, form.getLastname());
        map.put(Constant.FirstName, form.getFirstname());
        map.put(Constant.MiddleName, form.getMiddlename());
        map.put(Constant.MaidenName, form.getMaindenname());
        map.put(Constant.DateofBirth, dateFormat.format(form.getDateofbirth()));
        map.put(Constant.PlaceofBirth, form.getPlaceofbirth());
        map.put(Constant.Nationality, "");
        map.put(Constant.FamilyStatus, form.getMaritalstatus()+"");
        map.put(Constant.Address_1, form.getAddress());
        map.put(Constant.TelephoneNumber, form.getTelephone()+"");
        map.put(Constant.Profession,form.getOccupation());

        String templatePath = "/usr/share/nginx/printtemplate/visa.docx";
        InputStream is = new FileInputStream(templatePath);
        XWPFDocument document= new XWPFDocument(is);
        Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
        while (itPara.hasNext()) {
            XWPFParagraph paragraph = itPara.next();
            Set<String> set = map.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                List<XWPFRun> run=paragraph.getRuns();
                for(int i=0;i<run.size();i++)
                {
                    String str=run.get(i).getText(run.get(i).getTextPosition());
                    if(str!=null && str.contains(key))
                    {
                        run.get(i).setText(str.substring(0,str.indexOf(key))+map.get(key),0);
                    }
                }
            }
        }
        logger.info("replace genVisaWordFile fields");
        Iterator<XWPFTable> itTable = document.getTablesIterator();
        while (itTable.hasNext()) {
            XWPFTable table = (XWPFTable) itTable.next();
            int rcount = table.getNumberOfRows();
            for (int i = 0; i < rcount; i++) {
                XWPFTableRow row = table.getRow(i);
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    for (Map.Entry<String, String> e : map.entrySet()) {
                        if (cell.getText().equals(e.getKey())) {
                            XWPFParagraph p=cell.getParagraph(cell.getCTTc().getPArray()[0]);
                            if(e.getKey().equals(Constant.MaidenName)||e.getKey().equals(Constant.DateofBirth)||e.getKey().equals(Constant.PlaceofBirth)||e.getKey().equals(Constant.Nationality)||e.getKey().equals(Constant.FamilyStatus)||e.getKey().equals(Constant.Address_1)||e.getKey().equals(Constant.TelephoneNumber)||e.getKey().equals(Constant.Profession)){
                                p=cell.getParagraph(cell.getCTTc().getPArray()[1]);

                            }
                            p.removeRun(0);
                            p.removeRun(0);
                            p.removeRun(0);

                            if(e.getKey().equals(Constant.FirstName)||e.getKey().equals(Constant.MaidenName)){
                                p.removeRun(0);
                            }
                            if(e.getKey().equals(Constant.TelephoneNumber)){
                                p.removeRun(0);
                                p.removeRun(0);
                            }
                            p.createRun().setText(e.getValue());
                        }
                    }
                }
            }
        }
        FileOutputStream outStream = null;
        outStream = new FileOutputStream("/usr/share/nginx/html/printdoc/visa/visa_"+form.getId()+".doc");
        logger.info("generate visa file: visa_"+form.getId()+".doc");
        document.write(outStream);
        outStream.close();
    }

    public void genVisaStickerWordFile(Form form) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
        logger.info("start genVisaStickerWordFile");
        Map<String,String> map = new HashMap<String,String>();
        SimpleDateFormat dateFormat=new SimpleDateFormat(Constant.DATE_FORMAT);
        map.put(Constant.VISA_NUMBER, form.getVisanumber()==null?"":form.getVisanumber().toString());
        map.put(Constant.VISA_TYPE, form.getType()+"");
        map.put(Constant.Start_Date_Of_Stay,dateFormat.format(form.getVisaissuedate()));
        map.put(Constant.Issue_Date,dateFormat.format(form.getIssuedate()));
        map.put(Constant.Applicant_First_Name, form.getFirstname());
        map.put(Constant.Applicant_Last_Name, form.getLastname());
        map.put(Constant.Applicant_Citizenship, form.getCitizenship());
        map.put(Constant.Purpose_Of_Journey, form.getPurposeofjourney());
        map.put(Constant.DURATION_OF_STAY, form.getDurationofstay());
        map.put(Constant.Auth_Entries, form.getAuthorizedentries()+"");
        map.put(Constant.End_Date_Of_Stay,dateFormat.format(form.getVisaexpirationdate()));
        map.put(Constant.Passport_Number, form.getPassportno());
        String templatePath = "/usr/share/nginx/printtemplate/visasticker.docx";
        InputStream is = new FileInputStream(templatePath);
        XWPFDocument document= new XWPFDocument(is);
        Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
        while (itPara.hasNext()) {
            XWPFParagraph paragraph = itPara.next();
            Set<String> set = map.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                List<XWPFRun> run=paragraph.getRuns();
                for(int i=0;i<run.size();i++)
                {
                    String str=run.get(i).getText(run.get(i).getTextPosition());
                    if(str!=null && str.contains(key))
                    {
                        run.get(i).setText(str.substring(0,str.indexOf(key))+map.get(key),0);
                    }
                }
            }
        }
        logger.info("replace genVisaStickerWordFile fields");
        Iterator<XWPFTable> itTable = document.getTablesIterator();
        while (itTable.hasNext()) {
            XWPFTable table = (XWPFTable) itTable.next();
            int rcount = table.getNumberOfRows();
            for (int i = 0; i < rcount; i++) {
                XWPFTableRow row = table.getRow(i);
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    for (Map.Entry<String, String> e : map.entrySet()) {
                        if (cell.getText().equals(e.getKey())) {
                            XWPFParagraph p=cell.getParagraph(cell.getCTTc().getPArray()[0]);
                            if(e.getKey().equals(Constant.VISA_NUMBER)){
                                p.removeRun(0);
                            }else{
                                p.removeRun(0);
                                p.removeRun(0);
                                p.removeRun(0);
                            }
                            if(e.getKey().equals(Constant.DURATION_OF_STAY)){
                                p.removeRun(0);
                            }
                            p.createRun().setText(e.getValue());
                        }
                    }
                }
            }
        }
        FileOutputStream outStream = null;
        outStream = new FileOutputStream("/usr/share/nginx/html/printdoc/visasticker/visasticker_"+form.getId()+".doc");
        logger.info("generate visasticker file: visasticker_"+form.getId()+".doc");
        document.write(outStream);
        outStream.close();
    }
}