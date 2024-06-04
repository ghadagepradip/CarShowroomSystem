package car_showroom_repository.org;

import java.util.Properties;

import car_showroom_config.org.DBHelper;
import car_showroom_config.org.DBPathHelper;
import car_showroom_model.org.CarIssue;
import java.io.*;


public class CarIssueRepository extends DBHelper {
	
	public boolean isAddIssue(CarIssue imodel) {
		try {
			String insertCarIssue=p.getProperty("insertCarIssue");
			pstmt = conn.prepareStatement(insertCarIssue);
			pstmt.setString(1, imodel.getIssuename());
			int value = pstmt.executeUpdate();
			if (value > 0) {
				return true;
			} else {
				System.out.println("Error while adding carmodel");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error in adding issue "+e);
			return false;
		}

	}

	public int getIssueId(String iname) {
		try {
			
			String selectIssueID = p.getProperty("selectIssueID");
			pstmt = conn.prepareStatement(selectIssueID);
			pstmt.setString(1, iname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				System.out.println("No issue found");
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Error fetching issue "+e);
			return 0;
		}

	}

	public boolean isShowAllIssue() {
		try {
			String selectAllCarIssue =p.getProperty("selectAllCarIssue");
			pstmt = conn.prepareStatement(selectAllCarIssue);
			rs = pstmt.executeQuery();
			System.out.println("Id\tIssue");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2));
			}
			return true;

		} catch (Exception e) {
			System.out.println("Error fetching issue "+e);
			return false;
		}
	}

}
