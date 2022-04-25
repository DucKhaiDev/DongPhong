package Dao.deploy;

import Connect.DBConnect;
import Dao.IProvinceDao;
import Entity.Province;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDao implements IProvinceDao {
    @Override
    public Province getProvince(int id) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Province province = new Province();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PROVINCE] WHERE PVC_ID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                province.setProvinceId(id);
                province.setProvinceName(rs.getString("PVC_NAME"));
                province.setLatitude(rs.getDouble("LAT"));
                province.setLongitude(rs.getDouble("LONG"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return province;
    }

    @Override
    public List<Province> getAll() {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Province> provinces = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PROVINCE]");
            rs = ps.executeQuery();
            while (rs.next()) {
                Province province = new Province();
                province.setProvinceId(rs.getInt("PVC_ID"));
                province.setProvinceName(rs.getString("PVC_NAME"));
                province.setLatitude(rs.getDouble("LAT"));
                province.setLongitude(rs.getDouble("LONG"));
                provinces.add(province);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return provinces;
    }
}