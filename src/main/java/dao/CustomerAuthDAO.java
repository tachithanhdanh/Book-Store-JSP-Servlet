package dao;

import model.Customer;
import model.CustomerAuth;

public interface CustomerAuthDAO {
    int insert(CustomerAuth customerAuth);
    CustomerAuth selectBySelector(String selector);
    int update(CustomerAuth customerAuth);
    int deleteBySelector(String selector);
    int deleteAllTokens(Customer customer);
}
