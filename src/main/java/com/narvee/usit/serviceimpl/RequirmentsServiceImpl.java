package com.narvee.usit.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.AssignRequirement;
import com.narvee.usit.entity.ReqSubmission;
import com.narvee.usit.entity.Requirements;
import com.narvee.usit.entity.Users;
import com.narvee.usit.helper.GetRecruiter;
import com.narvee.usit.repository.IAssignRequirementRepository;
import com.narvee.usit.repository.IRecRequirmentRepository;
import com.narvee.usit.repository.IRecSubmissionRepository;
import com.narvee.usit.service.IRequirmentService;

@Service
public class RequirmentsServiceImpl implements IRequirmentService {

	private static final Logger logger = LoggerFactory.getLogger(RequirmentsServiceImpl.class);
	@Autowired
	private IRecRequirmentRepository repository;

	@Autowired
	IAssignRequirementRepository repo;

	@Autowired
	IRecSubmissionRepository submissionrepo;

	@Autowired
	private EmailService emailService;

	@Override
	public List<Requirements> getAllRequirments() {
		logger.info("!!! inside class : RecRequirmentsServiceImpl, method : getAllRequirments");
		return repository.findAll();// repository.getAllRequirments();
	}

	@Override
	public boolean deleteRequirmentsByID(long reqID) {
		logger.info("!!! inside class : RecRequirmentsServiceImpl, method : deleteRequirmentsByID");
		ReqSubmission submission = submissionrepo.findByRequirementsRequirementid(reqID);
		if (submission == null) {
			repository.deleteById(reqID);
			return true;
		} else {
			return false;
		}

	}

	// saving requirement
	@Override
	public Requirements saveRequirments(Requirements requirements) {
		logger.info("!!! inside class : RecRequirmentsServiceImpl, method : saveRequirments");
		System.out.println(requirements.getEmpid());
		System.out.println(requirements);

		Requirements save = repository.save(requirements);
		if (save.getRequirementid() != null) {
			try {
				emailService.requirementMail(requirements);
			} catch (UnsupportedEncodingException | MessagingException e) {
				e.printStackTrace();
			}
		}
		requirements.getEmpid().forEach(ee -> {
			AssignRequirement asn2 = new AssignRequirement();
			asn2.setUserid(ee.getUserid());
			asn2.setFullname(ee.getFullname());
			asn2.setReqid(save.getRequirementid());
			repo.save(asn2);
		});
		return save;
	}

	@Override
	public Requirements updateRequirment(Requirements requirements) {
		try {
			System.out.println("===========================" + requirements.getUpdatedby());
			emailService.requirementMail(requirements);
		} catch (UnsupportedEncodingException | MessagingException e) {
			System.out.println("--------------------------------");
			e.printStackTrace();
		}
		return repository.save(requirements);
	}

	@Override
	public Requirements getRequrimentByID(long reqID) {
		logger.info("!!! inside class : RecRequirmentsServiceImpl, method : getRequrimentByID");
		Optional<Requirements> requirements = repository.findById(reqID);
		if (requirements.isPresent()) {
			return requirements.get();
		}
		return null;
	}

	@Override
	public boolean updateRequirments(Requirements requirements) {
		logger.info("!!! inside class : RecRequirmentsServiceImpl, method : updateRequirments");
		Requirements req = repository.save(requirements);
		if (req != null) {
			return true;
		}
		return false;
	}

	@Override
	public Page<Requirements> findPaginated(int pageNo, int pageSize) {
		logger.info("!!! inside class : RecRequirmentsServiceImpl, method : findPaginated");
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<Requirements> findAll = repository.findAll(pageable);
		return findAll;
	}

	@Override
	public List<Object[]> getrequirements() {
		return repository.getrequirements();
	}

	@Override
	public String findmaxReqNumber() {
		return repository.findmaxReqNumber();
	}

	@Override
	public List<GetRecruiter> getAssignedemploy(Long id) {
		return repo.getRecruiterbyid(id);
	}

}