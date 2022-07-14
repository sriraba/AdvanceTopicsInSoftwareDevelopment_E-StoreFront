package com.project.estorefront.repository;

import java.sql.SQLException;

public interface ISellerPersistence {

	public void updateProfile();
	public String deleteSellerAccount() throws SQLException;
	
}
