package com.db.operation;

import com.PriceBean;

import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class DatabaseOperation {

    public static Statement stmtObj;
    public static Connection conn;
    public static ResultSet resultSetObj;

    /* Соединение с БД */
    private static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:users.db";
            conn = DriverManager.getConnection(url);
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return conn;
    }

    /* Получаем список всех цен */
    public ArrayList<PriceBean> getpricesListFromDB() {
        ArrayList<PriceBean> pricesList = new ArrayList<PriceBean>();
        String sql = "select * from prices_list";
        try (Connection connection = connect()) {
            stmtObj = connection.createStatement();
            resultSetObj = stmtObj.executeQuery(sql);
            while (resultSetObj.next()) {
                PriceBean stuObj = new PriceBean();
                stuObj.setId(resultSetObj.getInt("prices_id"));
                stuObj.setName(resultSetObj.getString("prices_name"));
                stuObj.setValue(resultSetObj.getString("prices_value"));
                pricesList.add(stuObj);
            }

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return pricesList;
    }

    /* Метод, используемый для сохранения нового значения */
    public String savePriceDetailsInDB(PriceBean newPriceObj) {
        int saveResult = 0;
        String navigationResult = "";
        String sql = "insert into prices_list (prices_name, prices_value) values (?, ?)";
        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newPriceObj.getName());
            pstmt.setString(2, newPriceObj.getValue());
            saveResult = pstmt.executeUpdate();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        if (saveResult != 0) {
            navigationResult = "pricesList.xhtml?faces-redirect=true";
        } else {
            navigationResult = "createPrice.xhtml?faces-redirect=true";
        }
        return navigationResult;
    }

    /* Метод, используемый для изменения выбранного значения */
    public String editPriceRecordInDB(int PriceId) {
        PriceBean editRecord = null;
        String navigationResult = "";
        Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String sql = "select * from prices_list where prices_id = " + PriceId;
        try (Connection connection = connect()) {
            stmtObj = connection.createStatement();
            resultSetObj = stmtObj.executeQuery(sql);
            if (resultSetObj != null) {
                resultSetObj.next();
                editRecord = new PriceBean();
                editRecord.setId(resultSetObj.getInt("prices_id"));
                editRecord.setName(resultSetObj.getString("prices_name"));
                editRecord.setValue(resultSetObj.getString("prices_value"));
            }
            sessionMapObj.put("editRecordObj", editRecord);
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        navigationResult = "editPrice.xhtml?faces-redirect=true";
        return navigationResult;
    }

    /* Метод, используемый для обновления */
    public String updatePriceDetailsInDB(PriceBean updatePriceObj) {
        String navigationResult = "";
        String sql = "update prices_list set prices_name=?, prices_value=? where prices_id=?";
        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, updatePriceObj.getName());
            pstmt.setString(2, updatePriceObj.getValue());
            pstmt.setInt(3, updatePriceObj.getId());
            pstmt.executeUpdate();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        navigationResult = "pricesList.xhtml?faces-redirect=true";
        return navigationResult;
    }

    /* Метод, используемый для удаления позиции прайса */
    public String deletePriceRecordInDB(int PriceId) {
        String navigationResult = "";
        String sql = "delete from prices_list where prices_id = " + PriceId;
        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        navigationResult = "pricesList.xhtml?faces-redirect=true";
        return navigationResult;
    }
}