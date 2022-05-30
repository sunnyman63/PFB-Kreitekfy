package com.kreitek.kreitekfy.userSong.infraestructure.persistence;

import com.kreitek.kreitekfy.userSong.domain.entity.UserSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserSongRepository extends JpaRepository<UserSong, Long> {
    boolean existsBySong_IdAndUser_Id(Long id, Long id1);

    UserSong findBySong_IdAndUser_Id(Long id, Long id1);
}
