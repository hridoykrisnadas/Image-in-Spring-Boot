package com.hridoykrisna.me.BDNews.project.service.impl;

import com.hridoykrisna.me.BDNews.auth.DB.Session;
import com.hridoykrisna.me.BDNews.project.model.Post;
import com.hridoykrisna.me.BDNews.project.model.dto.PostDto;
import com.hridoykrisna.me.BDNews.project.repository.CategoryRepo;
import com.hridoykrisna.me.BDNews.project.repository.PostRepo;
import com.hridoykrisna.me.BDNews.project.service.PostService;
import com.hridoykrisna.me.BDNews.util.ResponseBuilder;
import com.hridoykrisna.me.BDNews.util.ResponseDTO.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final ModelMapper modelMapper;
    private final PostRepo postRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public ResponseDto save(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        System.out.println("Data:\n"+post.getCategory());
        Session.user.ifPresent(user -> post.setCreatedBy(user.getId()));
        Post result = postRepo.save(post);
        if (result != null) {
            PostDto postDto1 = modelMapper.map(result, PostDto.class);
            return ResponseBuilder.getSuccessMessage(HttpStatus.CREATED, "Post Saved", postDto1);
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }


    @Override
    public ResponseDto getAll() {
        List<Post> postList = postRepo.findAll();
        List<PostDto> postDtoList = this.getPostDtos(postList);
        int numberOfElement = postDtoList.get(0).getClass().getDeclaredFields().length;
        return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Successful", postDtoList, numberOfElement, postList.size());
    }

    private List<PostDto> getPostDtos(List<Post> postList) {
        List<PostDto> postDtoList = new ArrayList<>();
        postList.forEach(post -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            PostDto postDto = modelMapper.map(post, PostDto.class);
            postDtoList.add(postDto);
        });
        return postDtoList;
    }

    public static void saveImageToFile(BufferedImage image, String filePath) {
        try {
            File outputFile = new File(filePath);
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage decodeBase64Image(String imageString) {
        BufferedImage image = null;
        byte[] imageByte;
        try {
            imageByte = Base64.getDecoder().decode(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

}
