package com.nutritrack.repo;

import com.nutritrack.model.HealthRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, String> {
  List<HealthRecord> findByUserIdOrderByTimestampAsc(String userId);
}

