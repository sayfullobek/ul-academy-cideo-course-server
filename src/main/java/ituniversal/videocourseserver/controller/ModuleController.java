package ituniversal.videocourseserver.controller;

import ituniversal.videocourseserver.payload.ApiResponse;
import ituniversal.videocourseserver.repository.ModuleRepository;
import ituniversal.videocourseserver.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/course-in/module")
@CrossOrigin
@RequiredArgsConstructor
public class ModuleController {
    private final ModuleService moduleService;

    @PostMapping
    public HttpEntity<?> addModule(@RequestParam(name = "userId", required = false) UUID userId,
                                   @RequestParam(name = "courseId", required = false) UUID courseId,
                                   @RequestParam(name = "name", required = false) String name) {
        ApiResponse<?> apiResponse = moduleService.addModule(userId, courseId, name);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
