package com.infor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infor.models.InforParking;

public interface SlotRepository extends JpaRepository<InforParking, Long> {
	final static String SELECT_UNASSIGNED_SLOTS = "select iu.userid,iu.firstname,iu.lastname,iu.contactnumber,iu.emailaddress,iu.inforaddress,iu.position,ip.parkingid,ip.isparkingtandem from InforParking ip left join InforUser iu on ip.userid = iu.userid where ip.userid=0";
	final static String SELECT_ALL_SLOTS = "select iu.userid,iu.firstname,iu.lastname,iu.contactnumber,iu.emailaddress,iu.inforaddress,iu.position,ip.parkingid,ip.isparkingtandem from InforParking ip left join InforUser iu on ip.userid = iu.userid";
	final static String SELECT_ASSIGNED_SLOTS = "select iu.userid,iu.firstname,iu.lastname,iu.contactnumber,iu.emailaddress,iu.inforaddress,iu.position,ip.parkingid,ip.isparkingtandem from InforParking ip inner join InforUser iu on ip.userid = iu.userid where ip.userid !=0";
	
	@Query(SELECT_UNASSIGNED_SLOTS)
	public List<Object[]> getUnassignedSlots();
	
	@Query(SELECT_ASSIGNED_SLOTS)
	public List<Object[]> getAssignedSlots();
	
	@Query(SELECT_ALL_SLOTS)
	public List<Object[]> getAllSlots();
}
