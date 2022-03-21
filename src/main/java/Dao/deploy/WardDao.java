package Dao.deploy;

import Connect.DBConnect;
import Entity.Ward;
import Services.deploy.DistrictService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WardDao implements Dao.WardDao {
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public List<Ward> getByDistrict(int districtId) {
        Connection conn = DBConnect.getConnection();
        List<Ward> wards = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [WARD] WHERE DST_ID = ?");
            ps.setInt(1, districtId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ward ward = new Ward();
                ward.setWardId(rs.getInt("WRD_ID"));
                ward.setWardName(rs.getString("WRD_NAME"));
                ward.setDistrict(new DistrictService().getDistrict(rs.getInt("DST_ID")));

                wards.add(ward);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return wards;
    }
}
