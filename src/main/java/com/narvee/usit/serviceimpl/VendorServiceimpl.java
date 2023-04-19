package com.narvee.usit.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.narvee.usit.entity.City;
import com.narvee.usit.entity.GetVendor;
import com.narvee.usit.entity.PinCode;
import com.narvee.usit.entity.States;
import com.narvee.usit.entity.Users;
import com.narvee.usit.entity.VendorDetails;
import com.narvee.usit.entity.Vms;
import com.narvee.usit.helper.Helper;
import com.narvee.usit.helper.ListVendor;
import com.narvee.usit.repository.ICityRepository;
import com.narvee.usit.repository.IPincodeRepository;
import com.narvee.usit.repository.IStatesRepository;
import com.narvee.usit.repository.IVendorRepository;
import com.narvee.usit.repository.IVmsRepository;
import com.narvee.usit.service.IVendorService;

@Service
@Transactional
public class VendorServiceimpl implements IVendorService {
	public static final Logger logger = LoggerFactory.getLogger(VmsServiceImpl.class);

	@Autowired
	IVendorRepository repo;
	@Autowired
	ICityRepository cityrepo;

	@Autowired
	IStatesRepository staterepo;

	@Autowired
	IPincodeRepository pincoderepo;

	@Autowired
	IVmsRepository vmsrepo;

	@Override
	public VendorDetails save(VendorDetails vms) {
		return repo.save(vms);
	}

	@Override
	public Vms save(Vms vms) {
		return vmsrepo.save(vms);
	}

	@Override
	public List<VendorDetails> getall() {
		return repo.findAll();
	}

	@Override
	public boolean deleteById(long id) {
		logger.info("VmsServiceImpl.deleteById()");
		repo.deleteById(id);
		return true;
	}

	@Override
	public VendorDetails getbyId(long id) {
		return repo.findById(id).get();
	}

	@Override
	public List<GetVendor> getvendordetails() {
		return repo.listvendor();
	}

	@Override
	public int approve(String stat, Long id, Long role) {
		logger.info("VmsServiceImpl.saveVms()");
		// System.out.println(" ================= ");
		int save = repo.approveStatus(stat, id, role);
		if (save != 0)
			return 1;
		else
			return 0;
	}

	@Override
	public int changeStatus(String status, Long id, String remarks) {
		logger.info("VmsServiceImpl.changeStatus()");
		return repo.toggleStatus(status, id, remarks);
	}

	@Override
	public Page<VendorDetails> findPaginated(int pageNo, int pageSize) {
		logger.info("VmsServiceImpl.findPaginated()");
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<VendorDetails> findAll = repo.findAll(pageable);
		return findAll;
	}

	@Override
	public void saveexcel(MultipartFile file) {
		try {
			List<VendorDetails> products = Helper.convertExcelToListOfVendor(file.getInputStream());
			System.out.println(products);
			saveMail(products);

			// this.productRepo.saveAll(products);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveMail(List<VendorDetails> products) {
		// System.out.println(products);
		Users u = new Users();
		City c = null;
		States s = null;
		PinCode p = null;
		Long cityid = null;
		Long stateid = null;
		// List<VendorDetails> newvendor = new ArrayList();
		for (ListIterator<VendorDetails> it = products.listIterator(); it.hasNext();) {
			c = new City();
			s = new States();
			p = new PinCode();
			VendorDetails value = it.next();

			System.out.println("kkkk  " + value.getPincode());

			if (value.getCity() != null) {
				City city = cityrepo.findByCityname(value.getCity().getCityname());

				if (city == null) {
					c.setCityname(value.getCity().getCityname());
					// cityid = cityrepo.save(c).getId();
					System.out.println(" City === " + value.getCity().getCityname());

				} else {
					cityid = city.getId();
					c.setId(city.getId());
					value.setCity(c);
				}
			}

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

			if (value.getPincode() != null) {
				PinCode pincode = pincoderepo.findByPincode(value.getPincode().getPincode());
				System.out.println(" pincode +++++" + value.getPincode().getPincode());
				if (pincode == null) {
					p.setPincode(value.getPincode().getPincode());
					// pincoderepo.save(p);
				} else {
					p.setId(pincode.getId());
					value.setPincode(pincode);
				}
			}

			u.setUserid(1);
			value.setUser(u);
			try {
				List<VendorDetails> findBySubject = repo.findByCompanyAndCityIdAndStatesIdAndFedid(value.getCompany(),
						cityid, stateid, value.getFedid());
				if (findBySubject == null || findBySubject.isEmpty()) {
					// System.out.println("lllllllllllll " +value.getCompany()+" ==== "+ cityid + "
					// === " + stateid+" == "+value.getFedid());

				} else {
					it.remove();
				}
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
		}

		this.repo.saveAll(products);
	}

	@Override
	public List<ListVendor> getvendor() {
		// TODO Auto-generated method stub
		return repo.getvendor();
	}

	@Override
	public List<VendorDetails> findByCompanyAndCityIdAndStatesId(String company, long cityid, long stateid) {
		// TODO Auto-generated method stub
		return repo.findByCompanyAndCityIdAndStatesId(company, cityid, stateid);
	}

}
