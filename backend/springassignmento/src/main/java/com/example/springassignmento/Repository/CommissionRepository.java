package com.example.springassignmento.Repository;
import com.example.springassignmento.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long>
{
    List<Commission> findByCreatedDateIs(String date);
}