package com.infor.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infor.models.InforParking;
import com.infor.models.InforSlots;
import com.infor.models.InforUser;
import com.infor.service.SlotsService;
import com.infor.utils.ConverterUtils;

@Service
public class SlotsServiceImpl implements SlotsService{
	
	private final static String SELECT_ALL_SLOTS = "select iu.userid,iu.firstname,iu.lastname,iu.contactnumber,iu.emailaddress,iu.inforaddress,iu.position,ip.parkingid,ip.isparkingtandem from InforParking ip left join InforUser iu on ip.userid = iu.userid";
	private final static String SELECT_UNASSIGNED_SLOTS = "select iu.userid,iu.firstname,iu.lastname,iu.contactnumber,iu.emailaddress,iu.inforaddress,iu.position,ip.parkingid,ip.isparkingtandem from InforParking ip left join InforUser iu on ip.userid = iu.userid where ip.userid=0";
	private final static String SELECT_ASSIGNED_SLOTS = "select iu.userid,iu.firstname,iu.lastname,iu.contactnumber,iu.emailaddress,iu.inforaddress,iu.position,ip.parkingid,ip.isparkingtandem from InforParking ip inner join InforUser iu on ip.userid = iu.userid where ip.userid !=0";
	
	@Autowired
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<InforSlots> getUnassignedSlots() {
		// TODO Auto-generated method stub
		return slotGenerator(em.createNativeQuery(SELECT_UNASSIGNED_SLOTS).getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InforSlots> getAssignedSlots() {
		// TODO Auto-generated method stub
		return slotGenerator(em.createNativeQuery(SELECT_ASSIGNED_SLOTS).getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InforSlots> getAllSlots() {
		// TODO Auto-generated method stub
		return slotGenerator(em.createNativeQuery(SELECT_ALL_SLOTS).getResultList());
	}
	
	private List<InforSlots> slotGenerator(List<Object[]> plainObj){
		List<InforSlots> is = new ArrayList<InforSlots>();
		for(Object[] obj: plainObj){
			InforUser inforUser = new InforUser();
			InforParking inforParking = new InforParking();
			InforSlots inforSlots = new InforSlots();
			inforUser.setUserid(ConverterUtils.convertToInteger(obj[0]));
			inforUser.setFirstname(ConverterUtils.convertToString(obj[1]));
			inforUser.setLastname(ConverterUtils.convertToString(obj[2]));
			inforUser.setContactnumber(ConverterUtils.convertToString(obj[3]));
			inforUser.setEmailaddress(ConverterUtils.convertToString(obj[4]));
			inforUser.setInforaddress(ConverterUtils.convertToString(obj[5]));
			inforUser.setPosition(ConverterUtils.convertToString(obj[6]));
			
			inforParking.setParkingid(ConverterUtils.convertToString(obj[7]));
			inforParking.setIsparkingtandem(ConverterUtils.convertToString(obj[8]));

			inforSlots.setInforParking(inforParking);
			inforSlots.setInforUser(inforUser);
			is.add(inforSlots);
		}	
		return is;
	}

}
