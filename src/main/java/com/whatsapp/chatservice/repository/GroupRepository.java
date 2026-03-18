package com.whatsapp.chatservice.repository;

import com.whatsapp.chatservice.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    // Get all groups a user is member of
    @Query("SELECT g FROM Group g WHERE :memberId MEMBER OF g.memberIds")
    List<Group> findGroupsByMemberId(@Param("memberId") Long memberId);

    List<Group> findByCreatedBy(Long createdBy);
}