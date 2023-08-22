package ituniversal.videocourseserver.service;

import ituniversal.videocourseserver.entity.Course;
import ituniversal.videocourseserver.entity.Module;
import ituniversal.videocourseserver.entity.User;
import ituniversal.videocourseserver.exception.ResourceNotFoundException;
import ituniversal.videocourseserver.payload.ApiResponse;
import ituniversal.videocourseserver.repository.AuthRepository;
import ituniversal.videocourseserver.repository.CourseRepository;
import ituniversal.videocourseserver.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;
    private final AuthRepository authRepository;

    public ApiResponse<?> addModule(UUID userId, UUID courseId, String name) {
        User user = authRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(404, "getUser", "userId", userId));
        if (user == null) {
            return new ApiResponse<>("Sizga module qo'shish mumkin emas", false, 404);
        }
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException(404, "getCourse", "courseId", courseId));
        if (course == null) {
            return new ApiResponse<>("Bunday kurs mavjud emas", false, 404);
        }
        Module module = moduleRepository.save(
                Module.builder()
                        .moduleName(name)
                        .build()
        );
        course.getModules().add(module);
        courseRepository.save(course);
        return new ApiResponse<>("Muvaffaqiyatli saqlandi", true, 200);
    }
}
