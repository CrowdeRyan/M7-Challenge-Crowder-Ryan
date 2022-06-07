package com.company.musicstorecatalog.Repository;

import com.company.musicstorecatalog.Model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {
}
