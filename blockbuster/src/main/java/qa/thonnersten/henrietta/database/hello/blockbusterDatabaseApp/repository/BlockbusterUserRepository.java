package qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qa.thonnersten.henrietta.database.hello.blockbusterDatabaseApp.model.BlockbusterUser;

@Repository
public interface BlockbusterUserRepository extends JpaRepository<BlockbusterUser, Long> {

}

