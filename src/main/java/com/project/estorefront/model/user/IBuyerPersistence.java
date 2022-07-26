package com.project.estorefront.model.user;

import com.project.estorefront.model.user.User;

import java.sql.SQLException;

public interface IBuyerPersistence {

//	public void saveBuyer();
//	public void loadBuyer();
	User getBuyerByID(String buyerID) throws SQLException;
	boolean deactivateBuyerAccount(User buyer) throws SQLException;

	boolean updateBuyerAccount(User buyer) throws SQLException;

}
