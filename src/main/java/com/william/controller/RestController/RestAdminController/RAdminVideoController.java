package com.william.controller.RestController.RestAdminController;

import com.william.entity.Response;
import com.william.entity.ResponseStatus;
import com.william.entity.VideosEntity;
import com.william.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/videos")
public class RAdminVideoController {
    @Autowired
    private VideoService videoService;

    Response response = new Response();

    @PostMapping
    public Response addNewVideo(@RequestBody VideosEntity videosEntity) {
        videoService.save(videosEntity);
        response.setData(videosEntity);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }
    @GetMapping("/videosnew")
    public Response videosNew() {
        ArrayList<VideosEntity> videosEntities2 = (ArrayList<VideosEntity>) videoService.findAll();
        ArrayList<VideosEntity> videosEntities = new ArrayList<>();
        videosEntities.add(videosEntities2.get(0));
        videosEntities.add(videosEntities2.get(1));
        videosEntities.add(videosEntities2.get(2));
        response.setData(videosEntities);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @GetMapping
    public Response allVideos() {
        Iterable<VideosEntity> videosEntities = videoService.findAll();
        response.setData(videosEntities);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @DeleteMapping
    public Response deleteVideo(@RequestParam(name = "idVideo") int id) {
        videoService.deleteById(id);
        response.setData("ok");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @GetMapping("/find")
    public Response findVideo(@RequestParam(name = "idVideo") int id) {
        Optional<VideosEntity> videosEntity = (Optional<VideosEntity>) videoService.findById(id);
        if (videosEntity == null) {
            response.setData(null);
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage("ERROR");
            return response;
        }
        response.setData(videosEntity.get());
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @GetMapping("/findByCategory/{id}")
    public Response allVideosByCategory(@PathVariable(required = false) int id) {
        List<VideosEntity> videosEntities = new ArrayList<>();
        if (id == 0) {
            videosEntities = (List<VideosEntity>) videoService.findAll();
        } else {
            for (VideosEntity videosEntity : videoService.findAll()) {
                if (videosEntity.getCategoryId() == id) {
                    videosEntities.add(videosEntity);
                }
            }
        }
        response.setData(videosEntities);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

}
