package com.sparta.newsfeed.controller;

import com.sparta.newsfeed.dto.PostRequestDto;
import com.sparta.newsfeed.dto.PostResponseDto;
import com.sparta.newsfeed.dto.ResponseDto;
import com.sparta.newsfeed.entity.Post;
import com.sparta.newsfeed.security.UserDetailsImpl;
import com.sparta.newsfeed.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class PostController {

    private final PostService postService;


    @PostMapping("/save")
    public ResponseEntity<ResponseDto<PostResponseDto>> savePost(
            @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
//        빌더 패턴 적용전 코드
//        PostResponseDto responseDto = postService.savePost(requestDto, userDetails.getUser());
//
//        ResponseDto<PostResponseDto> form = new ResponseDto<>(HttpStatus.OK, "success", responseDto);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
//
//        return new ResponseEntity<>(form, headers, HttpStatus.CREATED);
        return ResponseEntity.ok()
                .body(ResponseDto.<PostResponseDto>builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("success")
                        .data(postService.savePost(requestDto, userDetails.getUser()))
                        .build());

    }

    @GetMapping("/{category}")
    public ResponseEntity<ResponseDto<List<PostResponseDto>>> findByCategoryNameToList(
            @PathVariable String category

        return ResponseEntity.ok()
                .body(ResponseDto.<List<PostResponseDto>>builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("success")
                        .data(postService.findByCategoryNameToList(category))
                        .build());
    }

    @GetMapping("/home")
    public ResponseEntity<PostResponse<List<PostResponseDto>>> RecommendedPosts() {
        List<Post> posts = postService.getRecommendedPosts();
        List<PostResponseDto> postResponseDtos = posts.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(
                PostResponse.<List<PostResponseDto>>builder()
                        .StatusCode(HttpStatus.OK.value())
                        .message("추천 순으로 전체 게시글이 조회되었습니다.")
                        .date(postResponseDtos)
                        .build()
        );
    }

    @GetMapping("/{category}/{id}")
    public ResponseEntity<PostResponse<PostResponseDto>> getPostById(@PathVariable Long id) {
        try {
            Post post = postService.getPostById(id);
            PostResponseDto postResponseDto = new PostResponseDto(post);

            return ResponseEntity.ok().body(
                    PostResponse.<PostResponseDto>builder()
                            .StatusCode(HttpStatus.OK.value())
                            .message("선택한 게시글이 조회되었습니다.")
                            .date(postResponseDto)
                            .build()
            );
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            PostResponse.<PostResponseDto>builder()
                                    .StatusCode(HttpStatus.NOT_FOUND.value())
                                    .message("해당 ID에 대한 게시글을 찾을 수 없습니다.")
                                    .build()
                    );
        }
    }


}







        return ResponseEntity.ok()
                .body(ResponseDto.<List<PostResponseDto>>builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("success")
                        .data(postService.findByCategoryNameToList(category))
                        .build());
    }
}
