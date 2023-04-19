package com.narvee.usit.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.narvee.usit.entity.City;
import com.narvee.usit.entity.ExceRec;
import com.narvee.usit.entity.Recruiter;
import com.narvee.usit.entity.States;
import com.narvee.usit.entity.Users;
import com.narvee.usit.entity.VendorDetails;
import com.narvee.usit.helper.Helper;
import com.narvee.usit.helper.ListRecruiter;
import com.narvee.usit.repository.IExcelRepository;
import com.narvee.usit.repository.IRecruiterRepository;
import com.narvee.usit.repository.IStatesRepository;
import com.narvee.usit.repository.IVendorRepository;
import com.narvee.usit.service.IRecruiterService;
import com.narvee.usit.service.IVendorService;

@Service
@Transactional
public class RecruiterServiceimpl implements IRecruiterService {
	public static final Logger logger = LoggerFactory.getLogger(VmsServiceImpl.class);

	@Autowired
	IRecruiterRepository repo;

	@Autowired
	IVendorRepository vendorrepo;

	@Autowired
	IStatesRepository staterepo;

	@Autowired
	IExcelRepository execrepo;

	@Override
	public Recruiter save(Recruiter vms) {
		logger.info("RecruiterServiceimpl.save()");
		return repo.saveAndFlush(vms); // repo.save(vms);
	}

	@Override
	public List<Recruiter> getall() {
		logger.info("RecruiterServiceimpl.getall()");
		return repo.findAll();// repo.getall();
	}

	@Override
	public boolean deleteById(long id) {
		logger.info("VmsServiceImpl.deleteById()");
		repo.deleteById(id);
		return true;
	}

	@Override
	public Recruiter getbyId(long id) {
		logger.info("RecruiterServiceimpl.getbyId()");
		return repo.findById(id).get();
	}

	@Override
	public List<Recruiter> recruiterinfobyVmsid(long id) {
		logger.info("RecruiterServiceimpl.recruiterinfobyVmsid()");
		return repo.findByVendorVmsid(id);
	}

	@Override
	public List<Recruiter> dupvendor(String email) {
		// TODO Auto-generated method stub
		return repo.findByEmail(email);
	}

	@Override
	public int changeStatus(String status, Long id, String remarks) {
		logger.info("VmsServiceImpl.changeStatus()");
		return repo.toggleStatus(status, id, remarks);
	}

	@Override
	public int approve(String stat, Long id, Long role) {
		logger.info("VmsServiceImpl.saveVms()");
		int save = repo.approveStatus(stat, id, role);
		if (save != 0)
			return 1;
		else
			return 0;
	}

	@Override
	public void saveexcel(MultipartFile file) {
		try {
			List<Recruiter> listOfexcel = Helper.convertExcelToListOfRecruiter(file.getInputStream());
			System.out.println(listOfexcel);
			saveintoDB(listOfexcel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveintoDB(List<Recruiter> listOfexcel) {
		VendorDetails vendor = null;
		Long vmsid = null;
		States s = null;
		Long stateid = null;
		Users user = new Users();
		for (ListIterator<Recruiter> it = listOfexcel.listIterator(); it.hasNext();) {
			Recruiter value = it.next();
			s = new States();
			System.out.println("kkkk  " + value.getVendor().getCompany());
			vendor = new VendorDetails();
			if (value.getVendor() != null) {
				VendorDetails vv = vendorrepo.findByCompany(value.getVendor().getCompany());
				if (vv == null) {
					vendor.setCompany(value.getVendor().getCompany());
					// cityid = cityrepo.save(c).getId();
					System.out.println(" company === " + value.getVendor().getCompany());

				} else {
					vmsid = vv.getVmsid();
					vendor.setVmsid(vmsid);
					value.setVendor(vendor);
				}
			}
			// vendor company close

			// states
			if (value.getStates() != null) {
				States state = staterepo.findByStatename(value.getStates().getStatename());
				if (state == null) {
					s.setStatename(value.getStates().getStatename());
					// stateid = staterepo.save(s).getId();
				} else {
					s.setId(state.getId());
					stateid = state.getId();
					value.setStates(s);
				}
			}
			// states close

			user.setUserid(1);
			value.setUser(user);

			try {
				List<Recruiter> findbymail = repo.findByEmail(value.getEmail());
				if (findbymail == null || findbymail.isEmpty()) {
				} else {
					it.remove();
				}
			} catch (NullPointerException e) {
			}

		} // main forloop

		this.repo.saveAll(listOfexcel);

	}

	@Override
	public ExceRec save(ExceRec vms) {
		// TODO Auto-generated method stub
		return execrepo.saveAndFlush(vms);
	}

	@Override
	public List<ExceRec> duprecru(String email) {
		// TODO Auto-generated method stub
		return execrepo.findByEmail(email);
	}

	@Override
	public List<ListRecruiter> getallrecruiter() {
		return repo.getallrecruiter();
	}

}
