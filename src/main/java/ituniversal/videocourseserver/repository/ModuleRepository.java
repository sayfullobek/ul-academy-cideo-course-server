package ituniversal.videocourseserver.repository;

import ituniversal.videocourseserver.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {
}
