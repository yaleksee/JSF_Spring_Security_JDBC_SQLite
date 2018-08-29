package com;

import com.db.operation.DatabaseOperation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;

@Component
@ManagedBean(name = "priceBean", eager = true)
@RequestScoped
public class PriceBean implements Serializable {

    private int id;
    private String name;
    private String value;
    private static final long serialVersionUID = 1L;


    public ArrayList<PriceBean> pricesListFromDB;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    DatabaseOperation databaseOperation = new DatabaseOperation();

    /**
     * отложенная инициализация
     */
    @PostConstruct
    public void init() {
        pricesListFromDB = databaseOperation.getpricesListFromDB();
    }

    /* Метод, используемый для извлечения всех записей из базы данных */
    public ArrayList<PriceBean> pricesList() {
        return pricesListFromDB;
    }

    /* Метод, используемый для cохранения */
    public String savePriceDetails(PriceBean newPriceObj) {
        return databaseOperation.savePriceDetailsInDB(newPriceObj);
    }

    /* Метод, используемый для изменения */
    public String editPriceRecord(int PriceId) {
        return databaseOperation.editPriceRecordInDB(PriceId);
    }

    /* Метод, используемый для обновления */
    public String updatePriceDetails(PriceBean updatePriceObj) {
        return databaseOperation.updatePriceDetailsInDB(updatePriceObj);
    }

    /* Метод, используемый для удаления */
    public String deletePriceRecord(int PriceId) {
        return databaseOperation.deletePriceRecordInDB(PriceId);
    }

}