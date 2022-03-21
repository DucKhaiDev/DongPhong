package Dao.deploy;

import Connect.DBConnect;
import Entity.District;
import Services.deploy.ProvinceService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistrictDao implements Dao.DistrictDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public District getDistrict(int districtId) {
        conn = DBConnect.getConnection();
        District district = new District();

        try {
            ps = conn.prepareStatement("SELECT * FROM [DISTRICT] WHERE DST_ID = ?");
            ps.setInt(1, districtId);
            rs = ps.executeQuery();
            rs.next();
            district.setDistrictId(districtId);
            district.setDistrictName(rs.getString("DST_NAME"));
            district.setProvince(new ProvinceService().getProvince(rs.getInt("PVC_ID")));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return district;
    }

    @Override
    public List<District> getByProvince(int provinceId) {
        conn = DBConnect.getConnection();
        List<District> districts = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [DISTRICT] WHERE PVC_ID = ?");
            ps.setInt(1, provinceId);
            rs = ps.executeQuery();
            while (rs.next()) {
                District district = new District();
                district.setDistrictId(rs.getInt("DST_ID"));
                district.setDistrictName(rs.getString("DST_NAME"));
                district.setProvince(new ProvinceService().getProvince(rs.getInt("PVC_ID")));

                districts.add(district);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return districts;
    }
}
