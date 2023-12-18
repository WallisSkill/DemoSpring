package com.example.demo.Repository.Imple;

import com.example.demo.Model.Account;
import com.example.demo.Repository.AccountRepo;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepo {

    private final DataSource dataSource;

    public AccountRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "Select * from Account";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setAccountID(rs.getInt("AccountID"));
                account.setMail(rs.getString("Mail"));
                account.setPassword(rs.getString("Password"));
                account.setRole(rs.getInt("Role"));
                account.setIsActive(rs.getBoolean("IsActive"));
                list.add(account);
            }

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return list;
        }
    }
    @Override
    public int addAccount(Account account) {
        String sql = "INSERT INTO Account(Mail, Password, Role, IsActive) VALUES(?,?,?,?)";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            if(FindByMail(account.getMail())!=null){
                throw new Exception();
            }
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, account.getMail());
            ps.setString(2, account.getPassword());
            ps.setInt(3, account.getRole());
            ps.setBoolean(4, false);
            return ps.executeUpdate();
        }  catch (Exception e) {
            return 0;
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    public int deleteAccount(int id) {
        String sql = "DELETE FROM Account WHERE AccountID = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public Account FindById(int id){
        String sql = "SELECT * FROM Account WHERE AccountID = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setAccountID(rs.getInt("AccountID"));
                account.setMail(rs.getString("Mail"));
                account.setPassword(rs.getString("Password"));
                account.setRole(rs.getInt("Role"));
                account.setIsActive(rs.getBoolean("IsActive"));
                return account;
            }

        } catch (Exception e) {
            return null;
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();

                }
                if (connection != null) {
                    connection.close();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public Account FindByMail(String mail){
        String sql = "SELECT * FROM Account WHERE Mail = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, mail);
            rs = ps.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setAccountID(rs.getInt("AccountID"));
                account.setMail(rs.getString("Mail"));
                account.setPassword(rs.getString("Password"));
                account.setRole(rs.getInt("Role"));
                account.setIsActive(rs.getBoolean("IsActive"));
                return account;
            }

        } catch (Exception e) {
            return null;
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();

                }
                if (connection != null) {
                    connection.close();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int updateAccount(Account account) {
        String sql = "UPDATE Account SET Password = ?, Role = ? WHERE Mail = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, account.getPassword());
            ps.setInt(2, account.getRole());
            ps.setString(3, account.getMail());
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public int updateAccountStauts(int id,boolean status){
        String sql = "UPDATE Account SET IsActive = ? WHERE AccountID = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
