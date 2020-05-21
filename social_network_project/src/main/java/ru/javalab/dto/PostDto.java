package ru.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.javalab.models.entity.Post;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostDto {
    private Long id;
    private String text;
    private Long userId;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .text(post.getText())
                .userId(post.getUser().getId())
                .build();
    }

    public static List<PostDto> from(List<Post> posts) {
        return posts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }
}
