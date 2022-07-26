package com.project.estorefront.repository;

import com.project.estorefront.model.User;

import java.sql.SQLException;

public interface IBuyerPersistence {

//	public void saveBuyer();
//	public void loadBuyer();
	User getBuyerByID(String buyerID) throws SQLException;
	boolean deactivateBuyerAccount(User buyer) throws SQLException;

	boolean updateBuyerAccount(User buyer) throws SQLException;

}
