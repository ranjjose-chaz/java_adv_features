package org.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        //To maintain the list of rows fetched from the Excel file in the Object format
        List<WOP> dataList = new ArrayList<>();

        //Load the Excel file
        Workbook workbook = WorkbookFactory.create(
                new FileInputStream("C:/Users/chazr/Downloads/July 21 CLE - Swiss Re.xlsx"));

        Sheet wopSheet = workbook.getSheet("WOP");
        extractRowData(180, 344, wopSheet, dataList, true);
        System.out.println("\n\t2nd Set");
        extractRowData(358, 391, wopSheet, dataList, false);

    }

    //This method iterates through each row in the Excel file within the required range and creates am Object per row
    // and adds to a list
    private static void extractRowData(
            int startRow, int endRow, Sheet wopSheet, List<WOP> dataList,
            boolean considerPaymentsCommenced) {

        for(int i = startRow; i< endRow; ++i) {
            Row row = wopSheet.getRow(i);

            WOP wop = new WOP(
                    row.getCell(1).getStringCellValue(),
                    row.getCell(2).getStringCellValue(),
                    row.getCell(3).getStringCellValue(),
                    row.getCell(4).getDateCellValue(),
                    row.getCell(5).getDateCellValue(),
                    row.getCell(6).getStringCellValue(),
                    row.getCell(7).getNumericCellValue(),
                    row.getCell(8).getNumericCellValue(),
                    row.getCell(9).getStringCellValue(),
                    getStringCellValue(row, i+1),
                    considerPaymentsCommenced ? row.getCell(19).getDateCellValue():null,
                    considerPaymentsCommenced
            );
            System.out.println(wop);
            dataList.add(wop);

        }
    }

    // record is a new `type` available in Java (from Java14 version). record is an easier way to define a class.
    // If you use the keyword record instead of class, it automatically creates constructor, getters
    // and implement a bunch of overridden methods from Object class like (toString, equals, hashcode, etc.).
    // So here I just create a class called WOP where I have instance variables representing various columns.
    // I will use this class to hold data from the Excel file and will later on write it into a fixed length file.
    record WOP(String planId, String clientName, String forename, Date dob, Date dateNotified,
               String product, Double monthlyAmount, Double reassurerShare, String typeOfClaim,
               String indexationDue, Date paymentsCommencedWEF, boolean considerPaymentsCommenced){


        //Overriding this method to do the necessary steps to format the contents as per the fixed length file
        @Override
        public String toString() {
            return
                    String.format("%-8s", planId)
                            + String.format("%-40s", clientName)
                            + String.format("%-40s", forename)
                            + new SimpleDateFormat("dd/MM/yyyy").format(dob)
                            + new SimpleDateFormat("dd/MM/yyyy").format(dateNotified)
                            + String.format("%-12s", product)
                            + String.format("%.2f", monthlyAmount)
                            + String.format("%.2f", reassurerShare)
                            + String.format("%-12s", typeOfClaim)
                            + String.format("%-20s", indexationDue == null? "": indexationDue)
                            + (considerPaymentsCommenced ? new SimpleDateFormat("dd/MM/yyyy").format(paymentsCommencedWEF): "")
                            + ";";

        }

    }

    private static String getStringCellValue(Row row, int rowNum) {
        try {
            return row.getCell(10).getStringCellValue();
        }catch (Exception e){
            System.out.println("processing row# "+ rowNum + " Error Details: " + e.getMessage());
        }
        return null;
    }
}
