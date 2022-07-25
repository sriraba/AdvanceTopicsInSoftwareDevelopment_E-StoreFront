package com.project.estorefront.repository;

import com.project.estorefront.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBuyerPersistence {

//	public void saveBuyer();
//	public void loadBuyer();
	User getBuyerByID(String buyerID);
	boolean deactivateBuyerAccount(User buyer);

	boolean updateBuyerAccount(User buyer);

}
