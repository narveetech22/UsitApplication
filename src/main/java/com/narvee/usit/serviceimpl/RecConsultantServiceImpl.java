package com.narvee.usit.serviceimpl;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.narvee.usit.entity.ConsultantFileUploads;
import com.narvee.usit.entity.RecConsultant;
import com.narvee.usit.entity.ReqSubmission;
import com.narvee.usit.helper.ListRecruitingConsultant;
import com.narvee.usit.repository.IConsultantFileUploadsRepository;
import com.narvee.usit.repository.IRecConsultantRepository;
import com.narvee.usit.repository.IRecSubmissionRepository;
import com.narvee.usit.service.IRecruitingConsultingService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class RecConsultantServiceImpl implements IRecruitingConsultingService {

	private static final Logger logger = LoggerFactory.getLogger(RecConsultantServiceImpl.class);

	@Autowired
	private IRecConsultantRepository repository;

	@Autowired
	private IConsultantFileUploadsRepository filerepository;
	@Autowired
	private IRecSubmissionRepository submissionrepo;

	@Override
	public RecConsultant saveRecruitingConsutant(RecConsultant consultant) {
		logger.info("!!! inside class : RecConsultantServiceImpl, !! method : saveRecruitingConsutant");
		return repository.save(consultant);
	}

	@Override
	public List<ListRecruitingConsultant> getAllRecruitingConsultant() {
		return repository.getall(); // repository.getAllRequirments();
	}

	@Override
	public RecConsultant getConsultantByID(long id) {
		logger.info("!!! inside class : RecConsultantServiceImpl, !! method : saveRecruitingConsutant");
		Optional<RecConsultant> recConsultant = repository.findById(id);
		if (recConsultant.isPresent()) {
			return recConsultant.get();
		}
		return null;
	}

	@Override
	public boolean deleteRecruitingConsultantByID(long id) {
		logger.info("!!! inside class : RecConsultantServiceImpl, !! method : saveRecruitingConsutant");
		// RecConsultant consultant = repository.findById(id).get();
		ReqSubmission submission = submissionrepo.findByConsultantId(id);
		if (submission == null) {
			filerepository.queryfordelete(id);
			repository.deleteById(id);
			return true;
		} else {
			return false;
		}

	}

//	@Override
//	public RecruitingConsultant getDetailsByConsultantName(String name, long id) {
//		return repository.getDetailsConsultantByName(name,id);
//	}

	@Override
	public Page<List<RecConsultant>> findPaginated(int pageNo, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//		Page<List<RecConsultant>> findAll = repository.getAllRequrimentsConsultantByPageNo(pageable);
		return null; // findAll;
	}

	@Override
	public long changeStatus(String status, long id) {
		return 0; // repository.toggleStatus(status, id);
	}

	@Override
	public List<RecConsultant> getAllRecConsultantByFilter(String keyword) {
//		List<RecConsultant> consultants = repository.getAllRecConsultantFilter(keyword);
		return null; // consultants;
	}

	@Override
	public int update(String resume, long id) {
		Integer upd = repository.update(resume, id);
		if (upd == null) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public List<Object[]> getconsultants() {
		return repository.getconsultants();
	}

	@Override
	public List<RecConsultant> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public int toggleStatus(String status, Long id, String rem) {
		int save = repository.toggleStatus(status, id, rem);
		if (save != 0)
			return 1;
		else
			return 0;

	}

	@Override
	public ConsultantFileUploads uploadfiles(String files, long id) {
		ConsultantFileUploads c = new ConsultantFileUploads();
		c.setConsultantid(id);
		c.setFilename(files);
		// filerepository.queryfordelete(id);
		ConsultantFileUploads upd = filerepository.save(c);
		return upd = filerepository.save(c);
	}

	@Override
	public ConsultantFileUploads getFile(long id) throws FileNotFoundException {
		return filerepository.findById(id).orElseThrow(() -> new FileNotFoundException("File does not exist" + id));
	}

	@Override
	public Resource download(long id) throws FileNotFoundException {
		ConsultantFileUploads model = filerepository.findById(id)
				.orElseThrow(() -> new FileNotFoundException("File does not exist" + id));
		String filesPath = "D:/stores2/";
		String filename = model.getFilename();
		System.out.println(filename);
		try {
			Path file = Paths.get(filesPath).resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public List<Object[]> getconsultantsbyRequirementId(long id) {
		return repository.getconsultantsByreqId(id);
	}

}
