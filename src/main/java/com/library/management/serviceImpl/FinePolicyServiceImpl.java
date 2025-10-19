package com.library.management.serviceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.management.entity.FinePolicy;
import com.library.management.repository.FinePolicyRepository;
import com.library.management.service.FinePolicyService;

@Service
public class FinePolicyServiceImpl implements FinePolicyService {

	private static final double DEFAULT_FINE_PER_DAY = 10.0;

	@Autowired
	private FinePolicyRepository finePolicyRepository;

	public FinePolicyServiceImpl(FinePolicyRepository finePolicyRepository) {
		this.finePolicyRepository = finePolicyRepository;
	}

	@Override
	public FinePolicy createOrUpdate(String category, double finePerDay) {
		var policy = finePolicyRepository.findByCategoryIgnoreCase(category)
				.orElse(FinePolicy.builder().category(category).build());
		policy.setFinePerDay(finePerDay);
		return finePolicyRepository.save(policy);
	}

	@Override
	public List<FinePolicy> getAll() {
		return finePolicyRepository.findAll();
	}

	@Override
	public double getFineForCategory(String category) {
		return finePolicyRepository.findByCategoryIgnoreCase(category).map(FinePolicy::getFinePerDay)
				.orElse(DEFAULT_FINE_PER_DAY);
	}
}
