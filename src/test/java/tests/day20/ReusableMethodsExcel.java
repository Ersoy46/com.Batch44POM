package tests.day20;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReusableMethodsExcel {


    //bir method oluşturalım
    //dosya yolu,sayfa ismi  ve satir ,hücre index'ini verince hcre bilgisini döndürsün

    @Test
    public static Cell hucreGetir(String path,String sayfaIsmi, int satirIndex, int hucreIndex) {
        Cell cell=null;

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            cell=workbook.getSheet(sayfaIsmi).getRow(satirIndex).getCell(hucreIndex);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;

    }


    public static Map <String, String > mapOlustur(String path, String sayfaAdi) {

        Map <String, String > excelMap=new HashMap<>();

        //ilk adım excel'e ulaşmak
        Workbook workbook=null;
        try {
            FileInputStream fis=new FileInputStream(path);
            workbook=WorkbookFactory.create(fis);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int satirSayisi=workbook.getSheet(sayfaAdi).getLastRowNum();
        String Key="";
        String Value="";

        for (int i=0 ; i<=satirSayisi ; i++){

           Key=workbook.getSheet(sayfaAdi).getRow(i).getCell(0).toString();
           Value=workbook.getSheet(sayfaAdi).getRow(i).getCell(1).toString() +
           ", " +workbook.getSheet(sayfaAdi).getRow(i).getCell(2).toString() +
           ", "+ workbook.getSheet(sayfaAdi).getRow(i).getCell(2).toString();
           excelMap.put(Key,Value);
        }

        return excelMap;

    }
}
