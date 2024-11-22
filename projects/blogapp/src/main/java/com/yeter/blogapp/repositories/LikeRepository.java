package com.yeter.blogapp.repositories;

import com.yeter.blogapp.entities.Comment;
import com.yeter.blogapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findByUserIdAndPostId(Long userId, Long postId);

    List<Like> findByUserId(Long userId);

    List<Like> findByPostId(Long postId);
    @Query(value = " select 'liked',l.post_id,u.user_name from" +
            " p_like l left join user u on u.id=l.user_id" +
            "  where l.post_id in: postsIds limit 5 ",nativeQuery = true)
    List<Object> findUserLikesByPostId(@Param("postIds") List<Long> postIds);
}
