package com.petrov.storage.Persist.Repo;

import com.petrov.storage.Persist.Entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface FileRepository extends JpaRepository<File, Long>, JpaSpecificationExecutor<File> {
}
