package com.tutapp.tutorialsapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tutapp.tutorialsapp.model.Tutorial;


// Interface that extends JpaRepository (ORM) for CRUD methods and custom finder methods [findOne()]. It will be autowired in TutorialController.
// Interacts with Tutorials from Database

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);
}
