package com.narvee.usit.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.narvee.usit.controller.TechSupportController;
import com.narvee.usit.entity.Vms;
import com.narvee.usit.repository.IVmsRepository;
import com.narvee.usit.service.IVmsService;

@Service
@Transactional
public class VmsServiceImpl implements IVmsService {
	public static final Logger logger = LoggerFactory.getLogger(VmsServiceImpl.class);

	@Autowired
	private IVmsRepository iVmsRepo;

	@Override
	public boolean saveVms(Vms vms) {
		logger.info("VmsServiceImpl.saveVms()");
		if (vms.getRole() > 1) {
			vms.setVms_stat("Approve");
		}
		System.out.println(vms + " ================= " + vms.getRole());
		Vms save = iVmsRepo.save(vms);
		if (save != null)
			return true;
		else
			return false;
	}

	@Override
	public List<Vms> findAllVms() {
		logger.info("VmsServiceImpl.findAllVms()");
		return null;// iVmsRepo.getallvms("Reject");
	}

	@Override
	public Vms getbyId(long id) {
		logger.info("VmsServiceImpl.getbyId()");
		return iVmsRepo.findById(id).get();
	}

	@Override
	public boolean deleteById(long id) {
		logger.info("VmsServiceImpl.deleteById()");
		iVmsRepo.deleteById(id);
		return true;
	}

	@Override
	public List<Vms> dupvendor(String email) {
		logger.info("VmsServiceImpl.dupvendor()");
		// List<Vms> vms = iVmsRepo.findByEmail(email);
		return null;
	}

//	@Override
	/*
	 * public int changeStatus(String status, int id) { return
	 * iVmsRepo.toggleStatus(status, id); }
	 */

	@Override
	public int changeStatus(String status, Long id, String rem) {
		logger.info("VmsServiceImpl.changeStatus()");
		System.out.println("000000000000000000000000000000000000000000000" + status);
		return 0;// iVmsRepo.toggleStatus(status, id, rem);
	}

	@Override
	public List<Vms> findVmsbyid(long id) {
		logger.info("VmsServiceImpl.findVmsbyid()");
		return null;// iVmsRepo.searchbyid(id);
	}

	public static boolean isValidDate(String inDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {
			System.out.println("threeeeee");
			dateFormat.parse(inDate.trim());
			System.out.println("fourrr");
		} catch (ParseException pe) {
			System.out.println("five");
			return false;
		}
		return true;
	}

	@Override
	public List<Vms> filterVms(String search) {
		logger.info("VmsServiceImpl.filterVms()");
		List<Vms> findAll = new ArrayList();
		if (search != null || !search.equals("dummysearch")) {
			boolean flg = isValidDate(search);
			if (flg == true) {
				try {
					LocalDate today = LocalDate.parse(search.trim());
					// findAll = iVmsRepo.getallvmsbydate(today);
				} catch (DateTimeParseException pe) {
					// findAll = iVmsRepo.getallvms("Reject");
				}
			} else {
				findAll = null;// iVmsRepo.getVMSFilter(search.trim());
			}
		} else {
			// findAll = iVmsRepo.getallvms("Reject");
		}
		return null;// findAll;
	}

	@Override
	public Page<Vms> findPaginated(int pageNo, int pageSize) {
		logger.info("VmsServiceImpl.findPaginated()");
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		// Page<Vms> findAll = iVmsRepo.getallvms(pageable);
		return null;// findAll;
	}

	@Override
	public void save(MultipartFile file) {
	}

	@Override
	public int approve(Vms vms) {
		logger.info("VmsServiceImpl.saveVms()");
		System.out.println(" ================= " + vms.getRole());
		int save = 0;// iVmsRepo.approveStatus(vms.getVms_stat(), vms.getId(), vms.getRole());
		if (save != 0)
			return 1;
		else
			return 0;
	}

}
