package dao;

import model.TokenForgetPassword;

public interface TokenForgetPasswordDAO {
    boolean insertToken(TokenForgetPassword tokenForgetPassword);

    void updateStatus(TokenForgetPassword tokenForgetPassword);

    TokenForgetPassword getToken(String token);
}
