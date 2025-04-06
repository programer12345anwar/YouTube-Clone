package com.youtube.central.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.youtube.central.models.Channel;


@Repository
public interface ChannelRepo extends JpaRepository<Channel, UUID> {

}