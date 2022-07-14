package com.project.estorefront.model;

import com.project.estorefront.repository.ISellerPersistence;

import java.util.ArrayList;

public interface ISeller {

    ArrayList<User> getAllSellers(ISellerPersistence persistence, String city);

}
